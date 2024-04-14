package com.cyber.crm.controller;

import com.cyber.crm.request.TaskAddRequest;
import com.cyber.crm.service.JobService;
import com.cyber.crm.service.TaskService;
import com.cyber.crm.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService  userService;
    @Autowired
    private JobService jobService;

    @GetMapping("")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task";
    }

    @PostMapping("/add")
    public String addTask(Model model, TaskAddRequest request) throws ParseException {
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("jobs", jobService.getAvailableJob());

        taskService.saveTask(request);

        return "task-add";
    }

    @GetMapping("/add")
    public String showAddTask(Model model) {

        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("jobs", jobService.getAvailableJob());

        return "task-add";
    }
}
