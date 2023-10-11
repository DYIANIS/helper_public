package com.shtura.helper;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.shtura.helper")
public class HelperApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ReminderApplication.class, args);
        
        SpringApplication app = new SpringApplication(HelperApplication.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8443"));

        app.run(args);
    }
}
