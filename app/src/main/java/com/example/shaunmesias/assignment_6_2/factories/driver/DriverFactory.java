package com.example.shaunmesias.assignment_6_2.factories.driver;

import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class DriverFactory {
    public static Driver getDriver(String serverId, String name, String area, String email, DriverContact driverContact, DriverDetails driverDetails)
    {
        return new Driver.Builder()
                .serverId(serverId)
                .name(name)
                .area(area)
                .email(email)
                .getDriverContact(driverContact)
                .getDriverDetails(driverDetails)
                .build();
    }
}
