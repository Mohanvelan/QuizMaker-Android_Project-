package com.example.myproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class Myadapter2 extends ArrayAdapter<String> {
    Activity Context;
    String[] title;
    String[] subt;
    int img;

    Myadapter2(Activity c,String[] t,String[] st, int img){
        super(c,R.layout.custom_list1,t);
        this.Context = c;
        this.title = t;
        this.subt = st;
        this.img = img;
    }

    public View getView(int i, View v, ViewGroup vg){
        //  LayoutInflater lf = Context.getLayoutInflater();
        @SuppressLint("ViewHolder")
        View row = this.Context.getLayoutInflater().inflate(R.layout.custom_list1,null,true);

        TextView tt = (TextView) row.findViewById(R.id.tt);
        TextView ttt = (TextView) row.findViewById(R.id.ttt);
        ImageView ii = row.findViewById(R.id.ii);
        tt.setText(title[i]);
        ttt.setText(subt[i]+" followers");
        ii.setImageResource(R.drawable.ic_test);
        return row;
    }

}