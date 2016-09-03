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
import com.example.shaunmesias.assignment_6_2.services.person.AddPersonService;

/**
 * Created by Shaun Mesias on 2016/05/12.
 * This Intent service is used to add a person to the repository
 * The user doesn't need to be notified when adding to repository was successful.
 */
public class AddPersonServiceImpl extends IntentService implements AddPersonService {
    public static final String ACTION_FOO = "com.example.shaunmesias.assignment_6_2.services.person.impl.action.FOO";

    public static final String EXTRA_PARAM1 = "com.example.shaunmesias.assignment_6_2.services.person.impl.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.shaunmesias.assignment_6_2.services.person.impl.extra.PARAM2";

    PersonRepository repository;

    public static final String TAG = "com.example.shaunmesias.assignment_6_2.services.person.impl";

    private static AddPersonServiceImpl service = null;

    public AddPersonServiceImpl(){
        super("AddPersonServiceImpl");
        repository = new PersonRepositoryImpl(App.getAppContext());
    }

    public static AddPersonServiceImpl getInstance() {
        if (service == null)
            service = new AddPersonServiceImpl();
        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        PersonContact personContact = (PersonContact) intent.getSerializableExtra(EXTRA_PARAM2);
        PersonContact newContact = new PersonContact.Builder()
                .contactValue(personContact.getContactValue())
                .build();

        Person person = (Person) intent.getSerializableExtra(EXTRA_PARAM1);
            Person newPerson = new Person.Builder()
                    .location(person.getLocation())
                    .email(person.getEmail())
                    .name(person.getName())
                    .serverId(person.getServerId())
                    .getPersonContact(newContact)
                    .build();

            repository.save(newPerson);
        //Log.i(TAG, "The service has started...........");
    }

    @Override
    public void AddPerson(Context context, Person person, PersonContact personContact) {
        //Log.i(TAG, "The AddPerson");
        Intent intent = new Intent(context, AddPersonServiceImpl.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, person);
        intent.putExtra(EXTRA_PARAM2, personContact);
        context.startService(intent);
    }
}
