package com.cyber.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/403")
public class NotPermitController {

    @GetMapping("")
    public String dashboard() {
        return "403.html";
    }
}
