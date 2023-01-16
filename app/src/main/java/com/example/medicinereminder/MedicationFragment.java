package com.example.medicinereminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MedicationFragment extends Fragment {
    FloatingActionButton addTreatment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

   View fragmentLayoutView=inflater.inflate(R.layout.fragment_medications,container,false);
// add fab button
        addTreatment= fragmentLayoutView.findViewById(R.id.add_button);
        addTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // open add activity
                Intent launchIntent=new Intent(getContext(),AddActivity.class);
                startActivity(launchIntent);

            }
        });


        return fragmentLayoutView;
    }
/*-------------------------------------------------------------------------------------------------------------*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
/*--------------------------------------------------------------------------------------------------------------*/
}
