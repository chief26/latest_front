package com.example.shaunmesias.assignment_6_2.services.person.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.person.FindPersonService;

/**
 * Created by Shaun Mesias on 2016/05/11.
 * This bound service is used to search for individual Person.
 * if the person is found the user interface is updated and the user is notified.
 */
public class FindPersonServiceImpl extends Service implements FindPersonService{
    public static final String TAG = "com.example.shaunmesias.assignment_6_2.services.person.impl";
    private static FindPersonServiceImpl service = null;

    public static FindPersonServiceImpl getInstance() {
        if (service == null)
            service = new FindPersonServiceImpl();
        return service;
    }

    private final IBinder localBinder = new MyLocalBinder();

    public class MyLocalBinder extends Binder {
        public FindPersonServiceImpl getService(){
            return FindPersonServiceImpl.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public Person findPerson(String id) {
        Log.i(TAG, "The findPerson(String id)");
        PersonRepository database = new PersonRepositoryImpl(App.getAppContext());
        long newId = new Long(id);
        Person person = database.findById(newId);
        return person;
    }
}
