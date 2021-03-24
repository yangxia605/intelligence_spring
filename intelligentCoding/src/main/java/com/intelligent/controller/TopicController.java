package com.intelligent.controller;

//import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;
import com.intelligent.controller.type.*;
import com.intelligent.dao.TopicDao;
import com.intelligent.dao.UsersDao;
import com.intelligent.model.Topic;
import com.intelligent.model.Users;
import com.intelligent.service.TopicService;
import com.intelligent.type.TopicWithUserFavorite;
import com.intelligent.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class TopicController {
    private final TopicDao topicDao;
    private final UsersDao usersDao;
    @Autowired
    private TopicService topicService;
    private static final Logger log = Logger.getLogger(TopicController.class.getName());

    public TopicController(TopicDao topicDao, UsersDao usersDao) {
        this.topicDao = topicDao;
        this.usersDao = usersDao;
    }

    // 全部题目（id name time space）
    @RequestMapping(value = "alltopics", method = RequestMethod.GET)
    public List<Map<String, Object>> getAllTopics() {
        return topicDao.getAllTopicsInfo();
    }

    // tid题目信息（id name time space）
    public Map<String, Object> getTopicInfobyTid(@RequestParam("topicID") int tid) {
        return topicDao.getTopicInfobyTid(tid);
    }

    // 通过tid获取题目完整信息
    @GetMapping(path = "topics/{topicId}")  //URL地址
    public TopicWithUserFavorite getTopicByTid(@PathVariable("topicId") int topicId) {
        Users user = UserContext.getCurrentUser();
        TopicWithUserFavorite topicByTid = topicService.getTopicByTid(user.getId(), topicId);
        return topicByTid;
    }

//    // online_1: 获取所有编程题目
//    @RequestMapping(value = "getAllTopic", method = RequestMethod.GET)
//    public Result getAllCoding() {
//        Result result = new Result();
////        PageHelper.startPage(page,offset);
//        List<Topic> list = topicDao.getAll();
//        if (list.size() != 0) {
//            result.setCode(200);
//            result.setSuccess(true);
//            result.setMessage("查询编程题目成功");
//            result.setData(list);
//        }
//        else {
//            result.setCode(100);
//            result.setSuccess(false);
//            result.setMessage("查询编程题目失败");
//            result.setData(null);
//        }
//        return result;
//    }
//
//    // online_2: 通过关键词获取题目，即搜索题目功能
//    @RequestMapping(value = "getByKeywords", method = RequestMethod.GET)
//    public Result getByPName(@RequestParam("keywords") String keyword) {
//        Result result = new Result();
//        List<Topic> list = new ArrayList<>();
//        list = topicDao.getByKeyword(keyword);
//        if (list.size() != 0) {
//            result.setCode(200);
//            result.setSuccess(true);
//            result.setMessage("通过关键词查询题目成功");
//            result.setData(list);
//        }
//        else {
//            result.setCode(200);
//            result.setSuccess(true);
//            result.setMessage("未找到匹配该关键词的题目");
//            result.setData(list);
//        }
//        return result;
//    }
//
//    // online_3: 按难度查找题目
//    @RequestMapping(value = "getTopicByLevel", method = RequestMethod.GET)
//    public Result getByLevel(@RequestParam("level") Integer level) {
//        Result result = new Result();
//        List<Topic> list = new ArrayList<>();
//        list = topicDao.getTopicByLevel(level);
//        if(list.size() != 0) {
//            result.setCode(200);
//            result.setSuccess(true);
//            result.setMessage("通过题目难度查找题目成功");
//            result.setData(list);
//        }
//        else {
//            result.setCode(200);
//            result.setSuccess(true);
//            result.setMessage("未找到匹配该难度的题目");
//            result.setData(list);
//        }
//        return result;
//    }
//
//    // online_4: get按题号对题目进行排序,返回重新排序的结果
//    @RequestMapping(value = "getTopicByOrder", method = RequestMethod.GET)
//    public Result getByOrder(@RequestParam("order")boolean order) {
//        Result result = new Result();
//        List<Topic> list = new ArrayList<>();
//        if (order == true) list = topicDao.getAll();
//        else list = topicDao.getTopicByDesc();
//        if(list.size() != 0) {
//            result.setCode(200);
//            result.setSuccess(true);
//            result.setMessage("返回排序后的结果");
//            result.setData(list);
//        }
//        else {
//            result.setCode(100);
//            result.setSuccess(true);
//            result.setMessage("没有相关题目");
//            result.setData(list);
//        }
//        return result;
//    }
//
//    // online_5: 这里多表查询，topic-knowledge_point_topic-knowledge_points三表查询
//    @RequestMapping(value = "getTopicByTypes", method = RequestMethod.GET, params = "type")
//    public Result getByTypes(@RequestBody List<Integer> type) {
//        Result result = new Result();
//        List<Topic> list = new ArrayList<>();
//        int n = type.size();
//        for(int i=0;i<n;i++) {
//            list.addAll(topicDao.getTopicByTypes(type.get(i)));
//        }
//        result.setData(list);
//        result.setSuccess(true);
//        if(list.size() != 0) {
//            result.setCode(200);
//            result.setMessage("按照知识点查找成功");
//        }
//        else {
//            result.setCode(100);
//            result.setMessage("未找到匹配的结果");
//        }
//        return result;
//    }

//    // online_6: getByPassRate
//    // 单表查询：answer
//    @RequestMapping(value = "getTopicByPassRate", method = RequestMethod.GET)
//    public Result getByPassRate(@RequestParam("rate")boolean rate) {
//        Result result = new Result();
//        List<Topic> list = new ArrayList<>();
//        if(rate) {
//            list = topicDao.getTopicByRate();
//            result.setData(list);
//        }
//        else {
//            list = topicDao.getTopicByRateDesc();
//            result.setData(list);
//        }
//        result.setSuccess(true);
//        if(list.size() != 0) {
//            result.setCode(200);
//            result.setMessage("根据通过率查询成功");
//        }
//        else {
//            result.setCode(100);
//            result.setMessage("未找到匹配结果");
//        }
//        return result;
//    }
//
//    // online_7: getReset
//    @RequestMapping(value = "getTopicReset", method = RequestMethod.GET)
//    public Result getReset() {
//        Result result = new Result();
//        List<Topic> list = topicDao.getAll();
//        if (list.size() != 0) {
//            result.setCode(200);
//            result.setSuccess(true);
//            result.setMessage("题目重置成功");
//            result.setData(list);
//        }
//        else {
//            result.setCode(100);
//            result.setSuccess(false);
//            result.setMessage("题目重置成功");
//            result.setData(null);
//        }
//        return result;
//    }

    // online_8: getGlobalSearch
    @RequestMapping(value = "getGlobalSearch", method = RequestMethod.GET)
    public Result getGlobalSearch(@RequestParam("keyword") String keyword) {
        Result result = new Result();
        System.out.println("hhh");
        List<Topic> list = new ArrayList<>();

        return result;
    }

    // online_9: getUserPass
    // 多表查询：user
    // 返回结果：题目总数、挑战题数、通过题数、提交总次数
    @RequestMapping(value = "getUserPass", method = RequestMethod.GET)
    public UserPassMessage getUserPass(@RequestParam("userId") Integer userId) {
        return topicService.getUserPass(userId);
    }


    /**
     *  下面实现了分页加载数据的功能
     */
    /**
     * online_1: 获取所有数据
     *
     * @Param: pagerRequest
     * @Return: result
     **/
//    @ApiOperation(value = "题目列表")
//    @ApiResponses(@ApiResponse(code = 200, message = "处理成功"))
    @RequestMapping(value = "getAllCoding", method = RequestMethod.POST)
    public PageInfo getPage(@RequestBody PageRequest pageRequest) {
        return topicService.getAll(pageRequest);
    }

    /**
     * online_2: 通过关键词找题目
     **/
    @RequestMapping(value = "getByPName", method = RequestMethod.POST)
    public PageInfo getKeywords(@RequestParam("keywords") String keywords, @RequestBody PageRequest pageRequest) {
        Result pageResult = new Result();
        return topicService.getByKeyword(keywords, pageRequest);
    }

    /**
     * online_3: 通过题目难度找题目
     *
     * @return*/
    @RequestMapping(value = "getByLevel", method = RequestMethod.POST)
    public PageInfo getTopicByLevel(@RequestParam("level") int level, @RequestBody PageRequest pageRequest) {
        Result pageResult = new Result();
        return topicService.getTopicByLevel(level, pageRequest);
    }

    /**
     * online_4: 按题号逆序选择题目
     **/
    @RequestMapping(value = "getByOrder", method = RequestMethod.POST)
    public PageInfo getTopicByDesc(@RequestParam("order") boolean order, @RequestBody PageRequest pageRequest) {
        Result pageResult = new Result();
        if (order) {
            return topicService.getAll(pageRequest);
        }
        return topicService.getTopicByDesc(pageRequest);
    }

    /**
     * online_5: 通过知识点筛选题目
     **/
    @RequestMapping(value = "getByTypes", method = RequestMethod.POST)
    public PageInfo getTopicByTypes(@RequestParam("page") int page, @RequestParam("offset") int offset, @RequestBody List<Integer> types) {
        Result<List<Topic>> pageResult = new Result<>();
        List<Topic> list = new ArrayList<>();
        int n = types.size();
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPage(page);
        pageRequest.setOffset(offset);
        return topicService.getTopicByTypes(types, pageRequest);
    }

    /**
     * online_6: 通过题目通过率筛选题目
     **/
    @RequestMapping(value = "getByPassRate", method = RequestMethod.POST)
    public PageInfo getTopicByRate(@RequestParam("rate") boolean rate, @RequestBody PageRequest pageRequest) {
        PageInfo pageResult;
        if (rate) {
            pageResult = topicService.getTopicByRate(pageRequest);
        } else {
            pageResult = topicService.getTopicByRateDesc(pageRequest);
        }
        return pageResult;
    }

    /**
     * online_7: 题目重置
     **/
    @RequestMapping(value = "getReset", method = RequestMethod.POST)
    public Result<Topic> getTopicReset(@RequestBody PageRequest pageRequest) {
        Result pageResult = new Result();
//        pageResult = topicService.getAll(pageRequest);
        return pageResult;
    }

    /** online_8: 全局搜索 **/

}
