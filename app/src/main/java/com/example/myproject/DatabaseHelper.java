package com.example.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.database.sqlite.SQLiteDatabase.openDatabase;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbname = "MyProject.db";
   public static String dbpath = "";
    boolean isLoggedIn = false;
    public Context context;
    User userData = null;

    public DatabaseHelper(Context context) throws IOException {
        super(context,dbname,null,2);

       dbpath += context.getApplicationInfo().dataDir+"/databases/";
        this.context = context;
      // this.updateDatabases();
    }

    public boolean isLoggedIn(){
        if(isLoggedIn == true)
            return true;
        else return false;
    }


    public void storeUserData(User res){
         this.userData = res;
    }

    public User getUserData(){
        return userData;
    }


    public void updateDatabases() throws IOException {
        File dbfile = new File(dbpath + dbname);
        if(dbfile.exists())
             dbfile.delete();

        InputStream in = context.getAssets().open("MyProject.db");
        OutputStream out = new FileOutputStream(dbpath + "MyProject.db");

        byte[] arr = new byte[1024];
        int i = 0;
        while((i=in.read(arr))>0 )
            out.write(arr,0,i);

        out.flush();
        out.close(); in.close();
    }

    public void onCreate(SQLiteDatabase db){
      /* db.execSQL("CREATE TABLE user(uname VARCHAR(15),email VARCHAR(20),Class VARCHAR(15),interested_sub VARCHAR(50),password VARCHAR(15),age int(3))");
         db.execSQL("CREATE TABLE subject(sname VARCHAR(15),scode INT(3),catagory VARCHAR(15),recommended VARCHAR(15),followers varchar(10))");
         db.execSQL("CREATE TABLE ftred_and_sgted(sname VARCHAR(15),scode INT(3),catagory VARCHAR(15),followers VARCHAR(15))");
         db.execSQL("CREATE TABLE sub1(qcode INT(3),ques VARCHAR(250),opt1 VARCHAR(15),opt2 VARCHAR(15),opt3 VARCHAR(15),opt4 VARCHAR(15),ans VARCHAR(15),choice varchar(10))");
         db.execSQL("CREATE TABLE result(user VARCHAR(15),email VARCHAR(15),sub VARCHAR(15),score INT(3),outOf INT(3),percentage VARCHAR(5))");
    */
    }


    public void onUpgrade(SQLiteDatabase db,int oldVer,int newVer){
      /* db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS subject");
        db.execSQL("DROP TABLE IF EXISTS ftred_and_sgted");
        db.execSQL("DROP TABLE IF EXISTS sub1");
        db.execSQL("DROP TABLE IF EXISTS result");
        onCreate(db);*/
    }

    public boolean insertUserData(String uname,String email,String Class,int age,String int_sub,String pwd){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("uname",uname); cv.put("email",email);
        cv.put("Class",Class); cv.put("age",age);
        cv.put("interested_sub",int_sub); cv.put("password",pwd);
        long r = db.insert("user",null,cv);
        return (r!=1? true : false);
    }

    public boolean login(String uname,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM user WHERE uname ="+uname+" AND password ="+pass+" LIMIT 1", null);
        if(res.getCount() == 0)
            return false;
        else{
            uname = uname;
            pass = pass;
            return true;
        }
    }


    public Cursor getFeatured(SQLiteDatabase db){

       //  SQLiteDatabase db = openDatabase(new File("/data/data/com.example.myproject/databases/MyProject.db"),null);
       // String[] cols = new String[]{"sname","scode","catagory","followers"};
        //Cursor res = db.query("ftred_and_sgted",cols,"catagory = ?",new String[]{"featured"},null,null,null);

        String query = "SELECT * FROM ftred_and_sgted";
        Cursor res1 = db.rawQuery(query,null);
        return res1;
    }


    public Cursor getSuggested(SQLiteDatabase db){

        //SQLiteDatabase db = openDatabase(new File("/data/data/com.example.myproject/databases/MyProject.db"),null);
        //String[] cols = new String[]{"sname","scode","catagory","followers"};
        //Cursor res = db.query("ftred_and_sgted",cols,"catagory = ?",new String[]{"suggested"},null,null,null);

        String query2 = "SELECT * FROM ftred_and_sgted";
        Cursor res2 = db.rawQuery(query2,null);
        return res2;
    }

    public Cursor getSubjects(SQLiteDatabase db){

        String query = "SELECT * FROM subject";
        Cursor res = db.rawQuery(query,null);
        return res;
    }

    public Cursor getQuestions(String sub,String nQues,String qType){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM sub1 WHERE qType= ? ORDER BY ? LIMIT ?";
        Cursor res = db.rawQuery(query,new String[]{qType,qType,nQues});
        return res;
    }

    public boolean addResultEntry(String uname,String email,String score,String outOf){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("uname",uname); cv.put("email",email);
        cv.put("score",score); cv.put("outOf",outOf);
        double percentage = Integer.parseInt(score)/Integer.parseInt(outOf);
        cv.put("percentage",String.valueOf(percentage));
        long r = db.insert("result",null,cv);
        return (r!=1? true : false);
    }

}
