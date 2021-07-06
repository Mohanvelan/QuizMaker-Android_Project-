package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    DatabaseHelper dbhelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            dbhelper = new DatabaseHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //SQLiteDatabase db = dbhelper.getWritableDatabase();

        loadFragment(new HomeFragment());

        /*final ImageView im = (ImageView) findViewById(R.id.im_menu);
        im.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                PopupMenu popup = new PopupMenu(getApplication(),im);
                popup.getMenuInflater().inflate(R.menu.more_menu,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //Toast.makeText(getApplicationContext(),fdfdff,Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
            }
        });*/

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.botnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment frag = null;
        switch (item.getItemId()) {
            case R.id.item1:
                //Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                frag = new HomeFragment();
                break;
            case R.id.item2:
                //Toast.makeText(getApplicationContext(), "Tests", Toast.LENGTH_SHORT).show();
                frag = new TestFragment();
                break;
            case R.id.item3:
                // Toast.makeText(getApplicationContext(), "Account", Toast.LENGTH_SHORT).show();
                frag = new AccountFragment();
                break;
            case R.id.item4:
                frag = new MoreFragment();
                break;
        }
        return loadFragment(frag);
    }


    public boolean loadFragment(Fragment fr) {
        if (fr != null) {
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.fragment_container, fr)
                    .commit();
            return true;
        }
        return false;
    }

}
