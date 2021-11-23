package com.intelligent.controller;

import com.github.pagehelper.PageInfo;
import com.intelligent.controller.type.PageRequest;
import com.intelligent.controller.type.Result;
import com.intelligent.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
public class ControllerTest {

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "test01", method = RequestMethod.GET)
    public String test(@RequestParam("id") int id) {
        return id + "hello wanxia!";
    }

    @RequestMapping(value = "test02", method = RequestMethod.POST)
    public PageInfo getKeywords(@RequestParam("keywords") String keywords, @RequestBody PageRequest pageRequest) {
        Result pageResult = new Result();
        return topicService.getByKeyword(keywords, pageRequest);
    }

}
