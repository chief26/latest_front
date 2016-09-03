package com.example.shaunmesias.assignment_6_2.services.driver;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.factories.driver.DriverFactory;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.driver.impl.UpdateDriverServiceImpl;

import junit.framework.Assert;

/**
 * Created by Shaun Mesias on 2016/05/12.
 */
public class UpdateDriverTest extends AndroidTestCase {
    public void testUpdateDriver() throws Exception {
        final DriverDetails driverDetails = new DriverDetails.Builder()
                .carName("nissan")
                .ownerName("Jack")
                .build();

        final DriverContact driverContact = new DriverContact.Builder()
                .contactValue("0730006856")
                .build();

        Driver driver = DriverFactory.getDriver("1234", "black", "Town", "sss.vom",driverContact, driverDetails);
        UpdateDriverServiceImpl service = UpdateDriverServiceImpl.getInstance();

        DriverRepository database = new DriverRepositoryImpl(this.getContext());

        Driver savedDriver = database.save(driver);
        long id = savedDriver.getId();
        Driver entity = database.findById(id);

        Driver updateDriver = new Driver.Builder()
                .copy(entity)
                .name("white")
                .build();

        service.updateDriver(App.getAppContext(), updateDriver,driverContact,driverDetails);
        Thread.sleep(1000);

        Driver updatedDriver = database.findById(id);
        Assert.assertEquals("white", updatedDriver.getName());

    }
}
