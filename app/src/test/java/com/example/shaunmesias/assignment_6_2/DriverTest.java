package com.example.shaunmesias.assignment_6_2;

import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.factories.driver.DriverContactFactory;
import com.example.shaunmesias.assignment_6_2.factories.driver.DriverDetailsFactory;
import com.example.shaunmesias.assignment_6_2.factories.driver.DriverFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class DriverTest {
    @Test
    public void testCreate() throws Exception {
        DriverContact contact = DriverContactFactory.getContact("1", "0730006856");
        DriverDetails driverDetails = DriverDetailsFactory.getDetails("Mark", "Toyota", "Bakkie");

        Driver details = DriverFactory.getDriver("1234","Mark","Town","sme@h.com",contact,driverDetails);
        Assert.assertEquals("1234", details.getServerId());
        Assert.assertEquals("Toyota", details.getDriverDetails().getCarName());
        Assert.assertEquals("0730006856", details.getDriverContact().getContactValue());
    }

    @Test
    public void testUpdate() throws Exception {
        DriverContact contact = DriverContactFactory.getContact("1", "0730006856");
        DriverDetails driverDetails = DriverDetailsFactory.getDetails("Mark", "Toyota", "Bakkie");

        Driver details= DriverFactory.getDriver("1234", "Mark", "Town", "sme@h.com",contact ,driverDetails);
        Driver update = new Driver.Builder()
                .copy(details)
                .name("Steven")
                .build();
        Assert.assertEquals("Steven", update.getName());
        Assert.assertEquals("sme@h.com", update.getEmail());
        Assert.assertEquals("Toyota", update.getDriverDetails().getCarName());
        Assert.assertEquals("0730006856", update.getDriverContact().getContactValue());
    }
}
