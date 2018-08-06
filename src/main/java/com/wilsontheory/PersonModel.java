package com.wilsontheory;

import java.util.ArrayList;
import java.util.List;

public class PersonModel {
    public int getId() {
        return id;
    }

    public PersonModel setId(int id) {
        this.id = id;
        return this;
    }

    private int id;

    public String getName() {
        return name;
    }

    public PersonModel setName(String name) {
        this.name = name;
        return this;
    }

    private String name;

    public String getEmail() {
        return email;
    }

    public PersonModel setEmail(String email) {
        this.email = email;
        return this;
    }

    private String email;

    public List<Number> getPhones() {
        return phones;
    }

    public PersonModel addNumber(Number num) {
        this.phones.add(num);
        return this;
    }

    List<Number> phones = new ArrayList<>();

}
