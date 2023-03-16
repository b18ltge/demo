package com.example.demo.services;

import com.example.demo.PersonNotFoundException;
import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final class CustomPerson extends Person {
        private final Integer age;

        private CustomPerson(Person person, int age) {
            super(person);
            this.age = age;
        }

        @Override
        @JsonIgnore
        public Long getId() {
            return super.getId();
        }
        @Override
        @JsonIgnore
        public LocalDate getBirthdate() {
            return super.getBirthdate();
        }

        public Integer getAge() {
            return this.age;
        }
    }

    private final PersonRepository repository;

    private static int getAge(LocalDate birthdate) {
        var today = LocalDate.now();
        if (birthdate.isAfter(today)) {
            throw new IllegalArgumentException("Cannot get age; [ age < 0 ]");
        }


        var result = (int) ChronoUnit.YEARS.between(birthdate, today);
        if (result > 120) {
            throw new IllegalArgumentException("Cannot get age; [ age > 120 ]");
        }
        return result;
    }

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll().stream().map(new Function<Person, Person>() {
            @Override
            public Person apply(Person person) {
                return new CustomPerson(person, getAge(person.getBirthdate()));
            }
        }).collect(Collectors.toList());
    }

    public Person one(Long id) {
        var person = repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        return new CustomPerson(person, getAge(person.getBirthdate()));
    }
}
