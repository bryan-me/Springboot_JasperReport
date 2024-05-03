package com.example.springbootjasperreports.service;

import com.example.springbootjasperreports.model.User;
import com.example.springbootjasperreports.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    public final UserRepo userRepo;
    //Get all users
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User saveUser(User user){
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    public User updateUser(UUID id, User user){
        Optional<User> existingUser = userRepo.findById(id);

        User updatedUser = userRepo.save(user);

        return updatedUser;
    }

    public void deleteUserById(UUID id){
        userRepo.deleteById(id);
    }
}
