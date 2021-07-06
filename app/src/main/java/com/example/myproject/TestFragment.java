package com.example.myproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestFragment newInstance(String param1, String param2) {
        TestFragment fragment = new TestFragment();
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
        View v = inflater.inflate(R.layout.fragment_test, container, false);

        ListView ml =  v.findViewById(R.id.test_list);
       // final String[] arr1 = {"Probability & Statistics", "Technical English", "Organic Chemistry", "Basic Physics", "Human Anatomy"};
        // final String[] arr2 = {"5k likes", "23k likes", "10k likes", "4k likes", "1k likes"};

        try {
            dbhelper = new DatabaseHelper(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        SQLiteDatabase db = dbhelper.getWritableDatabase();

        String[] arr1 = new String[10];  String[] arr2 = new String[10];

        Cursor res1 = db.rawQuery("select * from subject",null); int p=0;
        while(res1.moveToNext()){
            arr1[p] = res1.getString(0);
            arr2[p] = res1.getString(4);
            p++;
        }

        final int img = R.drawable.ic_test;


        final Myadapter2 ma = new Myadapter2(getActivity(), arr1,arr2,img);
        ml.setAdapter(ma);

        ml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String value = ma.getItem(position);
                //Toast.makeText(getActivity(),value,Toast.LENGTH_SHORT).show();
                 Intent in = new Intent(getActivity(), Page2Activity.class);
                 in.putExtra("subject", value);
                 startActivity(in);

            }
        });



        return v;
    }
}