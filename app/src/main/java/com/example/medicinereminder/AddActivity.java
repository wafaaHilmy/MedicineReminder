package com.example.medicinereminder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements AddTreatmentFragment.OnNextButtonClickListener
, TimePickerDialog.OnTimeSetListener , DatePickerDialog.OnDateSetListener {

    DoseSetupFragment doseSetupFragment=new DoseSetupFragment();
    Calendar calender= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().setTitle("add new treatment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.add_medication_container,new AddTreatmentFragment()).commit();
    }

/*------------------------------------------------------------------------------------------------------------------------*/
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
//next button interface callback method implementation
    @Override
    public void OnNextButtonClick() {
        getSupportFragmentManager().beginTransaction().replace(R.id.add_medication_container,doseSetupFragment).commit();
    }
// set callback from TimePickerDialog.OnTimeSetListener interface
    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
        calender.set(Calendar.HOUR,hour);
       calender.set(Calendar.MINUTE,minutes);
        doseSetupFragment.setTimepickerTv(DateFormat.format("hh:mm aa",calender.getTime()));
    }


    // set callback from DatePickerDialog.OnTimeSetListener interface
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        calender.set(Calendar.YEAR,year);
        calender.set(Calendar.MONTH,month);
        calender.set(Calendar.DAY_OF_MONTH,dayOfMonth);

     CharSequence dateFormat= DateFormat.format("dd MMM yyyy",calender.getTime());
     doseSetupFragment.setDatePickerTv(dateFormat);
    }
}