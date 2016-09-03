package com.example.shaunmesias.assignment_6_2.services.person;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.services.person.impl.FindAllPersonServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/11.
 */
public class FindAllPersonTest extends AndroidTestCase {
    private Boolean isBound;
    private FindAllPersonServiceImpl myService;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        myService = FindAllPersonServiceImpl.getInstance();
        Intent intent = new Intent(App.getAppContext(), FindAllPersonServiceImpl.class);
        App.getAppContext().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            FindAllPersonServiceImpl.MyLocalBinder binder = (FindAllPersonServiceImpl.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound=false;
        }
    };

    public void testService() throws Exception {
        Set<Person> person = myService.findAll();
        Assert.assertTrue(" READ ALL", person.size() > 0);

    }
}
