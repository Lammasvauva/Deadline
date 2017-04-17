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
import android.text.InputType;
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
import java.util.List;

import d4.deadline.MainMenuNavigation;
import d4.deadline.R;

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
    final List<TextView> TextViews = new ArrayList<TextView>();
    public int notesIndex;
    private String noteText = "";

    //Lists for saving
    //private String[] noteTexts;
    ArrayList<String> noteTexts = new ArrayList<String>();
    public String savedText;


    //Activity activity = getActivity();
    //SharedPreferences prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    //SharedPreferences.Editor editor = activity.getPreferences(Activity.MODE_PRIVATE).edit();


    private FirstFragment.OnFragmentInteractionListener listener;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null)
        {
            notesIndex = savedInstanceState.getInt("notesIndex");
           // savedText = prefs.getString("noteText", "TÄMÄ EI PAHA POIS");
            //noteTexts = savedInstanceState.getStringArray("noteTexts");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_first, container, false);
        Button clickButton = (Button) view.findViewById(R.id.addbutton_whiteboard);


        //region get saved data
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null)
        {notesIndex = savedInstanceState.getInt("notesIndex");}

        LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.linearlayout);
        //EditText et2 = (EditText) view.findViewById(R.id.addTextLine2);
        //if created the first time
        if (notesIndex > 0)
        {

            TextView a = new TextView(view.getContext());
            TextViews.add(a);

            a.setText("Muistista luku onnistui, indeksi on: " + notesIndex);
            //a.setText(savedText);
            a.setHeight(150);
            a.setGravity(Gravity.CENTER);
            myLayout.addView(a);
            //et2.setVisibility(View.VISIBLE);


            /*
            for (String text: noteTexts)
            {
                TextView a = new TextView(view.getContext());
                a.setText("" + text);
                a.setHeight(150);
                a.setGravity(Gravity.CENTER);
                myLayout.addView(a);

            }
            */
 
        }
        else
        {
            //notesIndex = savedInstanceState.getInt("notesIndex");
            //Recreate notes
            for (int i = 0; i < 1; i++) {
                TextView a = new TextView(view.getContext());
                a.setText("Muisti on tyhjä, indeksi on: " + notesIndex);
                a.setHeight(150);
                a.setGravity(Gravity.CENTER);
                myLayout.addView(a);
                //et2.setVisibility(View.VISIBLE);
            }

        }
        //endregion


        TextView b = new TextView(view.getContext());
        b.setText("Tämä on uusi");
        b.setHeight(150);
        b.setGravity(Gravity.CENTER);
        myLayout.addView(b);
        TextViews.add(b);


        //Add text button
        clickButton.setOnClickListener(new View.OnClickListener() {

            EditText et = (EditText) view.findViewById(R.id.addTextLine1);
            //EditText et2 = (EditText) view.findViewById(R.id.addTextLine2);

            @Override
            //Put button functionality here
            public void onClick(View v) {
                //find LinearLayout of mainActivity for some reason
                //Works only here, not in it's own method, no idea why
                final LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.linearlayout);

                //et.setVisibility(View.VISIBLE);
                //et2.setVisibility(View.VISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Title");

                // Set up the input/
                final EditText input = new EditText(getContext());
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        notesIndex = new Integer(notesIndex + 1);
                        noteText = input.getText().toString();
                        TextView a = new TextView(view.getContext());
                        //a.setText("Tämä on luotu juuri nyt, indeksi on: " +notesIndex);
                        a.setText(noteText + " , indeksi: " +notesIndex);
                        a.setHeight(150);
                        a.setGravity(Gravity.CENTER);
                        myLayout.addView(a);
                        //a.setBackground();
                        //Add to lists to save
                        noteTexts.add(noteText);
                        //noteTexts[notesIndex]="adwad";

                        //editor.putString("noteTexts", noteTexts.toString());
                        //editor.putString("noteText", noteText);
                        //editor.commit();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                //Toast.makeText(getActivity(), "Fragment Button Click", Toast.LENGTH_LONG).show();
                //onButtonPressed("Fragment Button Click");
            }

        });


        //Clicking textviews
        for (TextView a: TextViews)
        {
            a.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    final LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.linearlayout);
                    TextView c = new TextView(view.getContext());
                    c.setText("Tämä on uusi");
                    c.setHeight(150);
                    c.setGravity(Gravity.CENTER);
                    myLayout.addView(c);
                }
            });


        }




        return view;
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
        state.putInt("notesIndex", notesIndex);

    }


    public void CreateWhiteboardNote() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Title");

        // Set up the input
        final EditText input = new EditText(getContext());
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                noteText = input.getText().toString();

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





    //Store data here
    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt("notesIndex", notesIndex);
        //state.putStringArray("noteTexts", noteTexts);

    }


}