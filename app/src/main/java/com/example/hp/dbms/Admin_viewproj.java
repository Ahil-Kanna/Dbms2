package com.example.hp.dbms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Admin_viewproj extends AppCompatActivity {

    ListView lv;
    int k=0;
    ArrayList<String> s=new ArrayList<String>();
    project_db db;
    String n;
    ArrayAdapter<String> ada1=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_viewproj);
        db=new project_db(getApplicationContext());
        lv=(ListView) findViewById(R.id.lv2);
        s.clear();
        s.add("ID    \t  \t\t\t    PROJ_NAME   \t \t      STATUS");

        String app,ap1;
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

                        String x=s.get(i);//.replaceFirst("pro_"+n+"_","");
                        x=x.substring(0,10).trim();
                        String abcd=new String();
                        abcd= x.replaceAll("[^0-9]","");
                        int in= Integer.parseInt(abcd);
                        db.deletet(x.replaceFirst("_"+in,""),in);
                        Toast.makeText(Admin_viewproj.this,"ID="+ x+" DELETED", Toast.LENGTH_SHORT).show();
                        s.remove(i);
                        ada1.notifyDataSetChanged();
                        lv.setAdapter(ada1);
                        k=0;

                    }}
            }
        });

    }

    public void delpro(View v){
        k=1;
    }
}
