package com.example.shaunmesias.assignment_6_2.services.driver;

import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/11.
 */
public interface FindAllDriversService {
    Set<Driver> getAll();
}
