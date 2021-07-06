package com.example.myproject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoreFragment newInstance(String param1, String param2) {
        MoreFragment fragment = new MoreFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_more, container, false);

        ListView ml = v.findViewById(R.id.more_list);
        final String[] arr = {"MyProfile","Privacy Policy","Version details","Feedback","About Us","SignOut"};

        class Myadapter extends ArrayAdapter<String> {
            Activity Context;
            String[] title;

            Myadapter(Activity c,String[] t){
                super(c,R.layout.about_list,t);
                this.Context = c;
                this.title = t;
            }

            @Override
            public View getView(int i, View v, ViewGroup vg){
                //  LayoutInflater lf = Context.getLayoutInflater();
                @SuppressLint("ViewHolder")
                View row = this.Context.getLayoutInflater().inflate(R.layout.about_list,null,true);

                TextView tt = row.findViewById(R.id.tfm);

                tt.setText(title[i]);
                return row;
            }

        }
        final Myadapter ma = new Myadapter(getActivity(), arr);
        ml.setAdapter(ma);

        ml.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String value = ma.getItem(position);
                Toast.makeText(getActivity(),value,Toast.LENGTH_SHORT).show();
                //   Intent in = new Intent(getApplicationContext(), Page2Activity.class);
                //  in.putExtra("subject", value);
                //  startActivity(in);

            }
        });

        return v;
    }
}