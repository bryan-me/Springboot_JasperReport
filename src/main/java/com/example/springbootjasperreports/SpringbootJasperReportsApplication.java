package com.example.springbootjasperreports;

import com.example.springbootjasperreports.model.User;
import com.example.springbootjasperreports.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootJasperReportsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJasperReportsApplication.class, args);
    }

    @Bean
    public CommandLineRunner initialUserData(UserRepo repository){
        return (args) -> {

//            User user = new User("tim@gmail.com", "Tim", "1234", "ADMIN");
//            repository.save(user);
//
//            user = new User("tam@gmail.com", "Tam", "1234", "USER");
//            repository.save(user);
//
//            user = new User("tom@gmail.com", "Tom", "1234", "USER");
//            repository.save(user);
        };
    }
}
