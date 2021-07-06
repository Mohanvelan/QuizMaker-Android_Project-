package com.example.myproject;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLDataException;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    ViewGroup progressView;
  //  protected boolean isProgressShowing = false;

    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /* Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        View v = this.getLayoutInflater().inflate(R.layout.progressbar, null);
        dialog.setContentView(v);
        dialog.show();*/

        try {
            dbhelper = new DatabaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        db = dbhelper.getWritableDatabase();

        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegister = (TextView) findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
               // String[] cols = {"uname","email","Class","interested_sub","password","age"};
                //Cursor res = db.query("user",cols,"sname = ? and password = ?",new String[]{user,pwd},null,null,null);

                Cursor res = db.rawQuery("select * from user",null);
                User u;
                 boolean flag = false;
                while(res.moveToNext()){
                    String uname = res.getString(0);
                    String pass = res.getString(4);
                    if(user.equals(uname) && pwd.equals(pass)){
                         u = new User(uname,res.getString(1),res.getString(2),res.getString(3),pass,res.getInt(5));
                         //dbhelper.storeUserData(u);
                        flag = true; break;
                    }
                    else continue;
                }


                if (flag == true) {
                    
                    sp = getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    
                    editor.putString("user",user);
                    editor.putString("pwd",pwd);
                    editor.commit();

                    Intent in = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(in);
                } else
                    Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /*public void showProgressingView() {

        if (!isProgressShowing) {
            View view=findViewById(R.id.progressBar1);
            view.bringToFront();
        }
    }

    public void hideProgressingView() {
        View v = this.findViewById(android.R.id.content).getRootView();
        ViewGroup viewGroup = (ViewGroup) v;
        viewGroup.removeView(progressView);
        isProgressShowing = false;
    }*/
}

