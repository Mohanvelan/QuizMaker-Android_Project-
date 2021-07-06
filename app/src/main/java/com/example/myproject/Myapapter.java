package com.example.myproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class Myadapter extends ArrayAdapter<String> {
    Activity Context;
    String[] title;
    String[] subt;

    Myadapter(Activity c,String[] t,String[] st){
        super(c,R.layout.custom_list1,t);
        this.Context = c;
        this.title = t;
        this.subt = st;
    }

    public View getView(int i, View v, ViewGroup vg){
        //  LayoutInflater lf = Context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"})
        View row = this.Context.getLayoutInflater().inflate(R.layout.custom_list1,null,true);

        TextView tt = (TextView) row.findViewById(R.id.tt);
        TextView ttt = (TextView) row.findViewById(R.id.ttt);

        tt.setText(title[i]);
        ttt.setText(subt[i]+" followers");
        return row;
    }

}
