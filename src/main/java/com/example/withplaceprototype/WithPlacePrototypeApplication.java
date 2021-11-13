package com.example.withplaceprototype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@EnableJpaRepositories
@SpringBootApplication
public class WithPlacePrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WithPlacePrototypeApplication.class, args);
    }

}
