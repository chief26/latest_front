package com.example.shaunmesias.assignment_6_2.services.driver.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.driver.FindAllDriversService;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/11.
 * This bound service is used to retrieve all drivers.
 * the user interface is updated for example in a listView and the user can view all drivers.
 */
public class FindAllDriversServiceImpl extends Service implements FindAllDriversService {

    private final IBinder localBinder = new MyLocalBinder();
    private static FindAllDriversServiceImpl service = null;

    public static FindAllDriversServiceImpl getInstance() {
        if (service == null)
            service = new FindAllDriversServiceImpl();
        return service;
    }

    public class MyLocalBinder extends Binder {
        public FindAllDriversServiceImpl getService(){
            return FindAllDriversServiceImpl.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public Set<Driver> getAll() {
        DriverRepository database = new DriverRepositoryImpl(App.getAppContext());
        Set<Driver> drivers = database.findAll();
        return drivers;
    }
}
