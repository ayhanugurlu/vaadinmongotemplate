package com.au.example;

import com.au.example.repository.UserRepository;
import com.au.example.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by Ayhan Ugurlu - (ayhan.ugurlu@odc.com.tr) on 12.09.2017.
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class TemplateApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {

        SpringApplication.run(TemplateApplication.class, args);


    }

    @PostConstruct
    public void init() {
        User user = userRepository.findByUserName("admin");

        if (user == null) {
            User admin = new User("admin", "ayhan", "ugurlu", "1");
            userRepository.save(admin);
        }

    }


}
