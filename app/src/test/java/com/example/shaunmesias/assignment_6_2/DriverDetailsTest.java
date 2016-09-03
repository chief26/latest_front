package com.example.shaunmesias.assignment_6_2;

import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.factories.driver.DriverDetailsFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class DriverDetailsTest {
    @Test
    public void testCreate() throws Exception {
        DriverDetails details = DriverDetailsFactory.getDetails("Mark", "Toyota", "Bakkie");
        Assert.assertEquals("Toyota", details.getCarName());
    }

    @Test
    public void testUpdate() throws Exception {
        DriverDetails details= DriverDetailsFactory.getDetails("Mark", "Toyota", "Bakkie");
        DriverDetails update = new DriverDetails.Builder()
                .copy(details)
                .carName("Chevrolet")
                .build();
        Assert.assertEquals("Chevrolet", update.getCarName());
        Assert.assertEquals("Bakkie", update.getCarType());
    }
}
