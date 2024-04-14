package com.cyber.crm.controller;

import com.cyber.crm.entity.RolesEntity;
import com.cyber.crm.repositoty.RolesRepository;
import com.cyber.crm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    public String add(Model model) {
        return "role-add";
    }

    @PostMapping("/add")
    public String processAdd(Model model, @RequestParam String roleName, @RequestParam String description) {
        System.out.println("Kiemtra " + roleName + " - " + description);

        model.addAttribute("isSuccess", roleService.insertRole(roleName, description));

        return "role-add";
    }

    @GetMapping("/show")
    public String showRole(Model model) {

        List<RolesEntity> rolesEntityList = roleService.getAllRole();
        model.addAttribute("roleList", rolesEntityList);

        return "role-table";
    }

    @PostMapping("/delete/{id}")
    public String deleteRole(@PathVariable int id) {

        System.out.println("Delete Id: kiem tra id - " + id);
        rolesRepository.deleteById(id);

        return "redirect:/role/show";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        System.out.println("ShowUpdate Id: kiem tra id - " + id);

        RolesEntity role = roleService.getRoleById(id);
        model.addAttribute("role", role);

        return "role-update";
    }

    @PostMapping("/update/{id}")
    public  String updateProcess(Model model, @PathVariable int id, @RequestParam(required = false) String roleName,
                                 @RequestParam(required = false) String description) {

        model.addAttribute("isSuccess", roleService.updateRole(id, roleName, description));

        RolesEntity role = roleService.getRoleById(id);
        model.addAttribute("role", role);

        return "role-update";
    }
}
