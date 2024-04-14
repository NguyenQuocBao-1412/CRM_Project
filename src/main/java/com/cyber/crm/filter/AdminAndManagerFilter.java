package com.cyber.crm.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AdminAndManagerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        if(session != null && session.getAttribute("email") != null && !session.getAttribute("email").equals("")) {
            if(session.getAttribute("role").equals("ROLE_ADMIN") || session.getAttribute("role").equals("ROLE_MANAGER")) {
                // Cho đi tiếp vào đường dẫn mà client đang gọi
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect("http://localhost:8080/403");
            }

        }else{
            // Chuyển hướng về login
            response.sendRedirect("http://localhost:8080/login");
        }
    }
}
