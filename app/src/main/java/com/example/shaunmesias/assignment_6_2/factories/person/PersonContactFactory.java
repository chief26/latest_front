package com.example.shaunmesias.assignment_6_2.factories.person;

import com.example.shaunmesias.assignment_6_2.conf.util.DomainState;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonContactFactory {
    public static PersonContact getContact(String value)
    {
        return new PersonContact.Builder()
                .contactValue(value)
                .build();
    }
}
