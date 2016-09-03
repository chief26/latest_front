package com.example.shaunmesias.assignment_6_2.services.driver.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.restapi.drivers.DriverApi;
import com.example.shaunmesias.assignment_6_2.services.driver.AddDriverService;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/12.
 * This Intent service is used to add a driver to the repository
 * The user doesn't need to be notified when adding to repository was successful.
 */
public class AddDriverServiceImpl extends IntentService implements AddDriverService {

    public static final String ACTION_FOO = "com.example.shaunmesias.assignment_6_2.services.driver.impl.action.FOO";

    public static final String EXTRA_PARAM1 = "com.example.shaunmesias.assignment_6_2.services.driver.impl.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.shaunmesias.assignment_6_2.services.driver.impl.extra.PARAM2";
    public static final String EXTRA_PARAM3 = "com.example.shaunmesias.assignment_6_2.services.driver.impl.extra.PARAM3";

    DriverRepository repository;
//    DriverApi rest = new DriverApi();

    public static final String TAG = "com.example.shaunmesias.assignment_6_2.services.driver.impl";

    private static AddDriverServiceImpl service = null;

    public AddDriverServiceImpl(){
        super("AddDriverServiceImpl");
        repository = new DriverRepositoryImpl(App.getAppContext());
    }

    public static AddDriverServiceImpl getInstance() {
        if (service == null)
            service = new AddDriverServiceImpl();
        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        DriverDetails details = (DriverDetails)intent.getSerializableExtra(EXTRA_PARAM3);
        DriverDetails driverDetails = new DriverDetails.Builder()
                .carName(details.getCarName())
                .ownerName(details.getOwnerName())
                .build();

        DriverContact contact = (DriverContact)intent.getSerializableExtra(EXTRA_PARAM2);
        DriverContact driverContact = new DriverContact.Builder()
                .contactValue(contact.getContactValue())
                .build();

        Driver driver = (Driver)intent.getSerializableExtra(EXTRA_PARAM1);
            Driver newDriver = new Driver.Builder()
                    .area(driver.getArea())
                    .email(driver.getEmail())
                    .name(driver.getName())
                    .serverId(driver.getServerId())
                    .getDriverDetails(driverDetails)
                    .getDriverContact(driverContact)
                    .build();

        repository.save(newDriver);
       // Log.i(TAG, "The service has started....");
    }

    @Override
    public void AddDriver(Context context, Driver driver, DriverContact driverContact, DriverDetails driverDetails) {
      //  Log.i(TAG, "The AddDriver");
        Intent intent = new Intent(context, AddDriverServiceImpl.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, driver);
        intent.putExtra(EXTRA_PARAM3, driverDetails);
        intent.putExtra(EXTRA_PARAM2, driverContact);
        context.startService(intent);
    }

}
