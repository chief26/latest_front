package com.example.shaunmesias.assignment_6_2;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.services.person.impl.FindPersonServiceImpl;

import java.lang.ref.SoftReference;

public class DetailsActivity extends AppCompatActivity {

    TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = ((TextView)findViewById(R.id.editText));
        email = ((TextView)findViewById(R.id.editText10));

    }


    public void ButtonUpdate(View v){

        Intent intent = new Intent(this, UpdateDriverActivity.class);
        String txtname = name.getText().toString();
        String txtemail = email.getText().toString();

        intent.putExtra("name", txtname);
        intent.putExtra("email", txtemail);
        startActivity(intent);
    }
}
