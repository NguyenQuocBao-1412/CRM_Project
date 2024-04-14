package com.cyber.crm.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.cyber.crm.entity.UsersEntity;
import com.cyber.crm.repositoty.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("")
    public String login(HttpServletRequest request, Model model) {

        Cookie[] cookies = request.getCookies();

        if(cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals("email")) {
                    model.addAttribute("email", cookie.getValue());
                }

                if(cookie.getName().equals("password")) {
                    model.addAttribute("password", cookie.getValue());
                }
            }
        }

        return "login";
    }

    @PostMapping("")
    public String progressLogin(@RequestParam(required = false) String email, @RequestParam(required = false) String password,
                                @RequestParam(required = false) boolean remember, HttpServletResponse response,
                                Model model, HttpSession httpSession) {

        // Kiểm tra email, password trong database
        List<UsersEntity> listUser = usersRepository.findByEmailAndPassword(email, password);

        //
        if(!listUser.isEmpty()) {

//            //Tạo cookie để tự động điền email, password cho lần sau
            if(remember){
                Cookie emailCookie = new Cookie("email", email);
                Cookie passwordCookie = new Cookie("password", password);

                response.addCookie(emailCookie);
                response.addCookie(passwordCookie);
            }

            // Tạo Sesstion cho chức năng tự động đăng nhập
            httpSession.setAttribute("email", email);

            String role = listUser.get(0).getRolesEntity().getName();
            System.out.println("kiem tra role " + role);
            httpSession.setAttribute("role", role);

            httpSession.setAttribute("id", listUser.get(0).getId());
            httpSession.setAttribute("fullname", listUser.get(0).getFullname());
            httpSession.setAttribute("userId", listUser.get(0).getId());

            httpSession.setMaxInactiveInterval(8 * 60 * 60);

            return "redirect:dashboard";
        } else  {
            model.addAttribute("isSuccess", false);
            return "login";
        }
    }

}
