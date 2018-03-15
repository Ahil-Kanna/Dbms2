package com.example.hp.dbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin on 08/11/2017.
 */

public class team_db  extends SQLiteOpenHelper {
    static final String PRO_DB = "team1.db";
    static int DB_VER=1;
    public static final int NAME_COLUMN = 1;
    String TN="team_table";
    static final String DATABASE_CREATE3 = "create table "+"team_table"+ "( " +"emp_id"+" text primary key,"+ "pro_name text,lead_id integer,role text); ";
    public SQLiteDatabase db;

    public  team_db(Context context)
    {
        super(context, PRO_DB, null, DB_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(DATABASE_CREATE3);
    }
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");
        _db.execSQL("DROP TABLE IF EXISTS " + TN);
        onCreate(_db);
    }

    public void insertEntry(String a,String b,int c,String d)
    {
        db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("emp_id",a);
        newValues.put("pro_name",b);
        if(c==-1)
        newValues.put("lead_id",c);
        newValues.put("role",d);

        db.insert(TN, null, newValues);
        db.close();
    }

    public void  updateEntry(String a,String b,int c,String d)
    {
        db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("emp_id",a);
        newValues.put("pro_name",b);
        if(c==-1)
            newValues.put("lead_id",c);
        newValues.put("role",d);

        String where="emp_id = "+a;
        db.update(TN,newValues, where, null);
        db.close();
    }

    public void deletet(String a){
        db = this.getWritableDatabase();
        db.delete(TN,"pro_name='"+a+"'",null);
        db.close();
    }

    public team getdetails(String id){
        team p=new team();
        db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from "+TN+" where emp_id='"+id+"'",null);
        if(cursor.moveToFirst()){
            p.setteam(cursor.getString(cursor.getColumnIndex("emp_id")),cursor.getString(cursor.getColumnIndex("pro_name")),cursor.getInt(cursor.getColumnIndex("Lead_id")),cursor.getString(cursor.getColumnIndex("role")));

            cursor.close();
            db.close();
            return p;

        }

        cursor.close();

        db.close();

        return null;
    }

    public team[] getall(String c_n,String abc){
        team[] p=new team[20];
        int i=0;
        db = this.getWritableDatabase();
        Cursor cursor;
        if(abc.equals("admin")){
            cursor=db.rawQuery("SELECT * from "+TN,null);
        }
        else
            cursor=db.rawQuery("SELECT * from "+TN+" WHERE emp_id='"+c_n+"'",null);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {

                do {
                    p[i].setteam(cursor.getString(cursor.getColumnIndex("emp_id")),cursor.getString(cursor.getColumnIndex("pro_name")),cursor.getInt(cursor.getColumnIndex("Lead_id")),cursor.getString(cursor.getColumnIndex("role")));
                    i++;
                } while (cursor.moveToNext());
                cursor.close();
                db.close();
                return p;

            }

        }
        cursor.close();


        db.close();
        return p;
    }


    public int countof(String c){
        db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TN,null);

        int l=cursor.getCount();
        cursor.close();
        db.close();
        return l;

    }
}
