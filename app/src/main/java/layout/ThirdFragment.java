package layout;

import android.content.Context;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import d4.deadline.R;
import d4.deadline.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ThirdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment implements View.OnClickListener{


    private OnFragmentInteractionListener listener;
    private String accountEmail;
    private String accountName;


    private String givenEmailString;


    private EditText mEmailDisplay;
    private TextView emailMatchingText;
    private Button addBtn;
    private ListView groupList;



    public ArrayList<String> emailArrayList = new ArrayList<String>();
    public String[] groupListArr = {};

    ArrayAdapter<String> adapter;
    Tag tag;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_third, container, false);



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mEmailDisplay = (EditText) getView().findViewById(R.id.emalText);
        emailMatchingText = (TextView) getView().findViewById(R.id.showThis);


        addBtn = (Button)getView().findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);


        groupList = (ListView) getView().findViewById(R.id.groupList);


        emailMatchingText.setVisibility(View.GONE);


        accountEmail = LoginActivity.display_Email;
        accountName = LoginActivity.display_name;

        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, emailArrayList);
        groupList.setAdapter(adapter);
    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onClick(View v) {

        givenEmailString = mEmailDisplay.getText().toString();
        emailArrayList.add(givenEmailString);
        mEmailDisplay.setText("");
        updateGroupList();
    }
    public void updateGroupList(){
       adapter.notifyDataSetChanged();
       inGroupHandler();
    }
    public void inGroupHandler(){
        if (emailArrayList.contains(accountEmail)){
            emailMatchingText.setVisibility(View.VISIBLE);
        }
    }

    public interface OnFragmentInteractionListener {
    }




}
