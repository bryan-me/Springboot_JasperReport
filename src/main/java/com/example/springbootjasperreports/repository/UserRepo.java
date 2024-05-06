package com.example.springbootjasperreports.repository;

import com.example.springbootjasperreports.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import static org.springframework.orm.hibernate5.SessionFactoryUtils.getDataSource;

public interface UserRepo extends JpaRepository<User, UUID> {

    User findByEmail(String email);
}




