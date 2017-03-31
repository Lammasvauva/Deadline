package com.d4.deadline;

import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import d4.deadline.R;
import layout.BlankFragment;
import layout.FirstFragment;
import layout.SecondFragment;
import layout.ThirdFragment;

public class MainMenuNavigation extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener,
SecondFragment.OnFragmentInteractionListener, ThirdFragment.OnFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener{

    private TextView mTextMessage;
    private EditText mEmailDisplay;
    private TextView emailMatchingText;
    String accountEmail;
    String accountName;

    //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        //Tämä kuuntelee navigation barin klikkauksia ja vaihtaa fragmentit
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_whiteboard:
                    fragment = FirstFragment.newInstance();
                    break;
                case R.id.navigation_home:
                    fragment = SecondFragment.newInstance();
                    break;
                case R.id.navigation_dashboard:
                    fragment = ThirdFragment.newInstance();
                    break;
            }
            if(fragment != null)
            {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }

            return true;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_navigation);


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //set default fragment when opening app
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, FirstFragment.newInstance());
        fragmentTransaction.commit();


        //region set default highlighted navigation bar item

        //endregion





    }

}
