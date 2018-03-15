package com.example.hp.dbms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Client extends AppCompatActivity {

    ListView lv;
    int k=0;
    ArrayList<String> s=new ArrayList<String>();
    project_db db;
    String n;
    ArrayAdapter <String> ada1=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        db=new project_db(getApplicationContext());

        n=getIntent().getStringExtra("user");
        //Toast.makeText(this,n, Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv=(ListView) findViewById(R.id.lv1);
        //ada=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,s);


        //lv.setAdapter(ada);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
                {
                    if(k==1){
                    if(i!=0) {

                        String x=s.get(i).replaceFirst("pro_"+n+"_","");
                        x=x.substring(0,5).trim();
                        int in= Integer.parseInt(x);
                        db.deletet(n,in);
                        Toast.makeText(Client.this,"ID="+ x+" DELETED", Toast.LENGTH_SHORT).show();
                        s.remove(i);
                        ada1.notifyDataSetChanged();
                        lv.setAdapter(ada1);
                        k=0;

                    }}
                }
            });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),getprodetails.class);
                i.putExtra("user",n);
                startActivity(i);
            }
        });
    }

    public void logout(View v){
        appdet appdet1=new appdet(getApplicationContext());
        appdet1.cleardet();
        Intent i=new Intent(this,Main2Activity.class);
        i.putExtra("user",n);
        finishAffinity();
        startActivity(i);
    }

    public void showproj(View v){
        k=-1;


        //Toast.makeText(this, n, Toast.LENGTH_SHORT).show();

        s.clear();
        s.add("ID    \t  \t\t\t    PROJ_NAME   \t \t\t      STATUS");

        String app,ap1;
        project[] p=new project[20];
        p=db.getall(n,"client");
        int i=0;
            while(p[i]!=null) {
                app=String.format("%-100s",p[i].pro_id + "            " + p[i].pro_name );
                ap1=app.substring(0,50);
                s.add(ap1 +p[i++].status);

            }




        //ArrayAdapter <String>
                ada1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s);
        lv.setAdapter(ada1);



    }

    public void delvalues(View v){
        k=1;

    }

}
