package com.example.hp.dbms;

public class project {
     String pro_id;
     String pro_name;
     String pro_req;
     public class date{
        int dd;
        int mm;
        int yy;
         public date(String a){

             String a1=a.replaceAll("[^0-9]","");
             if(a1.length()==6){
             dd=a1.charAt(0)+a1.charAt(1);
             mm=a1.charAt(2)+a1.charAt(3);
             yy=a1.charAt(4)+a1.charAt(5);}
             else{
                 dd=0;
                 mm=0;
                 yy=0;
             }

         }
    }
    date start_date;
    date end_date;
    String status;
    String c_name;

    public project(){

    }

    public void xyz(int a,String b,String c,date d,date e,String f,String g){
        pro_id="pro_"+g+"_"+a;
        pro_name=b;
        pro_req =c;
        start_date=d;
        end_date=e;
        status=f;
        c_name=g;
    }



}
