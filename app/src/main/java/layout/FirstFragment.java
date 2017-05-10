package layout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import d4.deadline.MainMenuNavigation;
import d4.deadline.R;

import static android.R.id.input;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FirstFragment extends Fragment {

    //Variables
    public List<TextView> TextViews = new ArrayList<TextView>();
    public int notesIndex;

    public int textViewsCount;
    public int index;

    private String noteText = "";
    public String tValue;
    public String[] noteTextsArray = new String[10];
    public ArrayList<String> noteTexts = new ArrayList<String>();
    public String savedText;


    //Activity activity = getActivity();
    //SharedPreferences prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    //SharedPreferences.Editor editor = activity.getPreferences(Activity.MODE_PRIVATE).edit();


    private FirstFragment.OnFragmentInteractionListener listener;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_first, container, false);
        Button clickButton = (Button) view.findViewById(R.id.addbutton_whiteboard);
        final LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.linearlayout);


        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Whiteboard");


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        //Reset TextViews-list
        String testString = "";
        textViewsCount = TextViews.size();
        TextViews.clear();

        //notesIndex = 0;
        //notesIndex = sharedPreferences.getInt("notesIndex", 0);


        //region get saved data
        //Only gets called if the app is turned off for a long time.
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
        {
            /*
            notesIndex = savedInstanceState.getInt("notesIndex");
            textViewsCount = savedInstanceState.getInt("textViewsCount");
            tValue = savedInstanceState.getString("textValue0","DEFAULT");
            addViewToLayout(view, myLayout,"Muistissa oli jotain");
            */
        }


        if (textViewsCount > 0)
        {
            for (int i=0; i < textViewsCount; i++)
            {
                String tValue = sharedPreferences.getString("textValue" + String.valueOf(i),"DEFAULT");
                //addViewToLayout(view, myLayout, "" +tValue);
                addViewToLayout(view, myLayout, "" +tValue);
            }
            //tValue = sharedPreferences.getString("textValue0","DEFAULT");
            //addViewToLayout(view, myLayout, "Testin pitäisi olla perässä: " +tValue);
            //addViewToLayout(view, myLayout,"Muistissa oli jotain" +notesIndex);
            //addViewToLayout(view, myLayout,"Tekstikenttiä oli: " +textViewsCount);
            //recreate all notes
        }

        //if created the first time
        else
        {
           addViewToLayoutFromMemory(view, myLayout,"Welcome! Please add a note from the button below.");
        }
        //endregion



        //Add text button
        clickButton.setOnClickListener(new View.OnClickListener() {

          //  EditText et = (EditText) view.findViewById(R.id.addTextLine1);

            @Override
            //Put button functionality here
            public void onClick(View v) {
                //find LinearLayout of mainActivity for some reason
                //Works only here, not in it's own method, no idea why
                final LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.linearlayout);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Title");
                final EditText input = new EditText(getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);


                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        noteText = input.getText().toString();
                        noteTextsArray[0]=noteText;
                        addViewToLayout(view, myLayout,noteText);
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null)
        {
        }

    }

    void addViewToLayout(View view, final LinearLayout myLayout, String text){
        TextView a = new TextView(view.getContext());
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertToDelete(v, myLayout);
            }
        });

        a.setText(text);
        a.setHeight(150);
        a.setGravity(Gravity.CENTER);
        a.setBackgroundResource(R.drawable.rounded_corner);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString("textValue" + String.valueOf(notesIndex), text);

        editor.putString("textValue" + String.valueOf(TextViews.size()), text);
        notesIndex = new Integer(notesIndex + 1);
        //editor.putString("textValue0", text);
        editor.commit();

        index = index +1;
        myLayout.addView(a);
        TextViews.add(a);
        noteTexts.add(text);
    }

    void addViewToLayoutFromMemory(View view, final LinearLayout myLayout, String text){
        TextView a = new TextView(view.getContext());
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertToDeleteWELCOME(v, myLayout);
            }
        });

        a.setText(text);
        a.setHeight(150);
        a.setGravity(Gravity.CENTER);
        a.setBackgroundResource(R.drawable.rounded_corner);
        myLayout.addView(a);
    }


    void showAlertToDelete(final View v, final LinearLayout myLayout){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete?");

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myLayout.removeView(v);
                TextViews.remove(v);

                notesIndex =- 1;

                //ReWrite memory
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                index = 0;
                for (TextView text:TextViews)
                {
                    editor.putString("textValue" + String.valueOf(index), text.getText().toString());
                    index = index +1;
                }


                editor.commit();



            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    void showAlertToDeleteWELCOME(final View v, final LinearLayout myLayout){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete?");

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myLayout.removeView(v);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void onButtonPressed(String uri) {
        if (listener != null) {
            listener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SecondFragment.OnFragmentInteractionListener) {
            listener = (FirstFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String uri);
    }


    public void onPause(Bundle state)
    {
        super.onSaveInstanceState(state);
        //state.putInt("notesIndex", notesIndex);
        //notesIndex = 0;


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();

/*
        editor.putInt("notesIndex", notesIndex);
        editor.putInt("textViewsCount", TextViews.size());

*/
        /*
        int index = 0;
        for (TextView text:TextViews)
        {
            editor.putString("textValue" + String.valueOf(index), text.getText().toString());
            index = index +1;
        }
        */

       // editor.putString("textValue0","wadaw");
        editor.commit();

    }



    public void ReWriteMemory(String text)
    {

    }

    //Store data here
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        SharedPreferences.Editor editor = sharedPreferences.edit();

/*
        int index = 0;
        for (TextView text:TextViews)
        {
            editor.putString("textValue" + String.valueOf(index), text.getText().toString());
            index = index +1;
        }
        */

        editor.putInt("textViewsSize", TextViews.size());
        savedInstanceState.putInt("notesIndex", notesIndex);
        savedInstanceState.putString("textValue0", "testiiiiiii");

        //editor.putInt("notesIndex", notesIndex);
        //editor.putString("textValue0", "testi");
        editor.commit();
    }



}