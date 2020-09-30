package com.web.service;


import com.web.model.Role;
import com.web.model.User;
import com.web.repository.RoleRepo;
import com.web.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private RoleRepo roleRepo;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userRepo.getUserByNickname(nickname);
        if (user == null) {
            System.err.println("User not found");
        }
        return user;
    }

    @Override
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepo.getOne(id);
    }


    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public boolean saveUser(User user) {
        userRepo.save(user);
        return true;
    }

    @Override
    public void edit(User user) {
        userRepo.save(user);
    }

    @Override
    public User getUserByNickname(String nickname) {
        return userRepo.getUserByNickname(nickname);
    }

}