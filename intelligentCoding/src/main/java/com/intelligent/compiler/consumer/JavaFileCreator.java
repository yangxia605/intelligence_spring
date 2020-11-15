package com.intelligent.compiler.consumer;

import com.google.common.base.Charsets;
import com.intelligent.model.Answer;
import com.intelligent.type.AnswerStatus;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.intelligent.compiler.Constants.FILE_NAME;
import static com.intelligent.compiler.Constants.SPLIT;

public class JavaFileCreator {
    public static Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);
    private static final String IN_FILE_NAME = "in.in";
    private static final String OUT_FILE_NAME = "out.out";
    private static final String COMPILE_PATH = "/tmp/";

    private final String templatePath;
    private final Answer answer;

    public JavaFileCreator(String templatePath, Answer answer) {
        this.templatePath = templatePath;
        this.answer = answer;
    }

    public File creat() {
        String fullPath = COMPILE_PATH + UUID.randomUUID().toString();
        String fullFileName = fullPath + "/" + FILE_NAME;

        File dir = new File(fullPath);

        try {
            boolean mkdirs = dir.mkdirs();
            File file = new File(fullFileName);
            boolean newFile = file.createNewFile();
            logger.info("生成文件 {}", fullFileName);
            //根据template path取到代码模版,然后把提交的答案替换进去
            String templateContent = readTemplate();
            Map<String, String> map = createTemplateDataModel();
            Template template = new Template("strTpl", templateContent, new Configuration(new Version("2.3.30")));
            StringWriter result = new StringWriter();
            template.process(map, result);
            logger.debug("java file content:{}", result.toString());
            FileUtils.writeStringToFile(file, result.toString(), Charsets.UTF_8);
        } catch (Exception e) {
            //保存失败
            logger.error("", e);
            answer.setStatus(AnswerStatus.COMPILE_FAILED.name());
            answer.setFailCount(answer.getFailCount() + 1);
            answer.setExecuteDetailMsg(e.getMessage());
        }

        return dir;
    }

    private Map<String, String> createTemplateDataModel() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("code", answer.getCode());
        //根据in out 生成 exec,分隔符是SPLIT
        List<String> in = readInTestCase();
        List<String> out = readOutTestCase();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < in.size(); i++) {
            String inString = in.get(i);
            String inArgs = StringUtils.join(StringUtils.split(inString, SPLIT), ",");
            String outString = out.get(i);
            //exec(in.arg1...,out.arg,time,solution);
            builder.append("exec(")
                    .append(inArgs).append(",")
                    .append(outString).append(",")
                    //需要获得topic的执行时间限制
                    .append("1000").append(",")
                    .append("solution);").append("\n");
        }
        map.put("exec", builder.toString());
        return map;
    }

    private String readTemplate() throws IOException {
        File file = new File(templatePath + FILE_NAME);
        return FileUtils.readFileToString(file, Charsets.UTF_8);
    }

    private List<String> readInTestCase() throws IOException {
        File file = new File(templatePath + IN_FILE_NAME);
        return FileUtils.readLines(file, Charsets.UTF_8);
    }

    private List<String> readOutTestCase() throws IOException {
        File file = new File(templatePath + OUT_FILE_NAME);
        return FileUtils.readLines(file, Charsets.UTF_8);
    }
}
