package com.example.medicinereminder.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.medicinereminder.database.Medicine;

public class AddViewModel extends ViewModel {
    private MutableLiveData<Medicine> userTreatment =new MutableLiveData<>();

    public void setUserTreatment (Medicine userTreatment){
        this.userTreatment.setValue(userTreatment);
    }

    public LiveData<Medicine> getUserTreatment(){
        return userTreatment;
    }
}
