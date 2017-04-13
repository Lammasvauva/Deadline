package layout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    final List<TextView> TextViewNotes = new ArrayList<TextView>();
    public int notesIndex;
    private String noteText = "";

    private FirstFragment.OnFragmentInteractionListener listener;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null)
        {notesIndex = savedInstanceState.getInt("notesIndex");}
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
        EditText et2 = (EditText) view.findViewById(R.id.addTextLine2);
        //if created the first time
        if (notesIndex > 0)
        {
            TextView a = new TextView(view.getContext());
            a.setText("Muistista luku onnistui, indeksi on: " + notesIndex);
            a.setHeight(150);
            a.setGravity(Gravity.CENTER);
            myLayout.addView(a);
            et2.setVisibility(View.VISIBLE);
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
                et2.setVisibility(View.VISIBLE);
            }

        }
        //endregion


        clickButton.setOnClickListener(new View.OnClickListener() {


            EditText et = (EditText) view.findViewById(R.id.addTextLine1);
            EditText et2 = (EditText) view.findViewById(R.id.addTextLine2);

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
                        noteText = input.getText().toString();
                        TextView a = new TextView(view.getContext());
                        //a.setText("Tämä on luotu juuri nyt, indeksi on: " +notesIndex);
                        a.setText(noteText + " , indeksi: " +notesIndex);
                        a.setHeight(150);
                        a.setGravity(Gravity.CENTER);
                        myLayout.addView(a);
                        //a.setBackground();

                        //Add to list
                        TextViewNotes.add(a);
                        notesIndex = new Integer(notesIndex + 1);

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

    }


}