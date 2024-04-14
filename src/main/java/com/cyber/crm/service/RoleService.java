package com.cyber.crm.service;

import com.cyber.crm.entity.RolesEntity;
import com.cyber.crm.repositoty.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RolesRepository rolesRepository;
    public List<RolesEntity> getAllRole() {
        return rolesRepository.findAll();
    }

    public boolean insertRole(String roleName, String desc) {
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setName(roleName);
        rolesEntity.setDescription(desc);
        boolean isSuccess = false;

        try{
            rolesRepository.save(rolesEntity);
            isSuccess = true;
        } catch (Exception e) {
            isSuccess = false;
        }

        return isSuccess;
    }

    public RolesEntity getRoleById(int id) {
        Optional<RolesEntity> rolesEntity = rolesRepository.findById(id);
        RolesEntity role = new RolesEntity();

        if(rolesEntity.isPresent()) {
            role = rolesEntity.get();
        }

        return role;
    }

    public boolean updateRole(int id, String roleName, String desc) {
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setId(id);
        rolesEntity.setName(roleName);
        rolesEntity.setDescription(desc);

        boolean isSuccess = false;

        try{
            rolesRepository.save(rolesEntity);
            isSuccess = true;
        } catch (Exception e) {
            isSuccess = false;
        }

        return isSuccess;
    }
}
