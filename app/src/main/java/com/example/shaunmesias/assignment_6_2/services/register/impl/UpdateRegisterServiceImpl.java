package com.example.shaunmesias.assignment_6_2.services.register.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.registration.Register;
import com.example.shaunmesias.assignment_6_2.repository.register.RegisterRepository;
import com.example.shaunmesias.assignment_6_2.repository.register.impl.RegisterRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.register.UpdateRegisterService;

/**
 * Created by Shaun Mesias on 2016/05/12.
 * This Intent service is used to update a register in the repository
 * The user doesn't need to be notified when updating the repository was successful.
 */
public class UpdateRegisterServiceImpl extends IntentService implements UpdateRegisterService {
    public static final String ACTION_FOO = "com.example.shaunmesias.assignment_6_2.services.register.impl.action.FOO";

    public static final String EXTRA_PARAM1 = "com.example.shaunmesias.assignment_6_2.services.register.impl.extra.PARAM1";

    RegisterRepository repository;

    public static final String TAG = "com.example.shaunmesias.assignment_6_2.services.register.impl";

    private static UpdateRegisterServiceImpl service = null;

    public UpdateRegisterServiceImpl(){
        super("UpdateRegisterServiceImpl");
        repository = new RegisterRepositoryImpl(App.getAppContext());
    }

    public static UpdateRegisterServiceImpl getInstance() {
        if (service == null)
            service = new UpdateRegisterServiceImpl();
        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Register person = (Register)intent.getSerializableExtra(EXTRA_PARAM1);
        Register newPerson = new Register.Builder()
                .copy(person)
                .build();

        repository.update(newPerson);
        Log.i(TAG, "The service has started...........");
    }

    @Override
    public void updateRegister(Context context, Register person) {
        Log.i(TAG, "The updateRegister");
        Intent intent = new Intent(context, UpdateRegisterServiceImpl.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, person);
        context.startService(intent);
    }
}
