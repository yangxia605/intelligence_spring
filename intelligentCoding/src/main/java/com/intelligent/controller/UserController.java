package com.intelligent.controller;

import com.intelligent.controller.type.Data;
import com.intelligent.controller.type.Response;
import com.intelligent.dao.UsersDao;
import com.intelligent.model.Users;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * 后端接口层，后端接口在此文件夹下完成
 */
@RestController
public class UserController {
    private final UsersDao usersDao;
    private static final Logger log =Logger.getLogger(UserController.class.getName());

    public UserController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    // 用户登录接口
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public Response login(@RequestParam("username") String name, @RequestParam("password") String password, HttpServletRequest request) {
        String message = null;
        Users user = usersDao.getUserByNameAndPassword(name, password);
        Users userbyname = usersDao.getUserByName(name);
        Response response = new Response();
        if (user != null) {
            response.setMessage("登录成功");
            response.setSuccess(true);
            response.setCode(20000);
            Data data = new Data();
            data.setToken("admin-token");
            response.setData(data);
            /*
                将登录成功的userID存到session中
                该用户后续的请求调用session.getAttribute("userID")将返回userID
                该用户后续的请求调用session.getAttribute("userName")将返回userName
             */

            log.info(user.getName() + "已登录");
            request.getSession().setAttribute("userID", user.getId());
            request.getSession().setAttribute("userName", user.getName());

        } else if(userbyname != null){
            response.setMessage("密码错误！");
            response.setSuccess(false);
        } else {
            response.setMessage("用户不存在！");
            response.setSuccess(false);
        }
        return response;
    }

    //登出
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Response logout(final HttpServletRequest request) {
        //已登录，销毁session
        String name = request.getSession().getAttribute("userName").toString();
        log.info(name + "已登出");
        request.getSession().invalidate();
        Response response = new Response();
        response.setMessage("已登出！");
        response.setSuccess(true);
        response.setCode(20000);
        return response;
    }

}

