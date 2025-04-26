package io.com.github.pantry.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PantryApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PantryApiApplication.class, args);
    }
}
