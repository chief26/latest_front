package com.example.shaunmesias.assignment_6_2;

import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        DriverTest.class,
        DriverContactTest.class,
        DriverDetailsTest.class,
        PersonTest.class,
        PersonContactTest.class,
        PersonDetailsTest.class,
        RegisterTest.class
        })

public class TestSuite {
}
