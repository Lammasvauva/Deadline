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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
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

import static android.R.id.input;

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

    private String eventText;



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
        final int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);

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

            //if (curTextViewId > 1)
           // {params.leftMargin=140;}

            textv.setWidth(200);
            textv.setHeight(50);
            textv.setGravity(Gravity.CENTER);
            textv.setBackgroundResource(R.drawable.date_timeline);
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


                final NumberPicker numberPicker = new NumberPicker(getActivity());
                numberPicker.setMaxValue(daysInMonth);
                numberPicker.setMinValue(1);


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Pick a date for the event");
                builder.setView(numberPicker);



                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    int day = (numberPicker.getValue());
                    EventText(relativeLayout, view, day);
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


    public void EventText(final RelativeLayout relativeLayout, final View view, final int day)
    {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
        builder2.setTitle("Enter event name");
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder2.setView(input);



        // Set up the buttons
        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    eventText = input.getText().toString();
                    AddEventToTimeline(eventText, relativeLayout, view, day);
            }
        });

        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder2.show();
    }

    public void AddDatesToTimeline(View view, RelativeLayout relativeLayout, int eventDay)
    {
        final Calendar c = Calendar.getInstance();
        int yy = c.get(Calendar.YEAR);
        int mm = c.get(Calendar.MONTH);
        int dd = c.get(Calendar.DAY_OF_MONTH);
        final int daysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
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

            //if (curTextViewId > 1)
            // {params.leftMargin=140;}

            if(i == (eventDay-1))
            {
                textv.setWidth(350);

            }
            else
            {textv.setWidth(200);}
            textv.setHeight(50);
            textv.setGravity(Gravity.CENTER);
            textv.setBackgroundResource(R.drawable.date_timeline);
            textv.setLayoutParams(params);

            prevTextViewId = curTextViewId;
            relativeLayout.addView(textv, params);
        }
    }


    public void AddEventToTimeline(String text, RelativeLayout relativeLayout, View view, int day)
    {

        final TextView textv = new TextView(view.getContext());
        final RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params2.addRule(RelativeLayout.BELOW, day);
        params2.addRule(RelativeLayout.ALIGN_START, day);
        //params2.addRule(RelativeLayout.RIGHT_OF, 31);

       // params2.leftMargin=10;

        textv.setText(text);
        textv.setLayoutParams(params2);
        textv.setHeight(100);
        textv.setGravity(Gravity.CENTER);
        textv.setBackgroundResource(R.drawable.timeline_note);

        relativeLayout.addView(textv, params2);

        AddDatesToTimeline(view, relativeLayout, day);
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
