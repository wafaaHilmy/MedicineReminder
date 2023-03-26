package com.example.medicinereminder;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.work.BackoffPolicy;
import androidx.work.Data;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.medicinereminder.database.Medicine;
import com.example.medicinereminder.extras.AlarmWork;
import com.example.medicinereminder.extras.DatePickerFragment;
import com.example.medicinereminder.extras.TimePickerFragment;
import com.example.medicinereminder.viewmodels.AddViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    Button alarmSetButton;
    Date startTime=null;



   /*------------------------------------------------------------------------------------------------------------------*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_dose_setup,container,false);


        timePickerTv=rootView.findViewById(R.id.tv_timepicker);
        timePickerTv.setText(DateFormat.format("hh:mm aa",currentTime));

        datePickerTv=rootView.findViewById(R.id.tv_datePicker);
        datePickerTv.setText(DateFormat.format("dd MMM yyyy",currentTime));

        alarmSetButton=rootView.findViewById(R.id.button_alarm_set);


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


        alarmSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                SetWorkManager();
                Toast.makeText(getContext(),"Alarm are setted for Medication",Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }



    /*---------------------------------------------------------------------------------------------------------------------*/
    public void setTimepickerTv(Date time){
        CharSequence timeFormat = DateFormat.format("hh:mm aa",time);
        timePickerTv.setText(timeFormat);
        startTime=time;


    }

    public void setDatePickerTv(Date date){
        CharSequence dateFormat= DateFormat.format("dd MMM yyyy",date);
        datePickerTv.setText(dateFormat);
    }



    private void SetWorkManager() {
       /* AlarmManager alarmManager= (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent receiverIntent=new Intent(getContext(), AlarmBroadcastReceiver.class);
        PendingIntent receiverPendingIntent=PendingIntent.getBroadcast(getContext(),012,receiverIntent,0);
        alarmManager.setRepeating();*/

        Data inputData= new Data.Builder().putString(Medicine.TREATMENT_NAME,userMedicine.getTreatmentName())
                .putInt(Medicine.DOSE_AMOUNT,userMedicine.getDoseAmount())
                .putString(Medicine.TREATMENT_TYPE,userMedicine.getType())
                .build();

        long delayDuration;
        if(startTime==null){
            //that mean that user did not choose start time so make first dose interval the next interval time
           delayDuration=userMedicine.IntervalBetweenDoses()*1000;
        }else {
            delayDuration= startTime.getTime() - new Date().getTime();
        }



        WorkRequest alarmWorkRequest=new PeriodicWorkRequest.Builder(AlarmWork.class,
                /*userMedicine.IntervalBetweenDoses()*/ 15*60, TimeUnit.SECONDS,5,TimeUnit.MINUTES)
                .addTag(userMedicine.getTreatmentName())

                .setBackoffCriteria(BackoffPolicy.LINEAR,5,TimeUnit.MINUTES)
                .setInputData(inputData)
                .build();
        //.setInitialDelay(/*delayDuration*/1*60*100,TimeUnit.MILLISECONDS)


        WorkManager.getInstance(getContext()).enqueue(alarmWorkRequest);

        Log.d("TAG", "onCreate: work manager add new task "+ userMedicine.getTreatmentName());


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addViewModel= ViewModelProviders.of(getActivity()).get(AddViewModel.class);
        addViewModel.getUserTreatment().observe(getViewLifecycleOwner(), new Observer<Medicine>() {
            @Override
            public void onChanged(Medicine medicine) {
                Log.d(DoseSetupFragment.class.getSimpleName(), "DoseSetup Fragment viewModel onChanged: update medicine user input ******* "+medicine.getTreatmentName());
                userMedicine=medicine;

            }
        });


    }
}
