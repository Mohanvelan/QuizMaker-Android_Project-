package com.example.myproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
    SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        ImageView aimg1 =  v.findViewById(R.id.aimg1);
        TextView at1 = v.findViewById(R.id.at1); TextView at2 = v.findViewById(R.id.at2);
        TextView asub = v.findViewById(R.id.asub);

        TextView at11 = v.findViewById(R.id.at11); TextView at12 = v.findViewById(R.id.at12);
        TextView at13 = v.findViewById(R.id.at13); TextView at14 = v.findViewById(R.id.at14);
        TextView at15 = v.findViewById(R.id.at15);

        try {
            dbhelper = new DatabaseHelper(getActivity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // User res1 = dbhelper.getUserData();

            sp = getActivity().getSharedPreferences("MyPreferences",Context.MODE_WORLD_READABLE);
            String user = sp.getString("user","");
            String pwd = sp.getString("pwd","");


            SQLiteDatabase db = dbhelper.getWritableDatabase();
            Cursor res = db.rawQuery("select * from user",null);
             User u = null; boolean flag = false;
                while(res.moveToNext()){
                    String uname = res.getString(0);
                    String pass = res.getString(4);
                    if(user.equals(uname) && pwd.equals(pass)){
                         u = new User(uname,res.getString(1),res.getString(2),res.getString(3),pass,res.getInt(5));
                         //dbhelper.storeUserData(u);
                        flag = true; break;
                    }
                    else continue;
                }

         if(flag==true){
            at1.setText(u.uname);
            at2.setText("@Class "+u.Class);
            asub.setText(u.int_sub);
            at11.setText(u.uname); at12.setText(u.email);
            at13.setText(String.valueOf(u.age)); at14.setText("Unknown");
            at15.setText("0 tests");
          }
           
        return v;
    }
}