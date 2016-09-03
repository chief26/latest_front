package com.example.shaunmesias.assignment_6_2.services.driver.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.driver.DeleteDriverService;

/**
 * Created by Shaun Mesias on 2016/05/12.
 * This bound service is used to delete drivers.
 * when a driver is deleted successfully the user will be notified.
 */
public class DeleteDriverServiceImpl extends Service implements DeleteDriverService {
    private static DeleteDriverServiceImpl service = null;

    public static DeleteDriverServiceImpl getInstance() {
        if (service == null)
            service = new DeleteDriverServiceImpl();
        return service;
    }

    private final IBinder localBinder = new MyLocalBinder();

    public class MyLocalBinder extends Binder {
        public DeleteDriverServiceImpl getService(){
            return DeleteDriverServiceImpl.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public Driver deleteDriver(Driver driver) {
        DriverRepository database = new DriverRepositoryImpl(App.getAppContext());
        Driver person = database.delete(driver);
        return person;
    }
}
