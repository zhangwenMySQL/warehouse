package com.itheima.interceptor;

import com.itheima.domain.Entity.CarUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrivilegeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String targetURL = requestURI.substring(contextPath.length());
        if (("/user/register").equals(targetURL)) {
            return true;
        }
        HttpSession session = request.getSession();
        CarUser user = (CarUser) session.getAttribute("user");
        if (session == null || user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }
        return true;
    }
}
