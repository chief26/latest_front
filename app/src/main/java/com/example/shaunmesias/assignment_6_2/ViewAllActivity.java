package com.example.shaunmesias.assignment_6_2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.factories.driver.DriverContactFactory;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.restapi.drivers.DriverApi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ViewAllActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<Driver> arrayList;

    DriverRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        repository = new DriverRepositoryImpl(App.getAppContext());
        arrayList = new ArrayList<>();

        final Cursor cursor = repository.selectAll();

        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.moveToNext()) {
                String name = cursor.getString(1);
                String location = cursor.getString(2);
                String email = cursor.getString(4);
                String contact = cursor.getString(5);
                String owner = cursor.getString(6);
                String car = cursor.getString(7);

                final DriverDetails driverDetails = new DriverDetails.Builder()
                        .carName(car)
                        .ownerName(owner)
                        .build();

                final DriverContact driverContact = new DriverContact.Builder()
                        .contactValue(contact)
                        .build();

                Driver addDriver = new Driver.Builder()
                        .name(name)
                        .area(location)
                        .email(email)
                        .getDriverContact(driverContact)
                        .getDriverDetails(driverDetails)
                        .build();
                arrayList.add(addDriver);
            }
        }

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(App.getAppContext(), arrayList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Driver d = new Driver.Builder()
                        .copy(arrayList.get(position))
                        .build();
                String number = d.getDriverContact().getContactValue();
                //Toast.makeText(getBaseContext(),number, Toast.LENGTH_LONG).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+number));
                if (ActivityCompat.checkSelfPermission(App.getAppContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(callIntent);
            }
        });
    }

    public void ButtonExit(View v) throws Exception
    {
        finish();
    }
}
