package com.example.shaunmesias.assignment_6_2;

import com.example.shaunmesias.assignment_6_2.domain.registration.Register;
import com.example.shaunmesias.assignment_6_2.factories.register.RegisterFactory;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class RegisterTest {
    @Test
    public void testCreate() throws Exception {
        Register details = RegisterFactory.getRegister("messi","jack","se@e.com");
        Assert.assertEquals("messi", details.getUsername());
    }

    @Test
    public void testUpdate() throws Exception {
        Register details= RegisterFactory.getRegister("messi", "jack", "se@e.com");
        Register update = new Register.Builder()
                .copy(details)
                .password("more")
                .build();
        Assert.assertEquals("more", update.getPassword());
        Assert.assertEquals("messi", update.getUsername());
    }
}
