package com.example.shaunmesias.assignment_6_2.services.register;

import android.test.AndroidTestCase;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.registration.Register;
import com.example.shaunmesias.assignment_6_2.factories.register.RegisterFactory;
import com.example.shaunmesias.assignment_6_2.repository.register.RegisterRepository;
import com.example.shaunmesias.assignment_6_2.repository.register.impl.RegisterRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.register.impl.UpdateRegisterServiceImpl;

import junit.framework.Assert;

/**
 * Created by Shaun Mesias on 2016/05/12.
 */
public class UpdateRegisterTest extends AndroidTestCase {
    public void testUpdateRegister() throws Exception {
                Register register = RegisterFactory.getRegister("mack", "was", "saw.com");
                UpdateRegisterServiceImpl service = UpdateRegisterServiceImpl.getInstance();

                RegisterRepository database = new RegisterRepositoryImpl(this.getContext());

                Register savedRegister = database.save(register);
                long id = savedRegister.getId();
                Register entity = database.findById(id);

                Register updateRegister = new Register.Builder()
                        .copy(entity)
                        .password("password")
                        .build();

                service.updateRegister(App.getAppContext(), updateRegister);
                Thread.sleep(1000);

                Register updatedRegister = database.findById(id);
                Assert.assertEquals("password", updatedRegister.getPassword());
        }
}
