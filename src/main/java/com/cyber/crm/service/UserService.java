package com.cyber.crm.service;

import com.cyber.crm.entity.RolesEntity;
import com.cyber.crm.entity.UsersEntity;
import com.cyber.crm.repositoty.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    public List<UsersEntity> getAllUser() {
        List<UsersEntity> usersEntityList = usersRepository.findAll();

        return usersEntityList;
    }

    public UsersEntity getUserById(int id) {
        Optional<UsersEntity> optionalUser = usersRepository.findById(id);

        UsersEntity user = new UsersEntity();

        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        }

        return user;
    }

    public boolean saveUser(UsersEntity usersEntity) {

        boolean isSuccess = false;

        try{
            usersRepository.save(usersEntity);
            isSuccess = true;
        } catch (Exception e) {
            isSuccess = false;
        }

        return isSuccess;
    }

    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }

    public void deleteSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
