package com.example.medicinereminder.database;

import android.content.Context;

import com.example.medicinereminder.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medicine {
    private Context context;

    private String treatmentName;
    private String type;
    private int doseNumber;
    private String repetitionTime;
    private int doseAmount;

    //constants to get Data from alarmWork data input
    public static final String  TREATMENT_NAME="treatmentName";
    public static final String  TREATMENT_TYPE="treatmentType";
    public static final String DOSE_AMOUNT="doseAmount";

//num of seconds
    public static final int DAY_INTERVAL=24*60*60;
    public static final int WEEK_INTERVAL=24*60*60*7;
    public static final int MONTH_INTERVAL=24*60*60*30;


    public Medicine(Context context,String treatmentName, String type, int doseNumber, String repetitionTime, int doseAmount) {
        this.treatmentName = treatmentName;
        this.type = type;
        this.doseNumber = doseNumber;
        this.repetitionTime = repetitionTime;
        this.doseAmount = doseAmount;
        this.context =context;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public void setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
    }

    public String getRepetitionTime() {
        return repetitionTime;
    }

    public void setRepetitionTime(String repetitionTime) {
        this.repetitionTime = repetitionTime;
    }

    public int getDoseAmount() {
        return doseAmount;
    }

    public void setDoseAmount(int doseAmount) {
        this.doseAmount = doseAmount;
    }


    public int IntervalBetweenDoses(){
     List repetitionList= Arrays.asList(context.getResources().getStringArray(R.array.repetition_time));
        int Index=repetitionList.indexOf(repetitionTime);

        switch (Index){
            case 0 :  return DAY_INTERVAL/doseNumber;
            case 1:   return WEEK_INTERVAL/doseNumber;
            case 2 :  return  MONTH_INTERVAL/doseNumber;
            default:  return  -1;
        }

    }
}
