package com.example.hp.dbms;


public class Emp {
    String emp_id;
    int experience;
    String emp_name;
    int salary;
    String[] skills=new String[3];

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
    public class Address{
        int d_no;;
        String f_line;
        String s_line;
        String city;
        String State;
        int zip;

    }
    public class Emp_bio{
        String email;
        String mobile;
        char gender;
        int age;
        Address address;

    }
    date join_date;
    public Emp_bio biodata=new Emp_bio();

    public void setall(int a,int b,String c,int d,date e,String[] f,String email,String mobile,char gender,int age,Address address){
        emp_id="emp_"+a;
        experience=b;
        emp_name=c;
        salary=d;
        skills=f;
        join_date=e;
        setbio(email,mobile,gender,age,address);
    }

    public Emp(){

    }
    public void setbio(String email,String mobile,char gender,int age,Address address){
        this.biodata.email=email;
        this.biodata.mobile=mobile;
        this.biodata.gender=gender;
        this.biodata.age=age;
        this.biodata.address=address;

    }
    public Boolean checkmail(String a){
        if(a.equals(biodata.email))
        return true;
        return false;
    }
}
