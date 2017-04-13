package d4.deadline;

import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.TextView;

import layout.BlankFragment;
import layout.FirstFragment;
import layout.SecondFragment;
import layout.ThirdFragment;

public class MainMenuNavigation extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener,
SecondFragment.OnFragmentInteractionListener, ThirdFragment.OnFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener{

    private TextView mTextMessage;


    public static FirstFragment fragment_1;
    public static SecondFragment fragment_2;
    public static ThirdFragment fragment_3;
    public static android.support.v4.app.FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_navigation);
        fragment_1 = new FirstFragment();
        fragment_2 = new SecondFragment();
        fragment_3 = new ThirdFragment();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction_1 = fragmentManager.beginTransaction();
        transaction_1.replace(R.id.fragment_container, fragment_2);
        transaction_1.commit();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /*
        //set default fragment when opening app
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, SecondFragment.newInstance());
        fragmentTransaction.commit();
        */

        //region set default highlighted navigation bar item
        navigation.getMenu().getItem(1).setChecked(true);
        //endregion
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        //Tämä kuuntelee navigation barin klikkauksia ja vaihtaa fragmentit
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_whiteboard:
                    //fragment = FirstFragment.newInstance();
                    fragment = fragment_1;
                    break;
                case R.id.navigation_home:
                    //fragment = SecondFragment.newInstance();
                    fragment = fragment_2;
                    break;
                case R.id.navigation_dashboard:
                    //fragment = ThirdFragment.newInstance();
                    fragment = fragment_3;
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


    public void loadFragment(final Fragment fragment) {

        // create a transaction for transition here
        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        // put the fragment in place
        transaction.replace(R.id.fragment_container, fragment);

        // this is the part that will cause a fragment to be added to backstack,
        // this way we can return to it at any time using this tag
        transaction.addToBackStack(fragment.getClass().getName());

        transaction.commit();
    }


    public void backToFragment(final Fragment fragment) {
        // go back to something that was added to the backstack
        getSupportFragmentManager().popBackStackImmediate(
                fragment.getClass().getName(), 0);
        // use 0 or the below constant as flag parameter
        // FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    //region Set fragment methods
    public void SetFirstFragment()
    {
        FragmentTransaction transaction_2 = MainMenuNavigation.fragmentManager
                .beginTransaction();

        transaction_2.replace(R.id.fragment_container,
                MainMenuNavigation.fragment_2);
        transaction_2.addToBackStack(null);
        transaction_2.commit();

    }

    public void SetSecondFragment()
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, SecondFragment.newInstance());
        fragmentTransaction.commit();
    }

    public void SetSettingsFragment()
    {
        /*
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, ItemFragment.newInstance());
        fragmentTransaction.commit();
        */
    }
    //endregion


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.Settings_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.Logout_settings:
                Toast.makeText(this, "Logout selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }


    @Override
    public void onFragmentInteraction(String uri) {

    }

}
