package com.example.hp.dbms;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void logon1(View v){
        Intent i=new Intent(this,Login_ACTIVITY.class);
        i.putExtra("user","admin");
        startActivity(i);
    }

    public void logon2(View v){
        Intent i=new Intent(this,Login_ACTIVITY.class);
        i.putExtra("user","employee");
        startActivity(i);
    }
    public void logon3(View v){
        Intent i=new Intent(this,Login_ACTIVITY.class);
        i.putExtra("user","client");
        startActivity(i);
    }

    public void homepress(View v){
        Intent i=new Intent(this,MainActivity.class);
        finishAffinity();
        startActivity(i);

    }

    public void showall(View v){
        LoginDataBaseAdapter a=new LoginDataBaseAdapter(this);
        Cursor cursor=a.showall();
        if(cursor.getCount()>0) // UserName Not Exist
        {
            cursor.moveToFirst();
            do {

            Log.w("db entries ", cursor.getString(cursor.getColumnIndex("USERNAME")) + "   " + cursor.getString(cursor.getColumnIndex("PASSWORD")));
        }while(cursor.moveToNext());

        }
        a.close();

        cursor.close();
        //Log.w("a",cursor.getString(cursor.getColumnIndex("USERNAME")).equals(r)+" "+cursor.getString(cursor.getColumnIndex("PASSWORD")).equals(r));
    }

}
