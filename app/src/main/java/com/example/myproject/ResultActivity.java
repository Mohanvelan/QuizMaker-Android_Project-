package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    int correct = 0, wrong = 0;
    double pertge;
    String nQues; double percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle bdle = getIntent().getExtras();
        correct = bdle.getInt("correct");
        wrong = bdle.getInt("wrong");
        nQues = bdle.getString("nQues");
        pertge = bdle.getDouble("pertge");


      //  ArrayList<TestAns> aArr = new ArrayList<>();
        //   totalQues = aArr.size();

      /*  if(bdle != null){
            aArr = bdle.getParcelable("userAns");
        }

        for(int i=0; i<aArr.size(); i++){
            if(aArr.get(i).ans.equals(aArr.get(i).userAns))
               // if(aArr.get(i).ch.equals(aArr.get(i).userCh))
                    correct++;
            else wrong++;
        }*/

         int n = Integer.parseInt(nQues);
        percentage = (correct)*100/n;

        TextView result1 = findViewById(R.id.result1);
        TextView result2 = findViewById(R.id.result2);
        TextView result3 = findViewById(R.id.result3);

        result1.setText(correct+" marks out of"+nQues);
        result2.setText(wrong+" marks");
        result3.setText(percentage+" % scored !");


    }
}