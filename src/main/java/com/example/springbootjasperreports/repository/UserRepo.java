package com.example.springbootjasperreports.repository;

import com.example.springbootjasperreports.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepo extends CrudRepository<User, UUID> {

    User findByEmail(String email);
}
