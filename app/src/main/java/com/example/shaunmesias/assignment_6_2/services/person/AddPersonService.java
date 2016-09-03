package com.example.shaunmesias.assignment_6_2.services.person;

import android.content.Context;

import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;

/**
 * Created by Shaun Mesias on 2016/05/12.
 */
public interface AddPersonService {
    void AddPerson(Context context, Person person, PersonContact personContact);
}
