package com.example.hp.dbms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
    public void logout1(View v){
        Intent i=new Intent(this,Main2Activity.class);
        finishAffinity();
        startActivity(i);
    }

    public void viewproj(View v){
        Intent i=new Intent(this,Admin_viewproj.class);
        //finishAffinity();
        startActivity(i);

    }

    public void alloproj(View v){
        Intent i=new Intent(this,Admin_alloproj.class);
        //finishAffinity();
        startActivity(i);

    }

    public void viewclient(View v){
        Intent i=new Intent(this,Admin_viewclient.class);
        //finishAffinity();
        startActivity(i);

    }

    public void viewemp(View v){
        Intent i=new Intent(this,Admin_viewemp.class);
        //finishAffinity();
        startActivity(i);

    }

}
