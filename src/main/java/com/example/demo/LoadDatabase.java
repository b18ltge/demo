package com.example.demo;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Person("Mike", "Brown", LocalDate.of(1995,6,15))));
            log.info("Preloading " + repository.save(new Person("Rick", "Black", LocalDate.of(1992, 3, 27))));
        };
    }
}