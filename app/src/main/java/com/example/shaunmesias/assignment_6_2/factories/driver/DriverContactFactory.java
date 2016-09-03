package com.example.shaunmesias.assignment_6_2.factories.driver;

import com.example.shaunmesias.assignment_6_2.conf.util.DomainState;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class DriverContactFactory {
    public static DriverContact getContact(String value)
    {
        return new DriverContact.Builder()
                .contactValue(value)
                .build();
    }
}
