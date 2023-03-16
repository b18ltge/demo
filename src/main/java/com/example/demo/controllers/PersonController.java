package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.Person;
import com.example.demo.services.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people")
    public List<Person> all() {
        return personService.findAll();
    }

    @GetMapping("/people/{id}")
    public Person one(@PathVariable Long id) {
        return personService.one(id);
    }
}
