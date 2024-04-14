package com.cyber.crm.repositoty;

import com.cyber.crm.entity.StatusEntity;
import com.cyber.crm.entity.TasksEntity;
import com.cyber.crm.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TasksEntity, Integer> {
    List<TasksEntity> findByUsersEntity(UsersEntity usersEntity);
    List<TasksEntity> findByStatusEntity(StatusEntity status);
    @Query("SELECT t FROM tasks t WHERE t.usersEntity.id = ?1 AND t.statusEntity.id = ?2")
    List<TasksEntity> getTasksByUserIdAndStatusId(int userId, int statusId);

    @Query("SELECT count(*) FROM tasks t WHERE t.usersEntity.id = ?1 AND t.statusEntity.id = ?2")
    int countTasksByUserIdAndStatusId(int userId, int statusId);
}
