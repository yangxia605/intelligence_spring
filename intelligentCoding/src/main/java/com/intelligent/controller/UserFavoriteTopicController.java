package com.intelligent.controller;

import com.intelligent.controller.type.Response;
import com.intelligent.dao.TopicDao;
import com.intelligent.dao.UserFavoriteTopicDao;
import com.intelligent.dao.UsersDao;
import com.intelligent.model.UserFavoriteTopic;
import com.intelligent.model.Users;
import com.intelligent.service.UserFavoriteTopicService;
import com.intelligent.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class UserFavoriteTopicController {
    private final UsersDao usersDao;
    private final UserFavoriteTopicDao userFavoriteTopicDao;
    private final TopicDao topicDao;

    @Autowired
    private UserFavoriteTopicService userFavoriteTopicService;

    private static final Logger log = Logger.getLogger(UserFavoriteTopicController.class.getName());

    public UserFavoriteTopicController(UsersDao usersDao, UserFavoriteTopicDao userFavoriteTopicDao, TopicDao topicDao) {
        this.usersDao = usersDao;
        this.userFavoriteTopicDao = userFavoriteTopicDao;
        this.topicDao = topicDao;
    }

    // 获取收藏题目
    @RequestMapping(value = "favoritetopics", method = RequestMethod.GET)
    public List<Map<String, Object>> getFavoriteTidByuid(final HttpServletRequest request) {
        List<Map<String, Object>> favoriteTopics = new ArrayList<Map<String, Object>>();
        //String name = request.getSession().getAttribute("userName").toString();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int uid = usersDao.findByName(name).getId();
        //int uid = (int) request.getSession().getAttribute("userID");
        // 是否登录
        if (usersDao.findByName(name).getId() != null) {
            int[] favoriteTidByuid = userFavoriteTopicDao.getFavoriteTidByuid(uid);
            if (favoriteTidByuid != null) {
                for (int tempID : favoriteTidByuid) {
                    Map<String, Object> map = topicDao.getTopicInfobyTid(tempID);
                    favoriteTopics.add(map);
                }
            }
        }
        return favoriteTopics;

    }

    // 添加收藏题目
    @PostMapping(path = "favorite-topic/{topicId}")  //URL地址
    public Response addFavoriteTopicByTid(@PathVariable("topicId") int topicId) {
        Users user = UserContext.getCurrentUser();
        UserFavoriteTopic userFavoriteTopic = userFavoriteTopicService.addFavoriteTopicByTid(topicId, user.getId());
        Response response = new Response();
        if (userFavoriteTopic != null) {
            response.setSuccess(true);
        }
        return response;
    }

    //取消题目收藏
    // 添加收藏题目
    @DeleteMapping(path = "cancel-favorite-topic/{topicId}")  //URL地址
    public Response cancelFavoriteTopicByTid(@PathVariable int topicId) {
        Users user = UserContext.getCurrentUser();
        userFavoriteTopicService.cancelFavoriteTopicByTid(topicId, user.getId());
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }
}
