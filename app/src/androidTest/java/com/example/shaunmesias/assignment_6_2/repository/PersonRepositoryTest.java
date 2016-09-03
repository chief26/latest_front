package com.example.shaunmesias.assignment_6_2.repository;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonRepositoryImpl;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonRepositoryTest extends AndroidTestCase {
    private static final String TAG="PERSON TEST";
    private long id;

    public void testCreateReadUpdate() throws Exception {
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
        Person insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);
        //Assert.assertEquals(5, insertedEntity.getId());

        //READ ALL
        Set<Person> person = repository.findAll();
        Assert.assertTrue(TAG + " READ ALL", person.size() > 0);


        //READ ENTITY
        Person entity = repository.findById(id);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);



        //UPDATE ENTITY
        Person updateEntity = new Person.Builder()
                .copy(entity)
                .name("John")
                .build();
        repository.update(updateEntity);
        Person newEntity = repository.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "John", newEntity.getName());
        Assert.assertEquals(TAG + " UPDATE ENTITY", "0731116856", newEntity.getPersonContact().getContactValue());

        // DELETE ENTITY
        /*repository.delete(updateEntity);
        Person deletedEntity = repository.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);*/

    }
}
