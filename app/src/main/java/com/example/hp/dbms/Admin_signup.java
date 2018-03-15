package com.example.hp.dbms;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin_signup extends Activity {



    String n=new String();
    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);
        Button b=(Button) findViewById(R.id.button6);
        n=getIntent().getStringExtra("user");
        //Toast.makeText(this, getIntent().getStringExtra("user"), Toast.LENGTH_SHORT).show();
        loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
        b.setOnClickListener(new View.OnClickListener (){

            public void onClick(View v) {

                loginDataBaseAdapter = loginDataBaseAdapter.open();

                TextInputEditText user = (TextInputEditText) findViewById(R.id.username1);
                TextInputLayout userl1 = (TextInputLayout) findViewById(R.id.textInputLayout1);
                TextInputEditText pass = (TextInputEditText) findViewById(R.id.password1);
                TextInputLayout passl1 = (TextInputLayout) findViewById(R.id.passlay1);
                TextInputEditText passr = (TextInputEditText) findViewById(R.id.password2);
                TextInputLayout passl2 = (TextInputLayout) findViewById(R.id.passlay2);


                if (TextUtils.isEmpty(user.getText().toString())) {
                     userl1.setError("Field can't be empty");
                } else
                    userl1.setError(null);
                if (TextUtils.isEmpty(pass.getText().toString())||TextUtils.isEmpty(passr.getText().toString())) {
                    passl1.setError("Field can't be empty");
                } else
                    passl1.setError(null);
                if (!pass.getText().toString().equals(passr.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    passl2.setError("Password doesn't match!!!");

                } else if (!TextUtils.isEmpty(user.getText().toString()) && !TextUtils.isEmpty(pass.getText().toString())) {
                    if(!loginDataBaseAdapter.usercheck(user.getText().toString()))
                    {
                        userl1.setError("Username already exist");
                    }
                    else {
                        loginDataBaseAdapter.insertEntry(user.getText().toString(), pass.getText().toString(), n);
                        Toast.makeText(getApplicationContext(), "\""+n + "\" Account Successfully Created ", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), Login_ACTIVITY.class);
                        i.putExtra("user", n);
                        startActivity(i);
                        finish();
                    }
                }

            }
                });


    }

    public void login(View v){
        Intent i=new Intent(this,Login_ACTIVITY.class);
        i.putExtra("user",n);
        startActivity(i);
    }
    public void onDestroy(){
        super.onDestroy();
        //loginDataBaseAdapter.close();
    }

}
