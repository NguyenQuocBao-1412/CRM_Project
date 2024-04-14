package com.cyber.crm.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

//Ứng dụng cookie
//Nhớ mật khẩu
@Controller
@RequestMapping("/cookie")
public class DemoCookieController {

    @GetMapping("")
    public String createCookie(HttpServletResponse response, HttpServletRequest request) {
//        //Tạo cookie
//        Cookie cookie = new Cookie("hello", URLEncoder.encode("Nguyễn Quốc Bảo", StandardCharsets.UTF_8));
//
//        //server bắt client tạo cookie
//        response.addCookie(cookie);

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("hello")) {
                String name = cookie.getName();
                String value = cookie.getValue();
                int age = cookie.getMaxAge();

                System.out.println("Kiem tra name " + name);
                System.out.println("Kiem tra value " + value);
                System.out.println("Kiem tra age " + age);
            }
        }

        return "login";
    }
}
