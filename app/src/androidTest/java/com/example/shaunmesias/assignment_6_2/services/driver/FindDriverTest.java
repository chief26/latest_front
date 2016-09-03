package com.example.shaunmesias.assignment_6_2.services.driver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.driver.impl.FindDriverServiceImpl;

import junit.framework.Assert;

/**
 * Created by Shaun Mesias on 2016/05/11.
 */
public class FindDriverTest extends AndroidTestCase{
    private Boolean isBound;
    private FindDriverServiceImpl myService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        myService = FindDriverServiceImpl.getInstance();
        Intent intent = new Intent(App.getAppContext(), FindDriverServiceImpl.class);
        App.getAppContext().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            FindDriverServiceImpl.MyLocalBinder binder = (FindDriverServiceImpl.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };

    public void testService() throws Exception {
        DriverRepository repository = new DriverRepositoryImpl(this.getContext());
        //CREATE DRIVER FACTORIES
        final DriverDetails driverDetails = new DriverDetails.Builder()
                .carName("nissan")
                .ownerName("Jack")
                .build();

        final DriverContact driverContact = new DriverContact.Builder()
                .contactValue("0730006856")
                .build();

        Driver createEntity = new Driver.Builder()
                .serverId("1234")
                .name("Jack")
                .area("Durban")
                .email("was@b.com")
                .getDriverContact(driverContact)
                .getDriverDetails(driverDetails)
                .build();
        repository.save(createEntity);
        Driver driver = myService.findDriver("1");
        Assert.assertEquals("Jack", createEntity.getName());
    }
}
