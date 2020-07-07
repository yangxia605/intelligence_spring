package com.intelligent.controller;

import com.intelligent.controller.type.Response;
import com.intelligent.dao.UserFavoriteTopicDao;
import com.intelligent.dao.UsersDao;
import com.intelligent.model.Topic;
import com.intelligent.model.UserFavoriteTopic;
import com.intelligent.model.Users;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserFavoriteTopicController {
    private final UsersDao usersDao;
    private final UserFavoriteTopicDao userFavoriteTopicDao;

    public UserFavoriteTopicController(UsersDao usersDao, UserFavoriteTopicDao userFavoriteTopicDao) {
        this.usersDao = usersDao;
        this.userFavoriteTopicDao = userFavoriteTopicDao;
    }


    /*@RequestMapping(value = "favoritetopics", method = RequestMethod.GET)
    public List<Map<String,Object>> getFavoriteTopicByuid(@RequestParam("username") String name, HttpServletRequest request){
        Users user = usersDao.getUserByName(name);
        int uid = user.getId();
        List<Map<String,Object>> favoriteTidByuid = userFavoriteTopicDao.getFavoriteTidByuid(uid);
        if(favoriteTidByuid != null){
            // todo: getTopicbytid
        }

    }*/

}
