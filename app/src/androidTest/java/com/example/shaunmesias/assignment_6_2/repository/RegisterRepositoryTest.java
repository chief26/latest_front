package com.example.shaunmesias.assignment_6_2.repository;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.domain.registration.Register;
import com.example.shaunmesias.assignment_6_2.repository.register.RegisterRepository;
import com.example.shaunmesias.assignment_6_2.repository.register.impl.RegisterRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class RegisterRepositoryTest extends AndroidTestCase {
    private static final String TAG="PERSON TEST";
    private long id;

    public void testCreateReadUpdate() throws Exception {
        RegisterRepository repository = new RegisterRepositoryImpl(this.getContext());
        //CREATE DRIVER FACTORIES
        Register createEntity = new Register.Builder()
                .username("user")
                .password("was")
                .category("sw@de.com")
                .build();
        Register insertedEntity = repository.save(createEntity);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG + " CREATE", insertedEntity);

        //READ ALL
        Set<Register> person = repository.findAll();
        Assert.assertTrue(TAG+" READ ALL",person.size()>0);


        //READ ENTITY
        Register entity = repository.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Register updateEntity = new Register.Builder()
                .copy(entity)
                .password("rdf")
                .build();
        repository.update(updateEntity);
        Register newEntity = repository.findById(id);
        Assert.assertEquals(TAG + " UPDATE ENTITY", "rdf", newEntity.getPassword());

        // DELETE ENTITY
        repository.delete(updateEntity);
        Register deletedEntity = repository.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
