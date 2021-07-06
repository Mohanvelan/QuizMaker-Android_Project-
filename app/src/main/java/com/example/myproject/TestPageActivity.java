package com.example.myproject;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.transform.Result;

public class TestPageActivity extends AppCompatActivity {

    //RecyclerView trecv; //CustomAdapter adap;

    RadioGroup rg;
    RadioButton rb1,rb2,rb3,rb4;
    TextView qno,ques;
    DatabaseHelper dbhelper;

    int currPage = 1;
    ArrayList<Question> qArray;
    ArrayList<TestAns> aArray;
    Button nb, sb;

    int n,cnt,msec;
    String nQues, sub, qType, time;
    TextView tpt1, tpt2, tpt3;

    AlertDialog.Builder builder;
    int correct=0, wrong=0;
    int totalQues=0;
    double pertge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_page);
        qno = findViewById(R.id.qno);
        ques = findViewById(R.id.ques);
        rg = findViewById(R.id.rg);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        nb = findViewById(R.id.nb);

        final Bundle bdle = getIntent().getExtras();

        sub = bdle.getString("sub");
         nQues = bdle.getString("nQues");
         n = Integer.parseInt(nQues);
        time = bdle.getString("time");
         qType = bdle.getString("qType");

        tpt1 = findViewById(R.id.tpt1);
        tpt2 = findViewById(R.id.tpt2);
         tpt3 = findViewById(R.id.tpt3);


        tpt1.setText(sub+" - "+nQues+" Ques");
        tpt2.setText("");


            /*    new Timer().schedule(new TimerTask(){
                    int sec =  Integer.parseInt(time)*60;
                    @Override
                    public void run() {
                        tpt3.setText("Time remaining:"+sec+" s");
                        sec--;
                    }
                },0,1000);*/

        msec =  Integer.parseInt(time)*60*1000;
        cnt = Integer.parseInt(time)*60;
        new CountDownTimer(msec,1000){

            @Override
            public void onTick(long l) {
                tpt3.setText("Time remaining:"+String.valueOf(cnt)+" s");
                cnt--;
            }

            @Override
            public void onFinish() {
                tpt3.setText("Finished...");
            }
        }.start();


        try {
            dbhelper = new DatabaseHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        //Cursor res = db.getQuestions(sub, nQues, qType);

        qArray = new ArrayList<>();
        int i = 1;
        Cursor res = db.rawQuery("select * from sub1",null);
        while (res.moveToNext()) {
            String q = res.getString(1);
            String opt1 = res.getString(2);
            String opt2 = res.getString(3);
            String opt3 = res.getString(4);
            String opt4 = res.getString(5);
            String ans = res.getString(6);
            String ch = res.getString(7);
            qArray.add(new Question(i, q, opt1, opt2, opt3, opt4, ans, ch));
            i++;
        }



        onTest();
       nb.setEnabled(true);

        aArray = new ArrayList<>();
/*
       rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                RadioButton rans = radioGroup.findViewById(i);

               // String opt = null;
                switch (i) {
                    case R.id.rb1:
                        opt = "opt1";
                        break;
                    case R.id.rb2:
                        opt = "opt2";
                        break;
                    case R.id.rb3:
                        opt = "opt3";
                        break;
                    case R.id.rb4:
                        opt = "opt4";
                        break;
                }
                aArray.add(new TestAns(currPage, qArray.get(currPage).ques, qArray.get(currPage).ans, rans.getText().toString()));
                Toast.makeText(getApplicationContext(),"ans added...",Toast.LENGTH_SHORT).show();


            }

    });*/

        /*  rg.setOnClickListener(new View.OnClickListener(){

              @Override
              public void onClick(View view) {
                  int index = rg.getCheckedRadioButtonId();
                  RadioButton rans = findViewById(index);
                  aArray.add(new TestAns(currPage, qArray.get(currPage).ques, qArray.get(currPage).ans, rans.getText().toString()));
                  //Toast.makeText(getApplicationContext(),"ans added...",Toast.LENGTH_SHORT).show();
                  if(qArray.get(currPage).ans.equals(rans.getText().toString()))
                      // if(aArr.get(i).ch.equals(aArr.get(i).userCh))
                      correct++;
                  else wrong++;
                  totalQues++;
              }
          });*/


       //  builder = new AlertDialog.Builder(getApplicationContext());


        nb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    int index = rg.getCheckedRadioButtonId();
                    RadioButton rans = rg.findViewById(index);

                    aArray.add(new TestAns(currPage, qArray.get(currPage-1).ques, qArray.get(currPage-1).ans, rans.getText().toString()));
                    //Toast.makeText(getApplicationContext(),"ans added...",Toast.LENGTH_SHORT).show();
                    int i = currPage -1;
                    String ans = qArray.get(i).ans;
                    String userAns = rans.getText().toString();
                    if (ans.equals(userAns))
                        correct++;
                    else
                        wrong++;

                    totalQues++;


                if(nb.getText().equals("SUBMIT TEST")){
                    //Toast.makeText(getApplicationContext(),"Your Score: "+correct+" Outof "+totalQues,Toast.LENGTH_SHORT).show();
                            pertge = (correct/totalQues)*100;

                           Intent in = new Intent(TestPageActivity.this, ResultActivity.class);
                            //in.putExtra("userAns", aArray);
                            Bundle bdle = new Bundle();
                            bdle.putInt("correct",correct);
                            bdle.putInt("wrong",wrong);
                            bdle.putDouble("pertge",pertge);
                            bdle.putString("nQues",nQues);
                            in.putExtras(bdle);
                            startActivity(in);
                }

                else {
                    currPage++;
                    onTest();
                    if (currPage == 5) {
                        nb.setText("SUBMIT TEST");
                        nb.setBackgroundColor(Color.GREEN);
                        nb.setTextColor(Color.WHITE);
                    }
                }


            }
        });



      /* sb = findViewById(R.id.subButton);
       sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Your Score: "+correct+" Outof "+totalQues,Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage("Are You Sure for submitting the test?").setTitle("Submit Test");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(this,"", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(TestPageActivity.this, ResultActivity.class);
                        in.putExtra("userAns", aArray);
                        startActivity(in);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
               AlertDialog alert= builder.create();
               alert.show();


                pertge = (correct/totalQues)*100;

                Intent in = new Intent(TestPageActivity.this, ResultActivity.class);
                //in.putExtra("userAns", aArray);
                Bundle bdle = new Bundle();
                bdle.putInt("correct",correct);
                bdle.putInt("wrong",wrong);
                bdle.putDouble("pertge",pertge);
                startActivity(in,bdle);
            }
        });*/
    }


    public void onTest(){
          // if(currPage ==5)
            //   nb.setEnabled(false);
            int i = currPage -1;
            qno.setText("Q-"+currPage);
            ques.setText(qArray.get(i).ques);
            rb1.setText(qArray.get(i).opt1); rb2.setText(qArray.get(i).opt2);
            rb3.setText(qArray.get(i).opt3); rb4.setText(qArray.get(i).opt4);
            rg.clearCheck();
    }

}