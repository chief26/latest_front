package com.example.shaunmesias.assignment_6_2.services.person;

import com.example.shaunmesias.assignment_6_2.domain.person.Person;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/11.
 */
public interface FindAllPersonService {
    Set<Person> findAll();
}
