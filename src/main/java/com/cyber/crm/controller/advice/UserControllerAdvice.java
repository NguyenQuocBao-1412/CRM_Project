package com.cyber.crm.controller.advice;

import com.cyber.crm.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserControllerAdvice {

    @ModelAttribute
    public void addUserToModel(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("usernameSession", session.getAttribute("fullname"));
        model.addAttribute("userIdSession", session.getAttribute("userId"));
    }
}
