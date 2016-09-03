package com.example.shaunmesias.assignment_6_2.services.person;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.person.impl.FindPersonServiceImpl;

import junit.framework.Assert;

/**
 * Created by Shaun Mesias on 2016/05/11.
 */
public class FindPersonTest extends AndroidTestCase {
    private Boolean isBound;
    private FindPersonServiceImpl myService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        myService = FindPersonServiceImpl.getInstance();
        Intent intent = new Intent(App.getAppContext(), FindPersonServiceImpl.class);
        App.getAppContext().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            FindPersonServiceImpl.MyLocalBinder binder = (FindPersonServiceImpl.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };

    public void testService() throws Exception {
        PersonRepository repository = new PersonRepositoryImpl(this.getContext());
        //CREATE DRIVER FACTORIES
        PersonContact personContact = new PersonContact.Builder()
                .contactValue("0731116856")
                .build();
        Person createEntity = new Person.Builder()
                .serverId("1234")
                .name("Bo")
                .location("Durban")
                .email("was@b.com")
                .getPersonContact(personContact)
                .build();
        repository.save(createEntity);
        Person person = myService.findPerson("1");
        Assert.assertEquals("Bo", createEntity.getName());
    }
}
