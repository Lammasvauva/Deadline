package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
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
    public int NotesIndex;

    private FirstFragment.OnFragmentInteractionListener listener;
    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            final View view = inflater.inflate(R.layout.fragment_first, container, false);
            Button clickButton = (Button)view.findViewById(R.id.addbutton_whiteboard);

            //region get saved data
            super.onCreate(savedInstanceState);


            LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.linearlayout);

            EditText et2=(EditText)view.findViewById(R.id.addTextLine2);


            //if created the first time
            if(savedInstanceState == null){}
            else
            {
                NotesIndex = savedInstanceState.getInt("NotesIndex");
                //Recreate notes
                for (int i = 0; i < 5; i++   )
                {
                    TextView a = new TextView(view.getContext());
                    a.setText("Tämä on luotu juuri nyt, indeksi on: " +NotesIndex);
                    a.setHeight(150);
                    a.setGravity(Gravity.CENTER);
                    myLayout.addView(a);
                    //a.setBackground();
                    et2.setVisibility(View.VISIBLE);
                    SetNoteText();
                }

            }
            //endregion








            clickButton.setOnClickListener(new View.OnClickListener()
            {


                EditText et=(EditText)view.findViewById(R.id.addTextLine1);
                EditText et2=(EditText)view.findViewById(R.id.addTextLine2);

                @Override
                //Put button functionality here
                public void onClick(View v)
                {
                    //find LinearLayout of mainActivity for some reason
                    //Works only here, not in it's own method, no idea why
                    LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.linearlayout);

                    TextView a = new TextView(view.getContext());

                    a.setText("Tämä on luotu juuri nyt, indeksi on: " +NotesIndex);
                    a.setHeight(150);
                    a.setGravity(Gravity.CENTER);
                    myLayout.addView(a);
                    //a.setBackground();

                    //Add to list
                    TextViewNotes.add(a);
                    NotesIndex = new Integer(NotesIndex+1);
                    //startActivity(new Intent(FirstFragment.this, Pop.class));

                    //Bring out text field
                    //et.setVisibility(View.VISIBLE);
                    et2.setVisibility(View.VISIBLE);
                    CreateWhiteboardNote();
                    SetNoteText();

                    Toast.makeText(getActivity(), "Fragment Button Click", Toast.LENGTH_LONG).show();
                    onButtonPressed("Fragment Button Click");

                }



                public  void CreateWhiteboardNote()
                {


                }
            });
            return view;
        }






        public void onButtonPressed(String  uri) {
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




    public  void SetNoteText()
    {
        TextView t = (TextView) getView().findViewById(R.id.textView8);
        t.setText("Panoin nappia");
    }


    //Store data here
    @Override
    public void onSaveInstanceState(Bundle state)
    {
        super.onSaveInstanceState(state);
        state.putInt("NotesIndex", NotesIndex);

    }



    }