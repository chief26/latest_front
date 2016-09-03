package com.example.shaunmesias.assignment_6_2.factories.person;

import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonDetails;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonFactory {
    public static Person getPerson(String serverId, String name, String location, String email, PersonContact personContact)
    {
        return new Person.Builder()
                .serverId(serverId)
                .name(name)
                .location(location)
                .email(email)
                .getPersonContact(personContact)
                .build();
    }
}
