package com.example.myapplication.Classes;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.R;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Calendar;

public class TabOneWorks {

    Calendar calendar=Calendar.getInstance();
    final int YEAR=calendar.get(Calendar.YEAR);
    final int MONTH=calendar.get(Calendar.MONTH);
    int DATE=calendar.get(Calendar.DATE);

    int hour=calendar.get(Calendar.HOUR);
    int min=calendar.get(Calendar.MINUTE);

    View view;
    Button choosePhoto;
    EditText eventName,address,description;
    private TextView date,dateEnd,timeEnd,time;

    private Context context;
    private Activity mActivity;

    public TabOneWorks(Context c, Activity activity) {

        context=c;
        mActivity=activity;
    }

    public void datePicker()
    {
        date=mActivity.findViewById(R.id.dateid);

        DatePickerDialog datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int Year, int Month, int Date) {
                String dates=Date+"/"+(Month+1)+"/"+Year;
                date.setText(dates);


            }
        },YEAR,MONTH,DATE);
        datePickerDialog.show();

    }
   public void dateEndPicker()
    {
        dateEnd=mActivity.findViewById(R.id.dateEndid);

        DatePickerDialog datePickerDialog=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int Year, int Month, int Date) {
                String dates=Date+"/"+(Month+1)+"/"+Year;
                dateEnd.setText(dates);


            }
        },YEAR,MONTH,DATE);
        datePickerDialog.show();

    }

    public void timePicker()
    {


        time=mActivity.findViewById(R.id.timeid);
        TimePickerDialog timePickerDialog=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {
                String times=String.format("%02d:%02d",hour,min);
                time.setText(times);

            }
        },hour,min,true);

        timePickerDialog.show();

    }
    public void timeEndPicker()
    {

        timeEnd=mActivity.findViewById(R.id.timeEndid);
        TimePickerDialog timePickerDialog=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int min) {

                String times=String.format("%02d:%02d",hour,min);
                timeEnd.setText(times);

            }
        },hour,min,true);
        timePickerDialog.show();

    }
    public void createEvent()
    {

    }

}
