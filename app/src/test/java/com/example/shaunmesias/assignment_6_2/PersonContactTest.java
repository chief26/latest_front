package com.example.shaunmesias.assignment_6_2;

import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.factories.person.PersonContactFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonContactTest {
    @Test
    public void testCreate() throws Exception {
        PersonContact contact = PersonContactFactory.getContact("0730006856");
        Assert.assertEquals("0730006856", contact.getContactValue());
    }

    @Test
    public void testUpdate() throws Exception {
        PersonContact contact= PersonContactFactory.getContact("1111");
        PersonContact update = new PersonContact.Builder()
                .copy(contact)
                .contactValue("5555")
                .build();
        Assert.assertEquals("5555", update.getContactValue());
    }
}
