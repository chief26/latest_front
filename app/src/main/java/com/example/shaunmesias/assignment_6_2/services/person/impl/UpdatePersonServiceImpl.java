package com.example.shaunmesias.assignment_6_2.services.person.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.person.UpdatePersonService;

/**
 * Created by Shaun Mesias on 2016/05/12.
 * This Intent service is used to update a person in the repository
 * The user doesn't need to be notified when updating the repository was successful.
 */
public class UpdatePersonServiceImpl extends IntentService implements UpdatePersonService {
    public static final String ACTION_FOO = "com.example.shaunmesias.assignment_6_2.services.person.impl.action.FOO";

    public static final String EXTRA_PARAM1 = "com.example.shaunmesias.assignment_6_2.services.person.impl.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.shaunmesias.assignment_6_2.services.person.impl.extra.PARAM2";

    PersonRepository repository;

    public static final String TAG = "com.example.shaunmesias.assignment_6_2.services.person.impl";

    private static UpdatePersonServiceImpl service = null;

    public UpdatePersonServiceImpl(){
        super("UpdatePersonServiceImpl");
        repository = new PersonRepositoryImpl(App.getAppContext());
    }

    public static UpdatePersonServiceImpl getInstance() {
        if (service == null)
            service = new UpdatePersonServiceImpl();
        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Person person = (Person)intent.getSerializableExtra(EXTRA_PARAM1);
        Person newPerson = new Person.Builder()
                .copy(person)
                .build();

        repository.update(newPerson);
    }

    @Override
    public void updatePerson(Context context, Person person, PersonContact personContact) {
        Intent intent = new Intent(context, UpdatePersonServiceImpl.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, person);
        intent.putExtra(EXTRA_PARAM2, personContact);
        context.startService(intent);
    }
}
