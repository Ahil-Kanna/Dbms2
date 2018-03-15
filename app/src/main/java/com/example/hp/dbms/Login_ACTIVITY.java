package com.example.hp.dbms;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class Login_ACTIVITY extends AppCompatActivity {

    String n=new String();
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        n=getIntent().getStringExtra("user");
        loginDataBaseAdapter=new LoginDataBaseAdapter(getApplicationContext());
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        //Toast.makeText(this, getIntent().getStringExtra("user"), Toast.LENGTH_SHORT).show();
    }

    public void signup(View v){
        Intent i=new Intent(this,Admin_signup.class);
        i.putExtra("user",n);
        startActivity(i);
        finishAffinity();
    }

    public void signin(View v){
        appdet appdet1=new appdet(getApplicationContext());



        TextInputEditText user=(TextInputEditText) findViewById(R.id.usernamel);
        TextInputLayout userl=(TextInputLayout) findViewById(R.id.textInputLayout);
        TextInputEditText pass=(TextInputEditText) findViewById(R.id.passwordl);
        TextInputLayout passl=(TextInputLayout) findViewById(R.id.passlay);


        if(TextUtils.isEmpty(user.getText().toString())) {
            userl.setError("This field can't be empty");
            Toast.makeText(this, "Enter fields", Toast.LENGTH_SHORT).show();
        }
        else if(!TextUtils.isEmpty(user.getText().toString())) {
            userl.setError(null);
        }

            if(TextUtils.isEmpty(pass.getText().toString())){
                passl.setError("This field can't be empty");
                Toast.makeText(this, "Enter fields", Toast.LENGTH_SHORT).show();
            }

            else if(!TextUtils.isEmpty(pass.getText().toString())) {
                userl.setError(null);
            }
            if(!TextUtils.isEmpty(user.getText().toString())&& !TextUtils.isEmpty(pass.getText().toString())){



                    String storedPassword=loginDataBaseAdapter.getpass(user.getText().toString(),n);

                    if(pass.getText().toString().equals(storedPassword)) {
                        Toast.makeText(this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                        Intent i;
                        CheckBox checkBox=(CheckBox) findViewById(R.id.checkBox);
                        if(checkBox.isChecked()){
                            appdet1.newdet(n,user.getText().toString());

                            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            appdet1.cleardet();

                        }
                        if(n.equals("admin")) {
                             i = new Intent(this, Admin.class);
                            i.putExtra("user",user.getText().toString());
                        }
                        else if(n.equals("employee")) {
                             i = new Intent(this, Employee.class);
                            i.putExtra("user",user.getText().toString());
                        }
                        else if (n.equals("client")){
                            i = new Intent(this, Client.class);
                            i.putExtra("user",user.getText().toString());
                        }
                        else{
                             i = new Intent(this, Main2Activity.class);
                        }
                        finishAffinity();
                        startActivity(i);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(this, "User Name or Password does not match", Toast.LENGTH_LONG).show();

                    }




            }
        }
    public void onDestroy(){
        super.onDestroy();
    }

    public void onBackPressed(){
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);
    }


    }

