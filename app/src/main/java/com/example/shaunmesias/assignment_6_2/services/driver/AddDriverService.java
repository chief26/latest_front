package com.example.shaunmesias.assignment_6_2.services.driver;

import android.content.Context;

import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;

/**
 * Created by Shaun Mesias on 2016/05/12.
 */
public interface AddDriverService {
    void AddDriver(Context context, Driver driver, DriverContact driverContact, DriverDetails driverDetails);
}
