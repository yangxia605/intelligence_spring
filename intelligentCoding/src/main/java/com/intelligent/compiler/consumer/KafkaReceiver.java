package com.intelligent.compiler.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelligent.compiler.beans.Message;
import com.intelligent.dao.AnswerDao;
import com.intelligent.model.Answer;
import com.intelligent.type.AnswerStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.function.Consumer;

@Component
public class KafkaReceiver {
    public static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AnswerDao answerDao;

    @KafkaListener(topics = "program")
    public void listen(ConsumerRecord<?, String> cr) throws Exception {
        logger.info("message received:{}-{}", cr.key(), cr.value());
        try {
            Message message = objectMapper.readValue(cr.value(), Message.class);
            Answer answer = answerDao.findById(message.getAnswerId()).get();
            new AnswerCodeCompiler().accept(answer);
            answerDao.save(answer);
        } catch (Exception e) {
            logger.error("", e);
        }

    }

    private static class AnswerCodeCompiler implements Consumer<Answer> {
        private static final String COMPILE_PATH = "/tmp/";
        private static final String COMPILE_CMD = "javac ";
        private static final String RUN_CMD = "java ";
        private static final String FILE_NAME = "Solution.java";
        private static final String CLASS_FILE_NAME = "Solution";
        private Answer answer;
        private String fullFileName;

        @Override
        public void accept(Answer answer) {
            //把找到的answer的code拿出来编译，运行
            logger.info("answer code {}", answer.getCode());
            this.answer = answer;
            String fullPath = COMPILE_PATH + UUID.randomUUID().toString();
            fullFileName = fullPath + "/" + FILE_NAME;

            //保存code到文件夹
            File file = new File(fullPath);
            createJavaFile(file);
            try {
                //2 编译刚生成的文件
                String compileResult = exec(COMPILE_CMD + FILE_NAME, file);
                if (StringUtils.isNotBlank(compileResult)) {
                    //编译有返回结果，说明编译失败
                    answer.setStatus(AnswerStatus.FINISH_FAILED.name());
                    answer.setFailCount(answer.getFailCount() + 1);
                    answer.setExecuteDetailMsg(compileResult);
                } else {
                    //3 运行
                    try {
                        String result = exec(RUN_CMD + CLASS_FILE_NAME, file);
                        logger.info("执行结果 {}", result);
                        answer.setStatus(AnswerStatus.FINISH_SUCCESS.name());
                        answer.setFailCount(answer.getSuccCount() + 1);
                        answer.setExecuteDetailMsg("执行完成");
                    } catch (Exception e) {
                        //执行失败
                        logger.error("", e);
                        answer.setStatus(AnswerStatus.FINISH_FAILED.name());
                        answer.setFailCount(answer.getFailCount() + 1);
                        answer.setExecuteDetailMsg(e.getMessage());
                    }
                }
            } catch (Exception e) {
                //编译失败
                logger.error("", e);
                answer.setStatus(AnswerStatus.COMPILE_FAILED.name());
                answer.setFailCount(answer.getFailCount() + 1);
                answer.setExecuteDetailMsg(e.getMessage());
            }
        }

        private void createJavaFile(File path) {
            try {
                boolean mkdirs = path.mkdirs();
                File file = new File(fullFileName);
                boolean newFile = file.createNewFile();
                logger.info("生成文件 {}", fullFileName);
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(answer.getCode());
                bw.close();
            } catch (Exception e) {
                //保存失败
                logger.error("", e);
                answer.setStatus(AnswerStatus.COMPILE_FAILED.name());
                answer.setFailCount(answer.getFailCount() + 1);
                answer.setExecuteDetailMsg(e.getMessage());
            }
        }

        private String exec(String cmd, File dir) {
            StringBuilder result = new StringBuilder();

            Process process = null;
            BufferedReader buffIn = null;
            BufferedReader buffError = null;

            try {
                // 执行命令, 返回一个子进程对象（命令在子进程中执行）
                process = Runtime.getRuntime().exec(cmd, null, dir);

                // 方法阻塞, 等待命令执行完成（成功会返回0）
                process.waitFor();

                // 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
                buffIn = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
                buffError = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));

                // 读取输出
                String line = null;
                while ((line = buffIn.readLine()) != null) {
                    result.append(line).append('\n');
                }
                while ((line = buffError.readLine()) != null) {
                    result.append(line).append('\n');
                }
                logger.info(result.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                closeStream(buffIn);
                closeStream(buffError);

                // 销毁子进程
                if (process != null) {
                    process.destroy();
                }
            }

            return result.toString();
        }

        private static void closeStream(Closeable stream) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception ignored) {
                }
            }
        }
    }
}
