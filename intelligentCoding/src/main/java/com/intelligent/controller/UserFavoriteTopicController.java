package com.intelligent.controller;

import com.intelligent.dao.TopicDao;
import com.intelligent.dao.UserFavoriteTopicDao;
import com.intelligent.dao.UsersDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class UserFavoriteTopicController {
    private final UsersDao usersDao;
    private final UserFavoriteTopicDao userFavoriteTopicDao;
    private final TopicDao topicDao;
    private static final Logger log =Logger.getLogger(UserFavoriteTopicController.class.getName());

    public UserFavoriteTopicController(UsersDao usersDao, UserFavoriteTopicDao userFavoriteTopicDao, TopicDao topicDao) {
        this.usersDao = usersDao;
        this.userFavoriteTopicDao = userFavoriteTopicDao;
        this.topicDao = topicDao;
    }

    // 获取收藏题目
    @RequestMapping(value = "favoritetopics", method = RequestMethod.GET)
    public List<Map<String,Object>> getFavoriteTidByuid(final HttpServletRequest request){
        List<Map<String,Object>> favoriteTopics = new ArrayList<Map<String, Object>>();
        String name = request.getSession().getAttribute("userName").toString();
        int uid = (int) request.getSession().getAttribute("userID");
        // 是否登录
        if( request.getSession().getAttribute("userID") != null ){
            int[] favoriteTidByuid = userFavoriteTopicDao.getFavoriteTidByuid(uid);
            if(favoriteTidByuid != null){
                for (int tempID:favoriteTidByuid){
                    Map<String, Object> map = topicDao.getTopicInfobyTid(tempID);
                    favoriteTopics.add(map);
                }
            }
        }
        return favoriteTopics;

    }

}
