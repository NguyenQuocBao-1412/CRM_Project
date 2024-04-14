package com.cyber.crm.controller;

import com.cyber.crm.constant.TaskStatusEnum;
import com.cyber.crm.entity.RolesEntity;
import com.cyber.crm.entity.TasksEntity;
import com.cyber.crm.entity.UsersEntity;
import com.cyber.crm.service.RoleService;
import com.cyber.crm.service.TaskService;
import com.cyber.crm.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public String getAllUsers(Model model) {

        List<UsersEntity> users = userService.getAllUser();
        model.addAttribute("users", users);

        return "user-table";
    }

    @GetMapping("/add")
    public String userAddView(Model model) {

        List<RolesEntity> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);

        return "user-add";
    }

    @PostMapping("/add")
    public String userAdd(Model model, @RequestParam String fullname, @RequestParam String email, @RequestParam String password,
                          @RequestParam String phoneNumber, @RequestParam int roleId){
        UsersEntity user = new UsersEntity();
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);

        RolesEntity role = new RolesEntity();
        role.setId(roleId);
        user.setRolesEntity(role);

        boolean isSuccess = userService.saveUser(user);
        model.addAttribute("isSuccess", isSuccess);

        List<RolesEntity> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);

        return "user-add";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {

        UsersEntity user = userService.getUserById(id);
        model.addAttribute("user", user);
        System.out.println(user.getRolesEntity().getId());

        List<RolesEntity> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);

        return "user-update";
    }

    @PostMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable int id, @RequestParam String fullname, @RequestParam String email, @RequestParam String password,
                          @RequestParam String phoneNumber, @RequestParam int roleId){
        UsersEntity user = new UsersEntity();
        user.setId(id);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);

        RolesEntity role = new RolesEntity();
        role.setId(roleId);
        user.setRolesEntity(role);

        model.addAttribute("user", user);

        boolean isSuccess = userService.saveUser(user);
        model.addAttribute("isSuccess", isSuccess);

        List<RolesEntity> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);

        return "user-update";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {

        userService.deleteUser(id);

        return "redirect:/user";
    }

    @GetMapping("/userdetails/{user_id}")
    public String userDetails( @PathVariable("user_id") int id,  Model model){

        UsersEntity user = userService.getUserById(id);
        model.addAttribute("usersEntity", user);

        List<TasksEntity> unfulfilledTasks = taskService.getTasksByUserIdAndStatusId(user.getId(), TaskStatusEnum.CHUA_THUC_HIEN.getId());
        model.addAttribute("unfulfilledTasks", unfulfilledTasks);

        List<TasksEntity> processingTasks = taskService.getTasksByUserIdAndStatusId(user.getId(), TaskStatusEnum.DANG_THUC_HIEN.getId());
        model.addAttribute("processingTasks", processingTasks);

        List<TasksEntity> doneTasks = taskService.getTasksByUserIdAndStatusId(user.getId(), TaskStatusEnum.HOAN_THANH.getId());
        model.addAttribute("doneTasks", doneTasks);

        int countUnfulfilledTasks = unfulfilledTasks.size();
        model.addAttribute("countUnfulfilledTasks", countUnfulfilledTasks);

        int countProcessingTasks = processingTasks.size();
        model.addAttribute("countProcessingTasks", countProcessingTasks);

        int countDoneTasks = doneTasks.size();
        model.addAttribute("countDoneTasks", countDoneTasks);

        return "user-details";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        userService.deleteSession(request);
        return "redirect:/login";
    }

}
