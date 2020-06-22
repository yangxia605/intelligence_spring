package com.intelligent.controller;

import com.intelligent.controller.type.Data;
import com.intelligent.controller.type.Response;
import com.intelligent.dao.UsersDao;
import com.intelligent.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 后端接口层，后端接口在此文件夹下完成
 */
@RestController
public class UserController {
    private final UsersDao usersDao;

    public UserController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    // 用户登录接口
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public Response login(@RequestParam("username") String name, @RequestParam("password") String password, HttpServletRequest request) {
        String message = null;
        Users user = usersDao.getUser(name, password);
        Response response = new Response();
        if (user != null) {
            response.setMessage("登录成功");
            response.setSuccess(true);
            response.setCode(20000);
            Data data = new Data();
            data.setToken("admin-token");
            response.setData(data);
        } else {
            response.setMessage("登录失败！");
            response.setSuccess(false);
        }
        return response;
    }


}

