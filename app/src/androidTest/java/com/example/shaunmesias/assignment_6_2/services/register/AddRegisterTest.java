package com.example.shaunmesias.assignment_6_2.services.register;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.registration.Register;
import com.example.shaunmesias.assignment_6_2.factories.register.RegisterFactory;
import com.example.shaunmesias.assignment_6_2.repository.register.RegisterRepository;
import com.example.shaunmesias.assignment_6_2.repository.register.impl.RegisterRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.register.impl.AddRegisterServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/12.
 */
public class AddRegisterTest extends AndroidTestCase {
    public void testRegisterService() throws Exception {
        Register register = RegisterFactory.getRegister("mack","was","saw.com");
        AddRegisterServiceImpl service = AddRegisterServiceImpl.getInstance();
        RegisterRepository database = new RegisterRepositoryImpl(this.getContext());

        service.addRegister(App.getAppContext(), register);
        Thread.sleep(1000);

        //READ ALL
        /*Set<Register> persons = database.findAll();
        Assert.assertTrue(" READ ALL", persons.size() > 0);*/
    }
}
