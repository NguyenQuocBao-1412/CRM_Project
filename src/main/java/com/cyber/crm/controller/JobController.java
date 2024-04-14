package com.cyber.crm.controller;

import com.cyber.crm.entity.JobsEntity;
import com.cyber.crm.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("")
    public String getAllJobs(Model model) {

        List<JobsEntity> jobs = jobService.getAllJobs();
        model.addAttribute("jobs", jobs);

        return "groupwork";
    }

    @GetMapping("/add")
    public String showAddJob() {

        return "groupwork-add";
    }

    @PostMapping("/add")
    public String addJob(Model model, @RequestParam String name, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        System.out.println("kiemtra name - " + name);
        System.out.println("kiemtra startDate - " + startDate);
        System.out.println("kiemtra endDate - " + endDate);
        JobsEntity job = new JobsEntity();
        job.setName(name);
        job.setStartDate(startDate);
        job.setEndDate(endDate);

        jobService.saveJob(job);

        model.addAttribute("isSuccess", true);

        return "groupwork-add";
    }

    @GetMapping("/update/{id}")
    public String showUpdateJob(Model model, @PathVariable int id) {

        JobsEntity job = jobService.getJobById(id);
        model.addAttribute("job", job);

        return "groupwork-update";
    }

    @PostMapping("/update/{id}")
    public String updateJob(Model model,@PathVariable int id, @RequestParam String name, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        JobsEntity job = new JobsEntity();
        job.setId(id);
        job.setName(name);
        job.setStartDate(startDate);
        job.setEndDate(endDate);

        jobService.saveJob(job);

        model.addAttribute("job", job);
        model.addAttribute("isSuccess", true);

        return "groupwork-update";
    }

    @PostMapping("/delete/{id}")
    public String deleteRole(@PathVariable int id) {

        jobService.deleteJobById(id);

        return "redirect:/job";
    }


}
