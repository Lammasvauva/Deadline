package d4.deadline;

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
import android.widget.TextView;

import layout.FirstFragment;
import layout.SecondFragment;
import layout.ThirdFragment;

public class MainMenuNavigation extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener,
SecondFragment.OnFragmentInteractionListener, ThirdFragment.OnFragmentInteractionListener{

    private TextView mTextMessage;

    //fragments
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
   // fragmentTransaction.replace();




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = FirstFragment.newInstance();

                    //tässä pitäisi näkyä fragment
                   // mTextMessage.setText(R.strin  g.title_home);
                    //Fragment eka = new FirstFragment();
                   // FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    //transaction.replace(R.id.container, eka).commit();
                    break;
                case R.id.navigation_dashboard:
                    fragment = SecondFragment.newInstance();
                    //mTextMessage.setText(R.string.title_dashboard);
                    break;
                case R.id.navigation_notifications:
                    fragment = ThirdFragment.newInstance();
                   // mTextMessage.setText(R.string.title_notifications);
                    break;
            }
            if(fragment != null)
            {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
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
    }

}
