package com.example.hp.dbms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class assign_mem extends AppCompatActivity {

    String n1;

    int ijk;
    ListView lv;
    int k = 0;
    ArrayList<String> s = new ArrayList<String>();
    emp_db db;
    team_db db1;
    project_db db2;
    String n;
    ArrayAdapter<String> ada1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_mem);
        n1 = getIntent().getStringExtra("pro");
        ijk=Integer.parseInt(getIntent().getStringExtra("proid"));
        db = new emp_db(getApplicationContext());
        db1 = new team_db(getApplicationContext());
        db2=new project_db(getApplicationContext());
        lv = (ListView) findViewById(R.id.lv12);
        s.clear();
        s.add("ID    \t\t EMPLOYEE_NAME    EXPERIENCE");

        String app, ap1;
        Emp[] p = new Emp[20];
        p = db.getall(null, "admin");
        int i = 0;
        while (p[i] != null) {
            app = String.format("%-100s", p[i].emp_id.replaceFirst("emp_", "") + "  \t\t\t\t\t\t\t        " + p[i].emp_name);
            ap1 = app.substring(0, 60);
            s.add(ap1 + p[i++].experience);

        }


        //ArrayAdapter <String>
        ada1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, s);
        lv.setAdapter(ada1);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (k == 1) {
                    if (i != 0) {

                        int siz;

                        String x = s.get(i);
                        siz = x.length() - 5;
                        String y;
                        y = x.substring(0, siz).trim();
                        String abcd = new String();
                        abcd = x.replaceAll("[^0-9]", "");
                        abcd.trim();
                        int ing = Integer.parseInt(abcd);
                        String y1 = x.substring(5, siz).trim();
                        Toast.makeText(assign_mem.this, "id" + ing + "nam" + y1, Toast.LENGTH_SHORT).show();
                        Toast.makeText(assign_mem.this, "ID=" + ing + " set as member", Toast.LENGTH_SHORT).show();
                        db1.insertEntry("emp_" + ing, n1, 0, "member");

                        ada1.notifyDataSetChanged();
                        lv.setAdapter(ada1);
                        db2.accept(ijk);
                        k=0;
                        finish();



                    }
                }
            }
        });

    }

    public void setemp1(View v) {
        k = 1;
    }

}