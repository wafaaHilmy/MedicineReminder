package com.example.medicinereminder;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.medicinereminder.database.Medicine;
import com.example.medicinereminder.viewmodels.AddViewModel;

import java.util.Arrays;

public class AddTreatmentFragment extends Fragment {

    private EditText medicineNameEditText;
    private Spinner typeSpinner;
    private Spinner numOfDosesSpinner;
    private Spinner repetitionSpinner;
    private Spinner doseAmountSpinner;
    private TextView summaryTextView;

    private AddViewModel addViewModel;

    String medicineName="";
    String type;
    int numOfDose;
    String repetitionTime;
    int doseAmount;


    OnNextButtonClickListener buttonClickListener;

    public interface OnNextButtonClickListener{
         void OnNextButtonClick();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        buttonClickListener=(OnNextButtonClickListener) context;

    }
/*-------------------------------------------------------------------------------------------------------------*/
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayoutView=inflater.inflate(R.layout.fragment_add_treatment,container,false);



        Button nextButton = fragmentLayoutView.findViewById(R.id.button_next);
        medicineNameEditText=fragmentLayoutView.findViewById(R.id.editText_medication_name);
       typeSpinner=fragmentLayoutView.findViewById(R.id.spinner_treatment_type);
       numOfDosesSpinner=fragmentLayoutView.findViewById(R.id.spinner_medication_dose);
       repetitionSpinner=fragmentLayoutView.findViewById(R.id.spinner_medication_repetition);
        doseAmountSpinner=fragmentLayoutView.findViewById(R.id.spinner_dose_amount);
        summaryTextView=fragmentLayoutView.findViewById(R.id.summary_tv);


//set function of next button
       nextButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //call function of interface buttonClickListener to transfer to another fragment
               buttonClickListener.OnNextButtonClick();

               //set Medicine object data and save it in addViewModel
               Medicine userTreatmentInput=new Medicine(medicineName,type,numOfDose,repetitionTime,doseAmount);
              addViewModel.setUserTreatment(userTreatmentInput);


           }
       });


     medicineNameEditText.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
         }
         @Override
         public void onTextChanged(CharSequence charSequence,  int start, int before, int count) {

         }
         @Override
         public void afterTextChanged(Editable editable) {

       medicineName= medicineNameEditText.getText().toString();
             SetSummaryTextView();

         }
     });


        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                type = adapterView.getItemAtPosition(position).toString();
                SetSummaryTextView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
              if(savedInstanceState!=null)
               type=adapterView.getItemAtPosition(0).toString();
            }
        });

        numOfDosesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
              numOfDose = Integer.parseInt(adapterView.getItemAtPosition(position).toString());
                    SetSummaryTextView();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                numOfDose= Integer.parseInt(adapterView.getItemAtPosition(0).toString());
            }
        });

        repetitionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                repetitionTime= adapterView.getItemAtPosition(position).toString();
                    SetSummaryTextView();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                repetitionTime=adapterView.getItemAtPosition(0).toString();
            }
        });

       doseAmountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
               doseAmount = Integer.parseInt(adapterView.getItemAtPosition(position).toString());
                SetSummaryTextView();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
               doseAmount= Integer.parseInt(adapterView.getItemAtPosition(0).toString());
            }
        });



        return fragmentLayoutView;
    }
    /*---------------------------------------------------------------------------------------------------------*/
private void SetSummaryTextView(){

    if(medicineName.length()!=0){
            String summary=String.format(getResources().getString(R.string.medicine_summary),
                    medicineName,doseAmount,type,numOfDose,repetitionTime);
            summaryTextView.setText(summary);}
}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addViewModel= ViewModelProviders.of(getActivity()).get(AddViewModel.class);
        addViewModel.getUserTreatment().observe(getViewLifecycleOwner(), new Observer<Medicine>() {
            @Override
            public void onChanged(Medicine medicine) {
                medicineName=medicine.getTreatmentName();
                medicineNameEditText.setText(medicineName);
                type=medicine.getType();
                typeSpinner.setSelection(Arrays.asList(getResources().getStringArray(R.array.medication_type)).indexOf(type));
                numOfDose=medicine.getDoseNumber();
                numOfDosesSpinner.setSelection(Arrays.asList(getResources().getStringArray(R.array.medication_dose)).indexOf(String.valueOf(numOfDose)));
                repetitionTime=medicine.getRepetitionTime();
               repetitionSpinner.setSelection(Arrays.asList(getResources().getStringArray(R.array.repetition_time)).indexOf(repetitionTime));
                doseAmount=medicine.getDoseAmount();
                doseAmountSpinner.setSelection(Arrays.asList(getResources().getStringArray(R.array.medication_amount)).indexOf(String.valueOf(doseAmount)));

                SetSummaryTextView();

            }
        });
    }


}











