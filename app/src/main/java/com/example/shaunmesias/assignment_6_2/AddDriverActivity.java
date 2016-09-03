package com.example.shaunmesias.assignment_6_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.factories.driver.DriverFactory;
import com.example.shaunmesias.assignment_6_2.services.driver.impl.AddDriverServiceImpl;

public class AddDriverActivity extends AppCompatActivity {

    static String id, name, loc, email, contact, owner, car;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
    }

    public void addPersonClick(View v) throws Exception{
        name = ((EditText)findViewById(R.id.txtName)).getText().toString();
        loc = ((EditText)findViewById(R.id.txtLocation)).getText().toString();
        email = ((EditText)findViewById(R.id.txtEmail)).getText().toString();
        contact = ((EditText)findViewById(R.id.txtContact)).getText().toString();
        owner = ((EditText)findViewById(R.id.txtOwner)).getText().toString();
        car = ((EditText)findViewById(R.id.txtCar)).getText().toString();

        final DriverDetails driverDetails = new DriverDetails.Builder()
                .carName(car)
                .ownerName(owner)
                .build();

        final DriverContact driverContact = new DriverContact.Builder()
                .contactValue(contact)
                .build();

        Driver driver = DriverFactory.getDriver("null", name, loc, email, driverContact, driverDetails);
        AddDriverServiceImpl service = AddDriverServiceImpl.getInstance();
        service.AddDriver(App.getAppContext(), driver, driverContact,driverDetails);
        Thread.sleep(1000);

        startActivity(new Intent(AddDriverActivity.this, ViewAllActivity.class));
    }
}
