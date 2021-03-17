package com.intelligent.controller;

import com.intelligent.controller.type.Data;
import com.intelligent.controller.type.Response;
import com.intelligent.dao.UsersDao;
import com.intelligent.interceptor.JwtProvider;
import com.intelligent.model.Users;
import com.intelligent.controller.type.JwtResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 后端接口层，后端接口在此文件夹下完成
 */
@Api(value = "用户模块")
@RestController
public class UserController {
    private final UsersDao usersDao;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder encoder;
    private static final Logger log = Logger.getLogger(UserController.class.getName());

    public UserController(UsersDao usersDao, AuthenticationManager authenticationManager, JwtProvider jwtProvider, PasswordEncoder encoder) {
        this.usersDao = usersDao;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.encoder = encoder;
    }

    // 用户登录接口
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ResponseEntity login(@RequestParam("username") String name, @RequestParam("password") String password, HttpServletRequest request) {
            /*
                使用spring security+ jwt实现用户登录验证，后续所有api需要携带token才能访问
             */
        log.info(name + " " + encoder.encode(password));
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(name, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    //登出
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public Response logout(final HttpServletRequest request) {
        //已登录，销毁session
//        String name = request.getSession().getAttribute("userName").toString();
//        log.info(name + "已登出");
        request.getSession().invalidate();
        Response response = new Response();
        response.setMessage("已登出！");
        response.setSuccess(true);
        response.setCode(20000);
        return response;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public Response register(@RequestParam("username") String name, @RequestParam("password") String password, HttpServletRequest request) {
        Response response = new Response();
        Users users = usersDao.findByName(name);
        if (users != null) {
            response.setCode(200);
            response.setMessage("用户名已被占用");
            response.setSuccess(false);
        } else {
            usersDao.insertUser(name, encoder.encode(password));
            response.setCode(400);
            response.setMessage("注册成功，请重新登录");
            response.setSuccess(true);
        }
        return response;
    }

    // 获取用户信息
    @RequestMapping(value = "userInfo", method = RequestMethod.GET)
    public Map<String, Object> getuserInfo(final HttpServletRequest request) {
        //String name = request.getSession().getAttribute("userName").toString();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = new HashMap<String, Object>();
        if (name != null) {
            Users user = usersDao.findByName(name);
            userInfo.put("username", user.getName());
            userInfo.put("location", user.getLocation());
            userInfo.put("email", user.getEamil());
            userInfo.put("intro", user.getIntro());
            if (user.isGender()) {
                userInfo.put("gender", "女");
            } else {
                userInfo.put("gender", "男");
            }
            if (user.isCareer()) {
                userInfo.put("career", "学生");
            } else {
                userInfo.put("career", "工作");
            }
        } else {
            // 未登录情况
        }
        return userInfo;
    }

}

