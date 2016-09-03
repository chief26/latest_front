package com.example.shaunmesias.assignment_6_2.repository;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.DomainState;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonDetails;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonDetailsRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonDetailsRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonDetailsRepositoryTest extends AndroidTestCase {
    private static final String TAG="DRIVER DETAILS TEST";
    private String id;

    public void testCreateReadUpdate() throws Exception {
        PersonDetailsRepository repository = new PersonDetailsRepositoryImpl(this.getContext());
        //CREATE DRIVER FACTORIES
        PersonDetails createEntity = new PersonDetails.Builder()
                .id("1")
                .ownerName("John")
                .carName("BMW")
                .carType("Sedan")
                .status(DomainState.ACTIVE.name())
                .state(DomainState.ACTIVE.name())
                .build();
        PersonDetails insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<PersonDetails> driver = repository.findAll();
        Assert.assertTrue(TAG+" READ ALL",driver.size()>0);


        //READ ENTITY
        long longId = new Long(id);
        PersonDetails entity = repository.findById(longId);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        PersonDetails updateEntity = new PersonDetails.Builder()
                .copy(entity)
                .carName("Nissan")
                .build();
        repository.update(updateEntity);
        PersonDetails newEntity = repository.findById(longId);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "Nissan", newEntity.getCarName());

        // DELETE ENTITY
        repository.delete(updateEntity);
        PersonDetails deletedEntity = repository.findById(longId);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
