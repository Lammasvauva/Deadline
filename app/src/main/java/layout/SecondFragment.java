package layout;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.xmlpull.v1.XmlPullParser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import d4.deadline.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    CompactCalendarView compactcalendar_view;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM- yyyy", Locale.getDefault());

    private SecondFragment.OnFragmentInteractionListener listener;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("Timeline");

        final View view = inflater.inflate(R.layout.fragment_second, container, false);
        //compactcalendar_view = (CompactCalendarView) V.findViewById(R.id.compactcalendar_view);
        //compactcalendar_view.setUseThreeLetterAbbreviation(true);

       // Event ev1 = new Event(Color.RED, 1349866756000L, "Teachers' Professional Day");
       // compactcalendar_view.addEvent(ev1);
        //Event ev2 = new Event(Color.RED, 1350039556000L, "Teachers' Professional Day");
        //compactcalendar_view.addEvent(ev2);

        final RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.RelativeLayout);

        //relativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        /*
        XmlPullParser parser = R.layout.timeline_relative;
        AttributeSet attributes = Xml.asAttributeSet(parser);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(view.getContext(),)
        */


        //params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        //params.leftMargin = 107;





        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);
        int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        int margin = 0;
        List<TextView> TextViews = new ArrayList<TextView>();


        int prevTextViewId = 0;

        for (int i = 0; i <daysInMonth; i++   )
        {
            final TextView textv = new TextView(view.getContext());

            textv.setText((i+1)+"."+ mm);
            textv.setTextColor(Color.BLACK);

            int curTextViewId = prevTextViewId + 1;
            textv.setId(curTextViewId);

            final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            params.addRule(RelativeLayout.RIGHT_OF, prevTextViewId);

            if (curTextViewId > 1)
            {params.leftMargin=50;}


            textv.setLayoutParams(params);

            prevTextViewId = curTextViewId;
            relativeLayout.addView(textv, params);
        }





        Button clickButton = (Button) view.findViewById(R.id.AddEventButton_calendar);
        //Add text button
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //Put button functionality here
            public void onClick(View v) {
                //find LinearLayout of mainActivity for some reason
                //Works only here, not in it's own method, no idea why
                final LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.linearlayout);


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                /*
                builder.setTitle("Title");
                final EditText input = new EditText(getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                final EditText input2 = new EditText(getContext());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input2);
*/

                builder.setTitle("TITLE");
                builder.setMessage("TEXT");

                // Set an EditText view to get user input
                final EditText email = new EditText(getContext());
                email.setHint("EMAIL_HINT");
                final EditText password = new EditText(getContext());
                password.setHint("PASSWORD_HINT");
                LinearLayout layout = new LinearLayout(getContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(email);
                layout.addView(password);
                builder.setView(layout);

                DatePicker picker = new DatePicker(getContext());
                picker.setCalendarViewShown(false);

                builder.setTitle("Create Year");
                builder.setView(picker);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //noteText = input.getText().toString();
                        //noteTextsArray[0]=noteText;
                        //addViewToLayout(V, myLayout,noteText);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof SecondFragment.OnFragmentInteractionListener) {
            listener = (SecondFragment.OnFragmentInteractionListener) context;
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
    }




}
