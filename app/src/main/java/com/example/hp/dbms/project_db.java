package com.example.hp.dbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class project_db extends SQLiteOpenHelper {
     static final String PRO_DB = "project.db";
    static int DB_VER=1;
    public static final int NAME_COLUMN = 1;
    String TN="Project_table";
    static final String DATABASE_CREATE2 = "create table "+"Project_table"+ "( " +"pro_id"+" integer primary key autoincrement,"+ "pro_name text,pro_req text,start_date text,end_date text,status text,c_name text); ";
    public SQLiteDatabase db;

    public  project_db(Context context)
    {
        super(context, PRO_DB, null, DB_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(DATABASE_CREATE2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        Log.w("TaskDBAdapter", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");
        _db.execSQL("DROP TABLE IF EXISTS " + TN);
        onCreate(_db);
    }
    public  project_db open() throws SQLException
    {
        db = this.getWritableDatabase();
        return this;
    }
    public void insertEntry(project p)
    {
        db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("pro_name",p.pro_name);
        newValues.put("pro_req",p.pro_req);
        newValues.put("start_date",p.start_date.dd+"-"+p.start_date.mm+"-"+p.start_date.yy);
        newValues.put("end_date",p.end_date.dd+"-"+p.end_date.mm+"-"+p.end_date.yy);
        newValues.put("status",p.status);
        newValues.put("c_name",p.c_name);

        db.insert(TN, null, newValues);
        db.close();
    }

    public void  updateEntry(project p,int id)
    {
        db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("pro_name",p.pro_name);
        newValues.put("pro_req",p.pro_req);
        newValues.put("start_date",p.start_date.dd+"-"+p.start_date.mm+"-"+p.start_date.yy);
        newValues.put("end_date",p.end_date.dd+"-"+p.end_date.mm+"-"+p.end_date.yy);
        newValues.put("status",p.status);
        newValues.put("c_name",p.c_name);
        String where="pro_id = "+id;
        db.update(TN,newValues, where, null);
        db.close();
    }

    public void deletet(String c_n,int id){
        db = this.getWritableDatabase();
        db.delete(TN,"c_name='"+c_n+"' and pro_id="+id,null);
        db.close();
    }

    public project getdetails(int id){
        project p=new project();
        db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from "+TN+" where pro_id="+id,null);
        if(cursor.moveToFirst()){
            project.date s=p.new date(cursor.getString(cursor.getColumnIndex("start_date")));
            project.date e=p.new date(cursor.getString(cursor.getColumnIndex("end_date")));
            p.xyz(cursor.getInt(cursor.getColumnIndex("pro_id")),cursor.getString(cursor.getColumnIndex("pro_name")),cursor.getString(cursor.getColumnIndex("pro_req")),s,e,cursor.getString(cursor.getColumnIndex("status")),cursor.getString(cursor.getColumnIndex("c_name")));

            cursor.close();
            db.close();
            return p;

        }

        cursor.close();

        db.close();

        return null;
    }

    public project[] getall(String c_n,String abc){
        project p[]=new project[20];
        int i=0;
        db = this.getWritableDatabase();
        Cursor cursor;
        if(abc.equals("admin")){
            cursor=db.rawQuery("SELECT * from "+TN,null);
        }
        else
             cursor=db.rawQuery("SELECT * from "+TN+" WHERE c_name='"+c_n+"'",null);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {

                do {
                    p[i]=new project();
                    project.date s=p[i].new date(cursor.getString(cursor.getColumnIndex("start_date")));
                    project.date e=p[i].new date(cursor.getString(cursor.getColumnIndex("end_date")));



                    p[i++].xyz(cursor.getInt(cursor.getColumnIndex("pro_id")),cursor.getString(cursor.getColumnIndex("pro_name")),cursor.getString(cursor.getColumnIndex("pro_req")),s,e,cursor.getString(cursor.getColumnIndex("status")),cursor.getString(cursor.getColumnIndex("c_name")));

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

    public void accept(int id){
        db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from "+TN+" where pro_id="+id,null);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {

                do {

                    project p=new project();

                    project.date s=p.new date(cursor.getString(cursor.getColumnIndex("start_date")));
                    project.date e=p.new date(cursor.getString(cursor.getColumnIndex("end_date")));
                    p.xyz(cursor.getInt(cursor.getColumnIndex("pro_id")),cursor.getString(cursor.getColumnIndex("pro_name")),cursor.getString(cursor.getColumnIndex("pro_req")),s,e,"ACCEPTED",cursor.getString(cursor.getColumnIndex("c_name")));

                    this.updateEntry(p,id);
                } while (cursor.moveToNext());
                cursor.close();
                db.close();

            }

        }
        cursor.close();


        db.close();

    }

    public int countof(String c){
        db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TN+" WHERE c_name='"+c+"'",null);

        int l=cursor.getCount();
        cursor.close();
        db.close();
        return l;

    }
}
