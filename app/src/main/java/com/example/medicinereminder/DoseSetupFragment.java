package com.example.medicinereminder;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.medicinereminder.extras.DatePickerFragment;
import com.example.medicinereminder.extras.TimePickerFragment;

import java.util.Calendar;
import java.util.Date;

public class DoseSetupFragment extends Fragment  {
    TextView timePickerTv;
    TextView datePickerTv;
   Date currentTime=Calendar.getInstance().getTime();

    String medicineName;
    String type;
    int numOfDoses;
    String repetitionTime;
    int doseAmount;

   /*------------------------------------------------------------------------------------------------------------------*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_dose_setup,container,false);


        timePickerTv=rootView.findViewById(R.id.tv_timepicker);
        timePickerTv.setText(DateFormat.format("hh:mm aa",currentTime));

        datePickerTv=rootView.findViewById(R.id.tv_datePicker);
        datePickerTv.setText(DateFormat.format("dd MMM yyyy",currentTime));


        timePickerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker=new TimePickerFragment();
                timePicker.show(getParentFragmentManager(),"timePicker");
            }
        });

        datePickerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePiker=new DatePickerFragment();
                datePiker.show(getParentFragmentManager(),"datePickerFragment");
            }
        });


        return rootView;
    }
/*---------------------------------------------------------------------------------------------------------------------*/
    public void setTimepickerTv(CharSequence time){
        timePickerTv.setText(time);
    }

    public void setDatePickerTv(CharSequence date){
        datePickerTv.setText(date);
    }

    public void AlarmSetOnClick(View view) {
        //set the alarm manager and insert in data base
    }

    public void setDoseData(String name,String type,int numOfDoses,String repetitionTime,int DoseAmount){
        this.medicineName=name;
        this.type=type;
       this. numOfDoses=numOfDoses;
       this.repetitionTime=repetitionTime;
       this.doseAmount=DoseAmount;

    }


}
