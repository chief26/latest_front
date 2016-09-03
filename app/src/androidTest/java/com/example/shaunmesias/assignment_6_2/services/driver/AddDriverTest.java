package com.example.shaunmesias.assignment_6_2.services.driver;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.factories.driver.DriverFactory;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.driver.impl.AddDriverServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/12.
 */
public class AddDriverTest extends AndroidTestCase {
    //DriverRepositoryImpl database;


    public void testDriverService() throws Exception {
        final DriverDetails driverDetails = new DriverDetails.Builder()
                .carName("nissan")
                .ownerName("rererw")
                .build();

        final DriverContact driverContact = new DriverContact.Builder()
                .contactValue("0730003434")
                .build();
        Driver driver = DriverFactory.getDriver("1234323", "black", "Towfffsn", "ssffsss.vom",driverContact,driverDetails);
        AddDriverServiceImpl service = AddDriverServiceImpl.getInstance();
        DriverRepository database = new DriverRepositoryImpl(this.getContext());

        service.AddDriver(App.getAppContext(), driver,driverContact, driverDetails);
        Thread.sleep(1000);

       //READ ALL
       /* Set<Driver> drivers = database.findAll();
        Assert.assertTrue(" READ ALL", drivers.size() > 0);*/
    }
}
