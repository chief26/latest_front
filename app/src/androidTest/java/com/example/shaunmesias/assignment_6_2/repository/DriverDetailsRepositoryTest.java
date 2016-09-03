package com.example.shaunmesias.assignment_6_2.repository;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.DomainState;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverDetailsRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverDetailsRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class DriverDetailsRepositoryTest extends AndroidTestCase {
    private static final String TAG="DRIVER DETAILS TEST";
    private String id;

    public void testCreateReadUpdate() throws Exception {
        DriverDetailsRepository repository = new DriverDetailsRepositoryImpl(this.getContext());
        //CREATE DRIVER FACTORIES
        DriverDetails createEntity = new DriverDetails.Builder()
                .id("1")
                .ownerName("John")
                .carName("BMW")
                .build();
        DriverDetails insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<DriverDetails> driver = repository.findAll();
        Assert.assertTrue(TAG+" READ ALL",driver.size()>0);


        //READ ENTITY
        long longId = new Long(id);
        DriverDetails entity = repository.findById(longId);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        DriverDetails updateEntity = new DriverDetails.Builder()
                .copy(entity)
                .carName("Nissan")
                .build();
        repository.update(updateEntity);
        DriverDetails newEntity = repository.findById(longId);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "Nissan", newEntity.getCarName());

        // DELETE ENTITY
        repository.delete(updateEntity);
        DriverDetails deletedEntity = repository.findById(longId);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
