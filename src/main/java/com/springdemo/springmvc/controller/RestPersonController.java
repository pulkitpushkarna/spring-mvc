package com.springdemo.springmvc.controller;

import com.springdemo.springmvc.entity.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RestPersonController {


    List<Person> personList= Arrays.asList(new Person(1,"Shinji", "SE"),
            new Person(2,"pulkit","SSE"),new Person(3,"vishal","ATL"));


    @RequestMapping(value = "/persons",method = RequestMethod.GET)
    public List<Person> getPersons(){
        return personList;
    }

    @RequestMapping(value = "/persons/{id}",method = RequestMethod.GET)
    public Person getPerson(@PathVariable int id){
        Optional<Person> personOptional=personList.stream().filter(p->p.getEmpId()==id).findFirst();
        Person person = personOptional.isPresent()? personOptional.get():null;
        return person;
    }

    @RequestMapping(value = "/searchPerson",method = RequestMethod.GET)
    public List<Person> searchPerson(@RequestParam String designation){

        return personList.stream().filter(person -> person.getDesignation().equals(designation))
                .collect(Collectors.toList());
    }


}
