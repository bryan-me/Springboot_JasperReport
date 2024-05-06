package com.example.springbootjasperreports.controller;

import com.example.springbootjasperreports.model.User;
import com.example.springbootjasperreports.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
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

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.saveUser(user));
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().body("Deleted Employee Successfully");
    }

//    @GetMapping("/jasper/{id}")
//    public void generateReport(HttpServletResponse response, @PathVariable UUID id)throws IOException, JRException, SQLException {
//    response.setContentType("application/x-download");
//    response.setHeader ("Content-Disposition", String.format ("attachment; filename=\"user.pdf\"")) ;
//    OutputStream out = response.getOutputStream();
//    userService.exportReport(id, out);
//    }

    @GetMapping("/report")
    public String userReport() {
        return userService.generateReport();
    }
}
