package com.example.shaunmesias.assignment_6_2.services.driver.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.driver.FindDriverService;

/**
 * Created by Shaun Mesias on 2016/05/11.
 * This bound service is used to search for individual drivers.
 * if the driver is found the user interface is updated and the user is notified.
 */
public class FindDriverServiceImpl extends Service implements FindDriverService {

    private static FindDriverServiceImpl service = null;

    public static FindDriverServiceImpl getInstance() {
        if (service == null)
            service = new FindDriverServiceImpl();
        return service;
    }

    private final IBinder localBinder = new MyLocalBinder();

    public class MyLocalBinder extends Binder {
        public FindDriverServiceImpl getService(){
            return FindDriverServiceImpl.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public Driver findDriver(String id) {
        DriverRepository database = new DriverRepositoryImpl(App.getAppContext());
        long newId = new Long(id);
        Driver person = database.findById(newId);
        return person;
    }
}
