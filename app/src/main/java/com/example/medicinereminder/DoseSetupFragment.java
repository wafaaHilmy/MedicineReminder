package com.example.medicinereminder;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.medicinereminder.database.Medicine;
import com.example.medicinereminder.extras.DatePickerFragment;
import com.example.medicinereminder.extras.TimePickerFragment;
import com.example.medicinereminder.viewmodels.AddViewModel;

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
    private AddViewModel addViewModel;

    private Medicine userMedicine;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addViewModel= ViewModelProviders.of(getActivity()).get(AddViewModel.class);
        addViewModel.getUserTreatment().observe(getViewLifecycleOwner(), new Observer<Medicine>() {
            @Override
            public void onChanged(Medicine medicine) {

                Log.d(DoseSetupFragment.class.getSimpleName(), "DoseSetup Fragment onChanged: update medicine user input ******* ");
                Log.d(DoseSetupFragment.class.getSimpleName(), medicine.getTreatmentName());
                userMedicine=medicine;

            }
        });


    }
}
