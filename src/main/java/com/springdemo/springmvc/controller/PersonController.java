package com.springdemo.springmvc.controller;


import com.springdemo.springmvc.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class PersonController {

    List<Person> personList= Arrays.asList(new Person(1,"Shinji", "SE"),
            new Person(2,"pulkit","SSE"));


    
    @GetMapping("/personsRest")
    @ResponseBody
    public List<Person> getPersons(){
        return personList;
    }
}
