package com.cyber.crm.service;

import com.cyber.crm.constant.TaskStatusEnum;
import com.cyber.crm.entity.JobsEntity;
import com.cyber.crm.entity.StatusEntity;
import com.cyber.crm.entity.TasksEntity;
import com.cyber.crm.entity.UsersEntity;
import com.cyber.crm.repositoty.TaskRepository;
import com.cyber.crm.request.TaskAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TasksEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    public void saveTask(TaskAddRequest request) throws ParseException {
        TasksEntity tasksEntity = new TasksEntity();
        tasksEntity.setName(request.getTaskName());
        tasksEntity.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getStartDate().toString()));
        tasksEntity.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getEndDate().toString()));

        JobsEntity jobsEntity = new JobsEntity();
        jobsEntity.setId(request.getJobId());
        tasksEntity.setJobsEntity(jobsEntity);

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(request.getUserId());
        tasksEntity.setUsersEntity(usersEntity);

        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setId(request.getStatusId());
        tasksEntity.setStatusEntity(statusEntity);

        taskRepository.save(tasksEntity);
        //return taskRepository.save(tasksEntity);
    }

    public List<TasksEntity>findByUsersEntity(UsersEntity usersEntity) {
        List<TasksEntity> tasksEntities = taskRepository.findByUsersEntity(usersEntity);
        return tasksEntities;
    }

    public List<TasksEntity> getTasksByUserIdAndStatusId(int userId, int statusId){
        return taskRepository.getTasksByUserIdAndStatusId(userId, statusId);
    }

    public int countTasksByUserIdAndStatusId(int userId, int statusId){
        return taskRepository.countTasksByUserIdAndStatusId(userId, statusId);
    }
}
