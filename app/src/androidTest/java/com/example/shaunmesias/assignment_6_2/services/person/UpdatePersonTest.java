package com.example.shaunmesias.assignment_6_2.services.person;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.factories.person.PersonFactory;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.person.impl.UpdatePersonServiceImpl;

import junit.framework.Assert;

/**
 * Created by Shaun Mesias on 2016/05/12.
 */
public class UpdatePersonTest extends AndroidTestCase {
        public void testUpdatePerson() throws Exception {
                PersonContact personContact = new PersonContact.Builder()
                .contactValue("0731116856")
                .build();

                Person driver = PersonFactory.getPerson("1234", "black", "Town", "sss.vom",personContact);
                UpdatePersonServiceImpl service = UpdatePersonServiceImpl.getInstance();

                PersonRepository database = new PersonRepositoryImpl(this.getContext());

                Person savedDriver = database.save(driver);
                long id = savedDriver.getId();
                Person entity = database.findById(id);

                Person updateDriver = new Person.Builder()
                        .copy(entity)
                        .name("white")
                        .build();

                service.updatePerson(App.getAppContext(), updateDriver, personContact);
                Thread.sleep(1000);

                Person updatedDriver = database.findById(id);
                Assert.assertEquals("white", updatedDriver.getName());
        }


}
