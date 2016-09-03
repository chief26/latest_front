package com.example.shaunmesias.assignment_6_2.services.person;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.factories.person.PersonFactory;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.person.impl.AddPersonServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/12.
 */
public class AddPersonTest extends AndroidTestCase {
    public void testPersonService() throws Exception {
        PersonContact personContact = new PersonContact.Builder()
                .contactValue("0731116856")
                .build();
        Person person = PersonFactory.getPerson("1234", "black", "Town", "sss.vom",personContact);
        AddPersonServiceImpl service = AddPersonServiceImpl.getInstance();
        PersonRepository database = new PersonRepositoryImpl(this.getContext());

        service.AddPerson(App.getAppContext(), person, personContact);
        Thread.sleep(1000);

        //READ ALL
        /*Set<Person> persons = database.findAll();
        Assert.assertTrue(" READ ALL", persons.size() > 0);*/

    }
}
