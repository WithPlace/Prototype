package com.example.withplaceprototype;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class WithPlacePrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WithPlacePrototypeApplication.class, args);
        log.info("Hi");
    }

}
