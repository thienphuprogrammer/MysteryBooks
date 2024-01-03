package com.example.mysterybook.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/home/*", "/friends/*", "/profile/*",
        "/home/*", "/friends/*", "/profile/*", "/messages/*", "/notifications/*", "/settings/*"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletReq = (HttpServletRequest) request;
        Cookie[] cookies = servletReq.getCookies();
        int userId = -1;
        boolean isLogin = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                userId = Integer.parseInt(cookie.getValue());
                isLogin = true;
                break;
            }
        }
        if (!isLogin) {
            ((HttpServletResponse) response).sendRedirect("login");
            return;
        }
        request.setAttribute("isLogin", isLogin);
        request.setAttribute("userId", userId);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
