package com.springdemo.springmvc.controller;

import com.springdemo.springmvc.entity.Person;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RestPersonController {

    List<Person> personList = new ArrayList<>();


    @PostConstruct
    public void init() {
        personList.add(new Person(1, "shinji", "SE"));
        personList.add(new Person(2, "pulkit", "SSE"));
        personList.add(new Person(3, "vishal", "ATL"));

    }


    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Person> getPersons() {
        return personList;
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable int id) {
        Optional<Person> personOptional = personList.stream().filter(p -> p.getEmpId() == id).findFirst();
        Person person = personOptional.isPresent() ? personOptional.get() : null;
        return person;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public Person savePerson(@RequestBody Person person) {
        personList.add(person);
        return person;
    }

//    @RequestMapping(value = "/persons{id}", method = RequestMethod.PUT)

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    public Person deletePerson(@PathVariable int id){
        Person person = personList.stream().filter(p->id==p.getEmpId()).findFirst().get();
         personList = personList.stream().filter(p -> id!=p.getEmpId()).collect(Collectors.toList());
         return person;

    }

    @RequestMapping(value = "/searchPerson", method = RequestMethod.GET)
    public List<Person> searchPerson(@RequestParam String designation, @RequestParam String name) {
        System.out.println(designation);
        System.out.println(name);
        return personList.stream().filter(person -> person.getDesignation().equals(designation) || person.getName().equals(name))
                .collect(Collectors.toList());
    }


}
