package com.example.shaunmesias.assignment_6_2.services.register.impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.util.App;
import com.example.shaunmesias.assignment_6_2.domain.registration.Register;
import com.example.shaunmesias.assignment_6_2.repository.register.RegisterRepository;
import com.example.shaunmesias.assignment_6_2.repository.register.impl.RegisterRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.services.register.AddRegisterService;

/**
 * Created by Shaun Mesias on 2016/05/12.
 * This Intent service is used to add a register to the repository
 * The user doesn't need to be notified when adding to repository was successful.
 */
public class AddRegisterServiceImpl extends IntentService implements AddRegisterService {
    public static final String ACTION_FOO = "com.example.shaunmesias.assignment_6_2.services.register.impl.action.FOO";

    public static final String EXTRA_PARAM1 = "com.example.shaunmesias.assignment_6_2.services.register.impl.extra.PARAM1";

    RegisterRepository repository;

    public static final String TAG = "com.example.shaunmesias.assignment_6_2.services.register.impl";

    private static AddRegisterServiceImpl service = null;

    public AddRegisterServiceImpl(){
        super("AddRegisterServiceImpl");
        repository = new RegisterRepositoryImpl(App.getAppContext());
    }

    public static AddRegisterServiceImpl getInstance() {
        if (service == null)
            service = new AddRegisterServiceImpl();
        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Register reg = (Register) intent.getSerializableExtra(EXTRA_PARAM1);
            Register newReg = new Register.Builder()
                    .category(reg.getCategory())
                    .password(reg.getPassword())
                    .username(reg.getUsername())
                    .build();

        repository.save(newReg);
       // Log.i(TAG, "The service has started...........");
    }

    @Override
    public void addRegister(Context context, Register register) {
        //Log.i(TAG, "The AddRegister");
        Intent intent = new Intent(context, AddRegisterServiceImpl.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, register);
        context.startService(intent);
    }
}
