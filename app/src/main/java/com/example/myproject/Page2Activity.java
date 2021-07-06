package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Page2Activity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        TextView t1 = (TextView) findViewById(R.id.textView2);
        Bundle extra = getIntent().getExtras();
        final String sub = extra.getString("subject");
        t1.setText("Topic:"+sub);

        EditText subj = findViewById(R.id.sub);
        final EditText noOfques = findViewById(R.id.noOfques);
        final EditText timing = findViewById(R.id.time);
        @SuppressLint("WrongViewCast") final Spinner quesType = findViewById(R.id.quesType);
        ArrayAdapter adap = new ArrayAdapter(this, android.R.layout.simple_spinner_item, new String[]{"Easy","Medium","Hard"});
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quesType.setAdapter(adap);

        EditText time = findViewById(R.id.time);

        final Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Toast.makeText(this, ,Toast.LENGTH_SHORT).show();
                Intent in = new Intent(Page2Activity.this, MainActivity.class);
                startActivity(in);
            }
        });

        subj.setText(sub);
        Button b2 = findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String nQues = noOfques.getText().toString();
                String qType = quesType.getSelectedItem().toString();
                String time = timing.getText().toString();

                Intent in = new Intent(Page2Activity.this, Page3Activity.class);
                Bundle bdle = new Bundle();
                bdle.putString("sub",sub);
                bdle.putString("nQues",nQues);
                bdle.putString("time",time);
                bdle.putString("qType",qType);
                in.putExtras(bdle);
                startActivity(in);
            }
        });

        //String[] arr1 = {"Trigonometry","Differentiation","Matrices","Geometry"} ;
       // String[] arr2 = {"Medium level","Beginner level","Medium level","Medium level","Hard level"} ;

 /*        ListView lv1 = (ListView) findViewById(R.id.lv2);
       final ArrayAdapter<String> adap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,arr1);
       // adap.addAll(arr1);
        lv1.setAdapter(adap);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String val = adap.getItem(i);
                Toast.makeText(getApplicationContext(),val,Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.botnav1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        Toast.makeText(getApplicationContext(), "Tests", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        Toast.makeText(getApplicationContext(), "Account", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item4:
                        Toast.makeText(getApplicationContext(), "More", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });*/
    }
}