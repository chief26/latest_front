package com.example.shaunmesias.assignment_6_2;

import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonDetails;
import com.example.shaunmesias.assignment_6_2.factories.person.PersonContactFactory;
import com.example.shaunmesias.assignment_6_2.factories.person.PersonDetailsFactory;
import com.example.shaunmesias.assignment_6_2.factories.person.PersonFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonTest {
    @Test
    public void testCreate() throws Exception {
        PersonContact contact = PersonContactFactory.getContact("0730006856");

        Person pdetails = PersonFactory.getPerson("1234", "messi", "CapeTown", "se@r.com", contact);
        Assert.assertEquals("messi", pdetails.getName());
        Assert.assertEquals("0730006856", pdetails.getPersonContact().getContactValue());
    }

    @Test
    public void testUpdate() throws Exception {
        PersonContact contact = PersonContactFactory.getContact("0730006856");
        PersonContact pcontact = PersonContactFactory.getContact("0731116856");
        Person pdetails= PersonFactory.getPerson("1234", "messi", "CapeTown", "se@r.com", contact);
        Person update = new Person.Builder()
                .copy(pdetails)
                .location("Durban")
                .getPersonContact(pcontact)
                .build();
        Assert.assertEquals("Durban", update.getLocation());
        Assert.assertEquals("messi", update.getName());
        Assert.assertEquals("0731116856", update.getPersonContact().getContactValue());
    }
}
