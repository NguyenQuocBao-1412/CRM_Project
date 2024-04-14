package com.cyber.crm.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AutoLoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        //Lấy đường dẫn
        String path = request.getServletPath();

        if(session != null && session.getAttribute("email") != null && !session.getAttribute("email").equals("")) {
            // Chuyển hướng về trang chủ
            response.sendRedirect("http://localhost:8080/dashboard");
        }else{
            // Cho đi tiếp vào đường dẫn mà client đang gọi
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
