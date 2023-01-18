package com.example.medicinereminder.database;

public class Medicine {

    private String treatmentName;
    private String type;
    private int doseNumber;
    private String repetitionTime;
    private int doseAmount;

    public Medicine(String treatmentName, String type, int doseNumber, String repetitionTime, int doseAmount) {
        this.treatmentName = treatmentName;
        this.type = type;
        this.doseNumber = doseNumber;
        this.repetitionTime = repetitionTime;
        this.doseAmount = doseAmount;
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
}
