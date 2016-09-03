package com.example.shaunmesias.assignment_6_2.repository;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.DomainState;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonContactRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonContactRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonContactRepositoryTest extends AndroidTestCase {
    private static final String TAG="PERSON CONTACT TEST";
    private String id;
    public void testCreateReadUpdate() throws Exception {
        PersonContactRepository repository = new PersonContactRepositoryImpl(this.getContext());
        //CREATE DRIVER FACTORIES
        PersonContact createEntity = new PersonContact.Builder()
                .contactValue("123")
                .build();
        PersonContact insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<PersonContact> driver = repository.findAll();
        Assert.assertTrue(TAG + " READ ALL", driver.size() > 0);


        //READ ENTITY
        //long longId = Long.valueOf(id).longValue();
        PersonContact entity = repository.findById(1L);
        Assert.assertNotNull(TAG + " READ ENTITY", entity);


        //UPDATE ENTITY
        PersonContact updateEntity = new PersonContact.Builder()
                .copy(entity)
                .contactValue("1234")
                .build();
        repository.update(updateEntity);
        PersonContact newEntity = repository.findById(1L);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "1234", newEntity.getContactValue());

        // DELETE ENTITY
       /* repository.delete(updateEntity);
        PersonContact deletedEntity = repository.findById(longId);
        Assert.assertNull(TAG + " DELETE", deletedEntity);*/
    }

}
