package com.example.hp.dbms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class getprodetails extends AppCompatActivity {

    String n;
    String[] req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getprodetails);
        n=getIntent().getStringExtra("user");
        req=getResources().getStringArray(R.array.req);

    }

    public void putdetails(View v){
        //Toast.makeText(this,n, Toast.LENGTH_SHORT).show();
        EditText et1=(EditText) findViewById(R.id.editText);
        EditText et2=(EditText) findViewById(R.id.editText2);
        Spinner spinner1=(Spinner) findViewById(R.id.spinner) ;
        project_db db=new project_db(getApplicationContext());

        String a=et1.getText().toString();
        project p1=new project();
        project.date d=p1.new date("nil");
        project.date e=p1.new date(et2.getText().toString());
        project   p=new project();

        p.xyz(0,a,req[spinner1.getSelectedItemPosition()] ,d,e,"PENDING",n);

        db.insertEntry(p);
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,Client .class);
        i.putExtra("user",n);
        finishAffinity();
        startActivity(i);


    }
}
