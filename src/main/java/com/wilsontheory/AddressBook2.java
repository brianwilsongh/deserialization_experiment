package com.wilsontheory;

import java.util.ArrayList;
import java.util.List;

public class AddressBook2 {
    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    ArrayList<Person> people;


    public class Person {
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        private String email;

        public List<Num> getPhones() {
            return phones;
        }

        public void setPhones(List<Num> phones) {
            this.phones = phones;
        }

        List<Num> phones;

        class Num {
            String number;
            int type;
        }
    }


}
