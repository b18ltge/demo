package com.example.demo.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthdate;

    public Person() {}

    public Person(String name, String surname, LocalDate birthdate) {

        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
    }

    public Person(Person person) {
        this.id = person.id;
        this.name = person.name;
        this.surname = person.surname;
        this.birthdate = person.birthdate;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String role) {
        this.surname = role;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Person))
            return false;

        Person person = (Person) o;
        return Objects.equals(this.id, person.id) && Objects.equals(this.name, person.name)
                && Objects.equals(this.surname, person.surname) && Objects.equals(this.birthdate, person.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.surname, this.birthdate);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + this.id + ", name='" + this.name + '\'' + ", surname='" + this.surname + '\'' + ", birthdate='" + this.birthdate + '}';
    }
}
