package com.example.hp.dbms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Admin_alloproj extends AppCompatActivity {
    ListView lv;
    int k = 0;
    ArrayList<String> s = new ArrayList<String>();
    project_db db;
    String n;
    String app,ap1;
    ArrayAdapter<String> ada1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_alloproj);
        db = new project_db(getApplicationContext());
        lv = (ListView) findViewById(R.id.lv3);
        s.clear();
        s.add("ID    \t  \t\t\t    PROJ_NAME   \t \t      STATUS");


        project[] p=new project[20];
        p=db.getall(null,"admin");
        int i=0;
        while(p[i]!=null) {
            app=String.format("%-100s",p[i].pro_id.replaceFirst("pro_","") + "                 " + p[i].pro_name );
            ap1=app.substring(0,40);
            s.add(ap1 +p[i++].status);

        }




        //ArrayAdapter <String>
        ada1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s);
        lv.setAdapter(ada1);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(k==1){
                    if(i!=0) {

                        String x=s.get(i);
                        x=x.substring(0,10).trim();
                        String abcd=new String();
                        abcd= x.replaceAll("[^0-9]","");
                        int in= Integer.parseInt(abcd);
                        Intent ij=new Intent(getApplicationContext(),Admin_assign.class);


                        //db.accept(in);
                        Toast.makeText(Admin_alloproj.this,"ID="+ x+" ACCEPTED", Toast.LENGTH_SHORT).show();
                        project p1=db.getdetails(in);
                        ij.putExtra("pro",p1.pro_id);
                        ij.putExtra("proid",abcd);
                        app=String.format("%-100s",p1.pro_id.replaceFirst("pro_","") + "                 " + p1.pro_name );
                        ap1=app.substring(0,40);
                        s.set(i,ap1+p1.status);
                        ada1.notifyDataSetChanged();
                        lv.setAdapter(ada1);
                        k=0;
                        startActivity(ij);

                    }}
            }
        });

    }

    public void accpro(View v){
        k=1;
    }
}

