package com.example.myapplication.Fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.Classes.TabOneWorks;
import com.example.myapplication.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Calendar calendar=Calendar.getInstance();
    final int YEAR=calendar.get(Calendar.YEAR);
    final int MONTH=calendar.get(Calendar.MONTH);
    int DATE=calendar.get(Calendar.DATE);

    int hour=calendar.get(Calendar.HOUR);
    int min=calendar.get(Calendar.MINUTE);

    View view;
    Button choosePhoto,createEvent;
    EditText eventName,address,description;
    TextView date,time,dateEnd,timeEnd;

    TabOneWorks tabOneWorks;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1 newInstance(String param1, String param2) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_tab1, container, false);

        eventName=view.findViewById(R.id.nameEdittextId);
        address=view.findViewById(R.id.addressid);
        description=view.findViewById(R.id.descriptionId);

        date=view.findViewById(R.id.dateid);
        time=view.findViewById(R.id.timeid);
        dateEnd=view.findViewById(R.id.dateEndid);
        timeEnd=view.findViewById(R.id.timeEndid);
        createEvent=view.findViewById(R.id.createEventbtnid);

        date.setOnClickListener(this);
        time.setOnClickListener(this);
        dateEnd.setOnClickListener(this);
        timeEnd.setOnClickListener(this);
        createEvent.setOnClickListener(this);

        String dates=DATE+"/"+(MONTH+1)+"/"+YEAR;
        date.setText(dates);
        dateEnd.setText(dates);

        String times=String.format("%02d:%02d",hour,min);
        time.setText(times);
        timeEnd.setText(times);

        tabOneWorks=new TabOneWorks(getContext(),getActivity());






        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.dateid:
                tabOneWorks.datePicker();
                break;
            case R.id.timeid:
                tabOneWorks.timePicker();
                break;
            case R.id.dateEndid:
                tabOneWorks.dateEndPicker();
                break;
            case R.id.timeEndid:
                tabOneWorks.timeEndPicker();
            case R.id.createEventbtnid:
                tabOneWorks.createEvent();
        }

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
