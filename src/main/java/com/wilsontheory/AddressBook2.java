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

        public Person setId(int id) {
            this.id = id;
            return this;
        }

        private int id;

        public String getName() {
            return name;
        }

        public Person setName(String name) {
            this.name = name;
            return this;
        }

        private String name;

        public String getEmail() {
            return email;
        }

        public Person setEmail(String email) {
            this.email = email;
            return this;
        }

        private String email;

        public List<Num> getPhones() {
            return phones;
        }

        public Person addPhone(Num num) {
            this.phones.add(num);
            return this;
        }

        List<Num> phones = new ArrayList<>();

        class Num {
            public Num(String number, int type){
                this.number = number;
                this.type = type;
            }
            String number;
            int type;
        }
    }


}
