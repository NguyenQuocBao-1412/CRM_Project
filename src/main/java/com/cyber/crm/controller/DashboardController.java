package com.cyber.crm.controller;

import com.cyber.crm.constant.TaskStatusEnum;
import com.cyber.crm.entity.TasksEntity;
import com.cyber.crm.entity.UsersEntity;
import com.cyber.crm.service.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public String dashboard(Model model, HttpSession session) {
        int userId = (int)session.getAttribute("userId");
        System.out.println(userId);

        int countUnfulfilledTasks = taskService.countTasksByUserIdAndStatusId(userId, TaskStatusEnum.CHUA_THUC_HIEN.getId());
        model.addAttribute("countUnfulfilledTasks", countUnfulfilledTasks);
        System.out.println(countUnfulfilledTasks);

        int countProcessingTasks = taskService.countTasksByUserIdAndStatusId(userId, TaskStatusEnum.DANG_THUC_HIEN.getId());
        model.addAttribute("countProcessingTasks", countProcessingTasks);
        System.out.println(countProcessingTasks);

        int countDoneTasks = taskService.countTasksByUserIdAndStatusId(userId, TaskStatusEnum.HOAN_THANH.getId());
        model.addAttribute("countDoneTasks", countDoneTasks);
        System.out.println(countDoneTasks);
        return "index";
    }
}
