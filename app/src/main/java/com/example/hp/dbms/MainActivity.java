package com.example.hp.dbms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void credits(View v){

        Intent i=new Intent(this,creditspage.class);
        startActivity(i);
    }

    public void logon(View v){
        appdet appdet1=new appdet(getApplicationContext());
        String a=appdet1.getdet();
        Intent i;
        if(a!=null)
        {
            if(a.equals("admin"))
            i=new Intent(this,Admin.class);
            else if(a.equals("employee"))
            i=new Intent(this,Employee.class);
            else if(a.equals("client"))
            i=new Intent(this,Client.class);
            else
            i=new Intent(this,Main2Activity.class);

        }
        else
            i=new Intent(this,Main2Activity.class);

        i.putExtra("user",appdet1.getuser());
        //Toast.makeText(this, appdet1.getuser(), Toast.LENGTH_SHORT).show();
        finishAffinity();
        startActivity(i);
    }

    public void abs(View v){

        Intent i=new Intent(this,Abstract.class);
        startActivity(i);

    }
}
