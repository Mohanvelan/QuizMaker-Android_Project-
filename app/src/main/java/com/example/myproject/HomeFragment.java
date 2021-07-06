package com.example.myproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static HomeFragment newInstance(String param1, String param2) {

        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    DatabaseHelper dbhelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        ListView lv1 = (ListView) v.findViewById(R.id.lv1);
        ListView lv2 = (ListView) v.findViewById(R.id.lv2);

        TextView t1 = (TextView) v.findViewById(R.id.t1);
        TextView t2 = (TextView) v.findViewById(R.id.t2);
        t1.setText("Featured Subjects");
        t2.setText("Suggested");

     //   final String[] arr = {"Advanced Mathematics", "Botany", "Technical English"};
      //  final String[] arr2 = {"Complex theory", "Economics", "History"};
       // final String[] arr11 = {"120 followers","77 followers","54 followers"};
        ///final String[] arr12 = {"106 followers","46 followers","51 followers"};
      //  final ArrayAdapter<String> adap = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, arr);
        //final ArrayAdapter<String> adap2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, arr2);

        try {
            dbhelper = new DatabaseHelper(getActivity().getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        SQLiteDatabase db = dbhelper.getWritableDatabase();

       String[] arr = new String[10];  String[] arr11 = new String[10];
        String[] arr2 = new String[10];  String[] arr12 = new String[10];

        int m=0, p =0;
        Cursor res1 = db.rawQuery("select * from ftred_and_sgted",null);

        while(res1.moveToNext()){
            if(res1.getString(2).equals("featured")){
                if(m>=3) continue;
                arr[m]=res1.getString(0);
                arr11[m++]=res1.getString(3);
            }
            else if(res1.getString(2).equals("suggested"))  {
                if(p>=3) continue;
                arr2[p]=res1.getString(0);
                arr12[p++]=res1.getString(3);
            }
        }


       final Myadapter m1 = new Myadapter(getActivity(),arr,arr11);
        lv1.setAdapter(m1);
       final Myadapter m2 = new Myadapter(getActivity(),arr2,arr12);
        lv2.setAdapter(m2);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String value = m1.getItem(position);
              //  Toast.makeText(getActivity(),value,Toast.LENGTH_SHORT).show();
               Intent in = new Intent(getActivity(), Page2Activity.class);
                in.putExtra("subject", value);
                startActivity(in);

            }
        });

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String value = m2.getItem(position);
                Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
                //Intent in = new Intent(getApplicationContext(),com.example.myproject.Page2Activity.class);
                //in.putExtra("subject",value);
                //startActivity(in);
            }
        });

        return v;
    }
}