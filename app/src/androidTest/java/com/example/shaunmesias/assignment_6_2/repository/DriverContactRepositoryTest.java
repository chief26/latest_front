package com.example.shaunmesias.assignment_6_2.repository;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.DomainState;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverContactRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverContactRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class DriverContactRepositoryTest extends AndroidTestCase {
    private static final String TAG="DRIVER CONTACT TEST";
    private String id;

    public void testCreateReadUpdate() throws Exception {
        DriverContactRepository repository = new DriverContactRepositoryImpl(this.getContext());
        //CREATE DRIVER FACTORIES
        DriverContact createEntity = new DriverContact.Builder()
                .id("2")
                .contactValue("123")
                .build();
        DriverContact insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<DriverContact> driver = repository.findAll();
        Assert.assertTrue(TAG+" READ ALL",driver.size()>0);


        //READ ENTITY
        long longId = new Long(id);
        DriverContact entity = repository.findById(longId);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        DriverContact updateEntity = new DriverContact.Builder()
                .copy(entity)
                .contactValue("1234")
                .build();
        repository.update(updateEntity);
        DriverContact newEntity = repository.findById(longId);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "1234", newEntity.getContactValue());

        // DELETE ENTITY
        repository.delete(updateEntity);
        DriverContact deletedEntity = repository.findById(longId);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
