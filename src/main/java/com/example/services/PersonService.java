package com.example.services;


import com.example.models.Person;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class PersonService {
    private ArrayList<Person> list = new ArrayList<>();
    
    public void add(String name, int age) {
        Person p = new Person(name, age);
        list.add(p);
    }
    
    public String getPeople() {
        return list.toString();
    }
}
