package com.cyber.crm.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/404")
public class NotFoundController implements ErrorController {

    @GetMapping("")
    public String dashboard() {
        return "404.html";
    }
}
