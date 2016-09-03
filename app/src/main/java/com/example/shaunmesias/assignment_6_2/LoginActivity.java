package com.example.shaunmesias.assignment_6_2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.registration.Register;
import com.example.shaunmesias.assignment_6_2.factories.register.RegisterFactory;
import com.example.shaunmesias.assignment_6_2.repository.register.RegisterRepository;
import com.example.shaunmesias.assignment_6_2.repository.register.impl.RegisterRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.register.impl.AddRegisterServiceImpl;

public class LoginActivity extends AppCompatActivity {

    TextView password, name, errorText;
    Spinner spinner;

    RegisterRepository repository;
    AddRegisterServiceImpl service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        repository = new RegisterRepositoryImpl(App.getAppContext());

        name = ((TextView)findViewById(R.id.editText2));
        password = ((TextView)findViewById(R.id.editText3));
        errorText = ((TextView)findViewById(R.id.textView12));
        spinner = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"Driver","Person"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    public void ButtonLogin(View v){
        final Cursor cursor = repository.selectAll();

        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.moveToNext()) {
                String c_name = cursor.getString(1);
                String c_password = cursor.getString(2);
                String c_cat = cursor.getString(3);
                if(c_name.compareTo(name.getText().toString()) == 0 &&
                        c_password.compareTo(password.getText().toString()) == 0)
                {
                    if(c_cat.compareToIgnoreCase("Person") == 0)
                    {
                        startActivity(new Intent(LoginActivity.this, ViewAllActivity.class));
                    }
                    else
                    {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                }
            }
        }

        errorText.setText("Invalid Login Details");
    }

    public void ButtonRegister(View v){

        Register reg = RegisterFactory.getRegister(name.getText().toString(), password.getText().toString(), String.valueOf(spinner.getSelectedItem()));
        service = AddRegisterServiceImpl.getInstance();
        service.addRegister(App.getAppContext(), reg);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(String.valueOf(spinner.getSelectedItem()).compareTo("Driver") == 0)
        {
            startActivity(new Intent(LoginActivity.this, ViewAllActivity.class));
        }
        else
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }
}
