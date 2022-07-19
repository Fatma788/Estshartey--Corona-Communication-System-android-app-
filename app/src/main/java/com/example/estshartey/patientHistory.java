package com.example.estshartey;

public class patientHistory {
    public String doctorname;
    public String date;
    public String time;
    public String symptoms;

    public patientHistory(String doctorname, String date, String time, String symptoms) {
        this.doctorname = doctorname;
        this.date = date;
        this.time = time;
        this.symptoms = symptoms;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }
}
