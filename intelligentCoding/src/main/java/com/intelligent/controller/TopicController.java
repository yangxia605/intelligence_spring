package com.intelligent.controller;

import com.alibaba.fastjson.JSONArray;
import com.intelligent.controller.type.Data;
import com.intelligent.controller.type.Response;
import com.intelligent.dao.TopicDao;
import com.intelligent.model.Topic;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    // 题库
    @RequestMapping(value = "alltopics", method = RequestMethod.GET)
    public List<Map<String,Object>> getAllTopics(HttpServletRequest request){
        List<Map<String,Object>> topics = topicDao.getAllTopics();
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(20000);
        return topics;
    }
}
