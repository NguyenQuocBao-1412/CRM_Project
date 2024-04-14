package com.cyber.crm.repositoty;

import com.cyber.crm.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
    List<UsersEntity> findByEmailAndPassword(String email, String password);
    UsersEntity getByEmail(String email);
}
