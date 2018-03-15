package com.example.hp.dbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class emp_db  extends SQLiteOpenHelper {
    static final String PRO_DB = "employe.db";
    static int DB_VER=1;
    String TN="employee_table";
    static final String DATABASE_CREATE3 = "create table "+"employee_table"+ "( " +"emp_id"+" integer primary key autoincrement,"+
            "emp_name text,experience integer,salary integer,age integer,skill1 text,skill2 text,skill3 text,"+
            "email text,mobile text,gender text); ";
    public SQLiteDatabase db;

    public  emp_db(Context context)
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
    public void insertEntry(Emp e)
    {
        db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("emp_name",e.emp_name);
        newValues.put("experience",e.experience);
        newValues.put("salary",e.salary);
        newValues.put("age",e.biodata.age);
        newValues.put("skill1",e.skills[0]);
        newValues.put("skill2",e.skills[1]);
        newValues.put("skill3",e.skills[2]);
        newValues.put("email",e.biodata.email);
        newValues.put("mobile",e.biodata.mobile);
        newValues.put("gender",Character.toString(e.biodata.gender));


        db.insert(TN, null, newValues);
        db.close();
    }

    public void  updateEntry(Emp e,int id)
    {
        db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("emp_name",e.emp_name);
        newValues.put("experience",e.experience);
        newValues.put("salary",e.salary);
        newValues.put("age",e.biodata.age);
        newValues.put("skill1",e.skills[0]);
        newValues.put("skill2",e.skills[1]);
        newValues.put("skill3",e.skills[2]);
        newValues.put("email",e.biodata.email);
        newValues.put("mobile",e.biodata.mobile);
        newValues.put("gender",Character.toString(e.biodata.gender));

        String where="emp_id = "+id;
        db.update(TN,newValues, where, null);
        db.close();
    }

    public void deletex(String e_n,int id){
        db = this.getWritableDatabase();
        db.delete(TN," emp_name='"+e_n+"'",null);
        db.close();
    }

    public Emp getdetails(String n){
        Emp e=new Emp();
        db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from "+TN+" where emp_name='"+n+"'",null);
        String[] a=new String[3];
        if(cursor.moveToFirst()){
            Emp.Address x=e.new Address();
            a[0]=cursor.getString(cursor.getColumnIndex("Skill1"));
            a[1]=cursor.getString(cursor.getColumnIndex("Skill2"));
            a[2]=cursor.getString(cursor.getColumnIndex("Skill3"));

           // e.setall(cursor.getInt(cursor.getColumnIndex("emp_id")),cursor.getInt(cursor.getColumnIndex("experience")),cursor.getString(cursor.getColumnIndex("emp_name")),cursor.getInt(cursor.getColumnIndex("salary")),null,a,cursor.getString(cursor.getColumnIndex("email")),
            //        cursor.getString(cursor.getColumnIndex("mobile")),cursor.getString(cursor.getColumnIndex("gender")).charAt(0),cursor.getInt(cursor.getColumnIndex("age")),x );

            cursor.close();
            db.close();
            return e;

        }

        cursor.close();

        db.close();

        return null;
    }

    public Emp[] getall(String c_n,String abc){
        Emp[] e=new Emp[20];
        int i=0;
        db = this.getWritableDatabase();
        Cursor cursor;
        if(abc.equals("admin")){
            cursor=db.rawQuery("SELECT * from "+TN,null);
        }
        else
            cursor=db.rawQuery("SELECT * from "+TN+" WHERE emp_name='"+c_n+"'",null);
        String[] a=new String[3];
        if(cursor!=null) {

            if( cursor.moveToFirst()) {

                do {
                    e[i] = new Emp();
                    Emp.Address x = e[i].new Address();
                    a[0] = ""; //cursor.getString(cursor.getColumnIndex("Skill1"));
                    a[1] = "";//cursor.getString(cursor.getColumnIndex("Skill2"));
                    a[2] = "";//cursor.getString(cursor.getColumnIndex("Skill3"));

                    e[i].setall(cursor.getInt(cursor.getColumnIndex("emp_id")), cursor.getInt(cursor.getColumnIndex("experience")), cursor.getString(cursor.getColumnIndex("emp_name")), cursor.getInt(cursor.getColumnIndex("salary")), null, a, cursor.getString(cursor.getColumnIndex("email")),
                            cursor.getString(cursor.getColumnIndex("mobile")), cursor.getString(cursor.getColumnIndex("gender")).charAt(0), cursor.getInt(cursor.getColumnIndex("age")), x);

                    i++;
                } while (cursor.moveToNext());
                cursor.close();
                db.close();
                return e;
            }



        }
        cursor.close();


        db.close();
        return e;
    }

    public void accept(int id){
        db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * from "+TN+" where pro_id="+id,null);
        if(cursor!=null) {
            if (cursor.moveToFirst()) {

                /*do {

                    project p=new project();

                    project.date s=p.new date(cursor.getString(cursor.getColumnIndex("start_date")));
                    project.date e=p.new date(cursor.getString(cursor.getColumnIndex("end_date")));
                    p.xyz(cursor.getInt(cursor.getColumnIndex("pro_id")),cursor.getString(cursor.getColumnIndex("pro_name")),cursor.getString(cursor.getColumnIndex("pro_req")),s,e,"ACCEPTED",cursor.getString(cursor.getColumnIndex("c_name")));

                    this.updateEntry(e,id);
                } while (cursor.moveToNext());
                cursor.close();
                db.close();

            */}

        }
        cursor.close();


        db.close();

    }

    public int countof(String c){
        db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TN+" WHERE emp_name='"+c+"'",null);

        int l=cursor.getCount();
        cursor.close();
        db.close();
        return l;

    }
}
