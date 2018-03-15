package com.example.hp.dbms;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Employee extends AppCompatActivity {

    String n;

    int ijk;
    ListView lv;
    int k = 0;
    ArrayList<String> s = new ArrayList<String>();
    emp_db db;
    team_db db1;
    project_db db2;
    ArrayAdapter<String> ada1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        n=getIntent().getStringExtra("user");
        db = new emp_db(getApplicationContext());
        db1 = new team_db(getApplicationContext());
        db2=new project_db(getApplicationContext());
        TextView tv=(TextView) findViewById(R.id.textView10);
        /*team a=db1.getdetails(n);
        if(a==null){
            tv.setText("YOU ARE JUST AN EMPLOYEE");
        }
        else if(a.role.equals("leader"))
            tv.setText("YOU ARE A TEAM LEADER");
        else
            tv.setText("YOU ARE A TEAM MEMBER");*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Emp_get_det.class);
                i.putExtra("user",n);
                startActivity(i);
            }
        });
    }

    public void logout(View v){
        Intent i=new Intent(this,Main2Activity.class);
        finishAffinity();
        startActivity(i);

    }

    public void showstat(View v){
        team a=db1.getdetails(n);
        if(a!=null){
            String x=a.pro_name;//.replaceFirst("pro_"+n+"_","");
            x=x.substring(0,10).trim();
            String abcd=new String();
            abcd= x.replaceAll("[^0-9]","");
            int in= Integer.parseInt(abcd);
            project p1=db2.getdetails(in);
            if(p1!=null){
                Toast.makeText(this, "STATUS:"+p1.status, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void teamlead(View v){
        team a=db1.getdetails(n);
        if(a!=null){

            Toast.makeText(this, "LEAD:"+a.lead_id, Toast.LENGTH_SHORT).show();
        }

    }

    public void teammember(View v){
        team a=db1.getdetails(n);
        team_db db3=new team_db(getApplicationContext());
        if(a!=null){
            team[] b=db3.getall(null,"admin");
            int j=0;
            while(j<b.length){
                if(b[j].pro_name.equals(a.pro_name)){
                    Toast.makeText(this, "Member :"+b[j].emp_name, Toast.LENGTH_SHORT).show();
                }
            }

        }

        if(a.role.equals("leader")){

        }

    }

    public void submitdat(View v){
        team a=db1.getdetails(n);
        if(a!=null){
            String x=a.pro_name;//.replaceFirst("pro_"+n+"_","");
            x=x.substring(0,10).trim();
            String abcd=new String();
            abcd= x.replaceAll("[^0-9]","");
            int in= Integer.parseInt(abcd);
            project p1=db2.getdetails(in);
            if(p1!=null){
               p1.status="COMPLETED";
                db2.updateEntry(p1,in);
                Toast.makeText(this, "PROJECT COMPLETED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
