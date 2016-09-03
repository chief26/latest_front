package com.example.shaunmesias.assignment_6_2.repository;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class DriverRepositoryTest extends AndroidTestCase {
    private static final String TAG="DRIVER TEST";
    private long id;

    public void testCreateReadUpdate() throws Exception {
        DriverRepository repository = new DriverRepositoryImpl(this.getContext());
        //CREATE DRIVER FACTORIES
        final DriverDetails driverDetails = new DriverDetails.Builder()
                .carName("nissan")
                .ownerName("Jack")
                .build();

        final DriverContact driverContact = new DriverContact.Builder()
                .contactValue("0730006856")
                .build();

        Driver createEntity = new Driver.Builder()
                .serverId("1234")
                .name("Jack")
                .area("Durban")
                .email("was@b.com")
                .getDriverContact(driverContact)
                .getDriverDetails(driverDetails)
                .build();
        Driver insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE", insertedEntity);

        //READ ALL
        Set<Driver> driver = repository.findAll();
        Assert.assertTrue(TAG+" READ ALL",driver.size()>0);


        //READ ENTITY
        Driver entity = repository.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Driver updateEntity = new Driver.Builder()
                .copy(entity)
                .name("John")
                .build();
        repository.update(updateEntity);
        Driver newEntity = repository.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "John", newEntity.getName());
        Assert.assertEquals(TAG + " UPDATE ENTITY", "nissan", newEntity.getDriverDetails().getCarName());

        // DELETE ENTITY
        /*repository.delete(updateEntity);
        Driver deletedEntity = repository.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);*/

    }
}
