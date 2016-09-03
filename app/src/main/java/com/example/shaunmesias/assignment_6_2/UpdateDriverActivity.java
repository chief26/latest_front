package com.example.shaunmesias.assignment_6_2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.driver.impl.UpdateDriverServiceImpl;

public class UpdateDriverActivity extends AppCompatActivity {
    TextView txtName, txtEmail, txtloc, contact, owner, car;
    DriverRepository repository;

    DriverDetails driverDetails;
    DriverContact driverContact;
    Driver createEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_driver);

        repository = new DriverRepositoryImpl(App.getAppContext());

        txtName = ((TextView)findViewById(R.id.editText4));
        txtEmail = ((TextView)findViewById(R.id.editText5));
        txtloc = ((TextView)findViewById(R.id.editText6));
        contact = ((TextView)findViewById(R.id.editText7));
        owner = ((TextView)findViewById(R.id.editText8));
        car = ((TextView)findViewById(R.id.editText9));
    }

    public void ButtonSubmit(View v) throws Exception
    {
        Intent intent = new Intent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        Cursor cursor = repository.selectAll();

        for (int i = 0; i < cursor.getCount(); i++)
        {
            if (cursor.getCount() <= 0)
            {
                if(name.compareTo(cursor.getString(1)) == 0 && email.compareTo(cursor.getString(4)) == 0)
                {
                    driverDetails = new DriverDetails.Builder()
                            .carName(car.getText().toString())
                            .ownerName(owner.getText().toString())
                            .build();

                    driverContact = new DriverContact.Builder()
                            .contactValue(contact.getText().toString())
                            .build();

                    createEntity = new Driver.Builder()
                            .id(cursor.getLong(0))
                            .name(txtName.getText().toString())
                            .area(txtloc.getText().toString())
                            .email(txtEmail.getText().toString())
                            .getDriverContact(driverContact)
                            .getDriverDetails(driverDetails)
                            .build();

                    //repository.update(createEntity);
                    UpdateDriverServiceImpl service = UpdateDriverServiceImpl.getInstance();
                    service.updateDriver(App.getAppContext(), createEntity, driverContact, driverDetails);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            cursor.moveToNext();
        }
        startActivity(new Intent(UpdateDriverActivity.this, MainActivity.class));
    }
}
