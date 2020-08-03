package com.intelligent.interceptor;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    private static final Logger log = Logger.getLogger(JwtAuthEntryPoint.class.getName());

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse httpResponse,
                         AuthenticationException e)
            throws IOException, ServletException {

        log.log(Level.SEVERE, "Unauthorized error. Message", e);
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "用户名或密码错误！");
    }
}
