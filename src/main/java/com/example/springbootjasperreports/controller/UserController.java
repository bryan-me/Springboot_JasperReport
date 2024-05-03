package com.example.springbootjasperreports.controller;

import com.example.springbootjasperreports.model.User;
import com.example.springbootjasperreports.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(User user){
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user){
        return ResponseEntity.ok().body(userService.updateUser(id, user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletetUser(@PathVariable UUID id){
        return ResponseEntity.ok().body("User successfully deleted.");
    }
}
