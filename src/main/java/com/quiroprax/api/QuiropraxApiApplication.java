package com.quiroprax.api;

import com.quiroprax.api.infra.configurations.DocumentedAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuiropraxApiApplication implements DocumentedAPI {

    public static void main(String[] args) {
        SpringApplication.run(QuiropraxApiApplication.class, args);
    }

}
