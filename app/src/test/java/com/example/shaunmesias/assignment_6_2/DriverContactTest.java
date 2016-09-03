package com.example.shaunmesias.assignment_6_2;

import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.factories.driver.DriverContactFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class DriverContactTest {

    @Test
    public void testCreate() throws Exception {
        DriverContact contact = DriverContactFactory.getContact("1","0730006856");
        Assert.assertEquals("0730006856", contact.getContactValue());
    }

    @Test
    public void testUpdate() throws Exception {
        DriverContact contact= DriverContactFactory.getContact("2","1111");
        DriverContact update = new DriverContact.Builder()
                .copy(contact)
                .contactValue("5555")
                .build();
        Assert.assertEquals("5555", update.getContactValue());
    }
}
