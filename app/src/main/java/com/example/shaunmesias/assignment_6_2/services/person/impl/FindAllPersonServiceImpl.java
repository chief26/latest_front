package com.example.shaunmesias.assignment_6_2.services.person.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.person.FindAllPersonService;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/11.
 * This bound service is used to retrieve all Persons.
 * the user interface is updated for example in a listView and the user can view all Persons.
 */
public class FindAllPersonServiceImpl extends Service implements FindAllPersonService {

    private final IBinder localBinder = new MyLocalBinder();
    private static FindAllPersonServiceImpl service = null;

    public static FindAllPersonServiceImpl getInstance() {
        if (service == null)
            service = new FindAllPersonServiceImpl();
        return service;
    }

    public class MyLocalBinder extends Binder {
        public FindAllPersonServiceImpl getService(){
            return FindAllPersonServiceImpl.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public Set<Person> findAll() {
        PersonRepository database = new PersonRepositoryImpl(App.getAppContext());
        Set<Person> person = database.findAll();
        return person;
    }
}
