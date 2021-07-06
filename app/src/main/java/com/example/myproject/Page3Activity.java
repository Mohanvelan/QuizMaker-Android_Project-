package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Page3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        final Bundle bdle = getIntent().getExtras();

        String sub = bdle.getString("sub");
        String nQues = bdle.getString("nQues");
        String time = bdle.getString("time");
        String qType = bdle.getString("qType");

        TextView it1 = findViewById(R.id.it1);
        it1.setText("Each Question carries one mark.");
        TextView it2 = findViewById(R.id.it2);
        it2.setText("No negative marks will be given for incorrect answers.");
        TextView it3 = findViewById(R.id.it3);
        it3.setText("Test will be submitted automatically when time up.");
        TextView it4 = findViewById(R.id.it4);
        it4.setText("Best of Luck :)");

        TextView it5 = findViewById(R.id.it5);
        it5.setText(sub);
        TextView it6 = findViewById(R.id.it6);
        it6.setText(nQues);
        TextView it7 = findViewById(R.id.it7);
        it7.setText(time);
        TextView it8 = findViewById(R.id.it8);
        it8.setText(qType);

        Button sbtn = findViewById(R.id.startButton);
        sbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent in = new Intent(Page3Activity.this,TestPageActivity.class);
                in.putExtras(bdle);
                startActivity(in);
            }
        });
    }
}