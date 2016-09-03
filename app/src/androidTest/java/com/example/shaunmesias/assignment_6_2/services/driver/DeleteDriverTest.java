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
import com.example.shaunmesias.assignment_6_2.services.driver.impl.DeleteDriverServiceImpl;

import junit.framework.Assert;

/**
 * Created by Shaun Mesias on 2016/05/12.
 */
public class DeleteDriverTest extends AndroidTestCase {
    private Boolean isBound;
    private DeleteDriverServiceImpl myService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        myService = DeleteDriverServiceImpl.getInstance();
        Intent intent = new Intent(App.getAppContext(), DeleteDriverServiceImpl.class);
        App.getAppContext().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            DeleteDriverServiceImpl.MyLocalBinder binder = (DeleteDriverServiceImpl.MyLocalBinder) service;
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
                .getDriverDetails(driverDetails)
                .getDriverContact(driverContact)
                .build();
        Driver driver = repository.save(createEntity);

        long id = driver.getId();

        myService.deleteDriver(driver);
        Driver driver1 = repository.findById(id);
        Assert.assertNull("DELETE DRIVER", driver1);

    }
}
