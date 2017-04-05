package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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

    private FirstFragment.OnFragmentInteractionListener listener;
    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_first, container, false);


            Button clickButton = (Button)view.findViewById(R.id.addbutton_whiteboard);
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

                    //region Create new Note
                    TextView a = new TextView(view.getContext());

                    a.setText("Tämä on luotu juuri nyt");
                    a.setHeight(150);
                    a.setGravity(Gravity.CENTER);
                   // a.setBackground();


                    myLayout.addView(a);

                    //Bring out text field
                    //et.setVisibility(View.VISIBLE);
                    et2.setVisibility(View.VISIBLE);
                    CreateWhiteboardNote();
                    SetNoteText();

                    //endregion






                    Toast.makeText(getActivity(), "Fragment Button Click", Toast.LENGTH_LONG).show();
                    onButtonPressed("Fragment Button Click");
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


    public  void CreateWhiteboardNote()
        {



        }

    public  void SetNoteText()
    {
        TextView t = (TextView) getView().findViewById(R.id.textView8);
        t.setText("Panoin nappia");

    }

    }