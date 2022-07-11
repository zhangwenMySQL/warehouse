package com.itheima.filter;

import com.itheima.domain.Entity.CarUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String targetURL = requestURI.substring(contextPath.length());
        HttpSession session = request.getSession();
        CarUser user = (CarUser) session.getAttribute("user");
        if (("/login.jsp").equals(targetURL)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (("/checkCode").equals(targetURL)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (("/register.jsp").equals(targetURL)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }  else if (!(user == null)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (("/").equals(targetURL)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (("/user/login").equals(targetURL)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else if (("/user/register").equals(targetURL)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (requestURI.contains(".css") || requestURI.contains(".jpg") || requestURI.contains(".jpeg") || requestURI.contains(".png") || requestURI.contains(".gif")|| requestURI.contains(".js")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
