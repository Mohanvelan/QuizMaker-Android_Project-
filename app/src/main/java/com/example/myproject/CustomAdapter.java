package com.example.myproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    public ArrayList<Question> data = new ArrayList<>();

    public CustomAdapter(ArrayList<Question> arr){
        this.data = arr;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView qno; TextView ques;
        RadioButton opt1; RadioButton opt2;
        RadioButton opt3; RadioButton opt4;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.qno = itemView.findViewById(R.id.tpt1);
            this.ques = itemView.findViewById(R.id.tpt2);
            this.opt1 = itemView.findViewById(R.id.rb1);
            this.opt2 = itemView.findViewById(R.id.rb2);
            this.opt3 = itemView.findViewById(R.id.rb3);
            this.opt4 = itemView.findViewById(R.id.rb4);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_card_layout, parent, false);

        //view.setOnClickListener();

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int i) {
        TextView qno = holder.qno; TextView ques = holder.ques;
        RadioButton rb1 = holder.opt1; RadioButton rb2 = holder.opt2;
        RadioButton rb3 = holder.opt3; RadioButton rb4 = holder.opt4;

        qno.setText(i+1); ques.setText(data.get(i).ques);
        rb1.setText(data.get(i).opt1);  rb2.setText(data.get(i).opt2);
        rb3.setText(data.get(i).opt3);  rb4.setText(data.get(i).opt4);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
