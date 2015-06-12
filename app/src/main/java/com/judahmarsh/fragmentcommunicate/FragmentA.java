package com.judahmarsh.fragmentcommunicate;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by judah_000 on 11/06/2015.
 */
public class FragmentA extends Fragment implements View.OnClickListener{

    Button button;
    int counter =0;
    Communicator comm;

    //if app gets destroyed this will save current state
    //this links to saveInstanceState

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null){
            //is it made the first time?
            counter = 0;
            //check for previous instance
        }else{
            counter = savedInstanceState.getInt("counter",0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }


    //use this method if there are attributes in fragment
    //other will look for it in main
    //must wait for onCreate to finish
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button = (Button)getActivity().findViewById(R.id.btn_click);

        //com points to main activity with getActiviy
        //getActivity contains respond method
        //so does interface
        comm = (Communicator)getActivity();
        //ini button
        button.setOnClickListener(this);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("counter", counter);
    }
    //the button function
    @Override
    public void onClick(View v) {
        counter ++;
        comm.respond("The button was clicked "+counter+" times");
    }
}
