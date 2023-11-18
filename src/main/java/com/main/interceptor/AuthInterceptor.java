package com.main.interceptor;

import com.main.entity.Users;
import com.main.utils.SessionAttr;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Users user = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
        String uri = request.getRequestURI();

        if (user == null && checkURIUser(uri)) {
            response.sendRedirect("/#!/dang-nhap");
            return false;
        }
        if (user != null && checkURIExits(uri)) {
            response.sendRedirect("/#!/trang-chu");
            return false;
        }
        return true;
    }

    private boolean checkURIExits(String uri) {
        return uri.contains("/#!/**");
    }


    private boolean checkURIUser(String uri) {
        return uri.contains("/quan-tri/**");
    }
}
