package com.example.myproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class SignupActivity extends AppCompatActivity {
    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    EditText mTextUsername, mTextPassword, mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    EditText mClass, mEmail, mAge, msub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        try {
            dbhelper = new DatabaseHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        db = dbhelper.getWritableDatabase();

        mTextUsername = (EditText)findViewById(R.id.edittext_username);
        mTextPassword = (EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button)findViewById(R.id.button_register);
        mTextViewLogin = (TextView)findViewById(R.id.textview_login);

         mEmail = findViewById(R.id.mEmail);
         mClass = findViewById(R.id.mClass);
         mAge = findViewById(R.id.mAge);
          msub = findViewById(R.id.int_sub);


        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();
                String email = mEmail.getText().toString();
                String Class = mClass.getText().toString();
                int age = Integer.parseInt(mAge.getText().toString());
                String int_sub = msub.getText().toString();

                if(pwd.equals(cnf_pwd)){

                    //String[] cols = {"uname","email","Class","interested_sub","password","age"};
                    //Cursor res = db.query("user",cols,"sname = ? and password = ?",new String[]{user,pwd},null,null,null);

                    Cursor res = db.rawQuery("select * from user",null);
                    User u; boolean flag = false;
                    while(res.moveToNext()){
                        String uname = res.getString(0);
                        String pass = res.getString(4);
                        if(user.equals(uname) && pwd.equals(pass)){
                            u = new User(uname,res.getString(1),res.getString(2),res.getString(3),pass,res.getInt(5));
                            dbhelper.storeUserData(u);
                            flag = true; break;
                        }
                        else continue;
                    }

                    if(flag==false){
                        db.execSQL("insert into user values(?,?,?,?,?,?)", new String[]{user,email,Class,int_sub,pwd, String.valueOf(age)});
                        Toast.makeText(getApplicationContext(),"You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(SignupActivity.this,LoginActivity.class);
                        startActivity(moveToLogin);
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

