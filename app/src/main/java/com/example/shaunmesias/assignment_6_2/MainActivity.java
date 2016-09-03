package com.example.shaunmesias.assignment_6_2;

import android.content.Intent;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void ButtonClick(View v){
        startActivity(new Intent(MainActivity.this, AddDriverActivity.class));
    }

    public void ButtonClickFind(View v){
        startActivity(new Intent(MainActivity.this, DetailsActivity.class));
    }

    public void ButtonClickExit(View v){
       // startActivity(new Intent(MainActivity.this, ViewAllActivity.class));

        finish();
    }
}
