package com.example.hp.dbms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Emp_get_det extends AppCompatActivity {

    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_get_det);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        RadioGroup gen=(RadioGroup) findViewById(R.id.radiogroupg);
        gen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            public void onCheckedChanged(RadioGroup gen,int i){
                switch(i){
                    case R.id.radioButton:gender="male";break;
                    case R.id.radioButton2:gender="female";break;
                    case R.id.radioButton3:gender="other";break;
                    default:break;
                }
            }

        }
        );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emp_db db=new emp_db(getApplicationContext());
                Emp emp=new Emp();
                TextInputEditText user=(TextInputEditText) findViewById(R.id.users);
                TextInputLayout userl=(TextInputLayout) findViewById(R.id.userlay2);
                TextInputEditText email=(TextInputEditText) findViewById(R.id.mail1);
                TextInputLayout emaill=(TextInputLayout) findViewById(R.id.maillay);
                TextInputEditText mobile=(TextInputEditText) findViewById(R.id.mob1);
                TextInputLayout mobilel=(TextInputLayout) findViewById(R.id.mobilelay);
                EditText ex=(EditText) findViewById(R.id.editText5);
                EditText age=(EditText) findViewById(R.id.editText9);
                EditText sal=(EditText) findViewById(R.id.editText6);
                String[] s=new String[3];
                Emp.date d=emp.new date("");
                Emp.Address add=emp.new Address();
                emp.setall(0,Integer.parseInt(ex.getText().toString()),user.getText().toString(),Integer.parseInt(sal.getText().toString()),d,s,email.getText().toString(),
                        mobile.getText().toString(),gender.charAt(0),Integer.parseInt(age.getText().toString()),add);
                db.insertEntry(emp);

                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),Employee.class);
                finishAffinity();
                startActivity(i);
            }
        });
    }



}
