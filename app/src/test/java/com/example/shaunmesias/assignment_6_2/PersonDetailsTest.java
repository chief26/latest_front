package com.example.shaunmesias.assignment_6_2;

import com.example.shaunmesias.assignment_6_2.domain.person.PersonDetails;
import com.example.shaunmesias.assignment_6_2.factories.person.PersonDetailsFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonDetailsTest {
    @Test
    public void testCreate() throws Exception {
        PersonDetails details = PersonDetailsFactory.getDetails("Mark", "Toyota", "Bakkie");
        Assert.assertEquals("Toyota", details.getCarName());
    }

    @Test
    public void testUpdate() throws Exception {
        PersonDetails details= PersonDetailsFactory.getDetails("Mark", "Toyota", "Bakkie");
        PersonDetails update = new PersonDetails.Builder()
                .copy(details)
                .carName("Chevrolet")
                .build();
        Assert.assertEquals("Chevrolet", update.getCarName());
        Assert.assertEquals("Bakkie", update.getCarType());
    }
}
