package com.intelligent.controller;

import com.alibaba.fastjson.JSONArray;
import com.intelligent.controller.type.Data;
import com.intelligent.controller.type.Response;
import com.intelligent.dao.TopicDao;
import com.intelligent.model.Topic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class TopicController {
    private final TopicDao topicDao;
    private static final Logger log =Logger.getLogger(TopicController.class.getName());

    public TopicController(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    // 全部题目（id name time space）
    @RequestMapping(value = "alltopics", method = RequestMethod.GET)
    public List<Map<String,Object>> getAllTopics(){
        return topicDao.getAllTopicsInfo();
    }

    // tid题目信息（id name time space）
    public Map<String,Object> getTopicInfobyTid(@RequestParam("topicID") int tid){
        return topicDao.getTopicInfobyTid(tid);
    }

    // tid题目完整信息
    public Topic getTopicbyTid(@RequestParam("topicID") int tid){
        return topicDao.getTopicbyTid(tid);
    }
}
