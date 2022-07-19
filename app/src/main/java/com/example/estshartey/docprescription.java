package com.example.estshartey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class docprescription extends AppCompatActivity {
    EditText ddiagnosis;

    EditText dMedicine;
    EditText dDose;
    EditText dEvery;
    EditText dDuration;
    EditText dDoctorcomments;
    FirebaseFirestore db;
    SharedPreferences sp;
    EditText dtest;
    EditText dDoctorcomments2;

    EditText dRadiologyType;
    EditText don;
    EditText dDoctorcomments3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docprescription);
        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        ddiagnosis =findViewById(R.id.ddiag);
        dMedicine =findViewById(R.id.dMedicinetext);
        dDose =findViewById(R.id.ddosetext);
        dEvery =findViewById(R.id.deverytext);
        dDuration =findViewById(R.id.ddurationtext);
        dDoctorcomments =findViewById(R.id.ddoccom);
        dtest =findViewById(R.id.dTesttext);
        dDoctorcomments2 =findViewById(R.id.ddoccom2);
        dRadiologyType =findViewById(R.id.dRadiologyTypetext);
        don =findViewById(R.id.dontext);
        dDoctorcomments3 =findViewById(R.id.ddoccom3);

        Map<String, Object> nestedData = new HashMap<>();
         nestedData.put("Diagnosis",ddiagnosis.getText().toString());
        nestedData.put("Medicine",dMedicine.getText().toString());
        nestedData.put("Dose",dDose.getText().toString());
        nestedData.put("Every",dEvery.getText().toString());
        nestedData.put("Duration",dDuration.getText().toString());
        nestedData.put("Any_comments_MED",dDoctorcomments.getText().toString());
        nestedData.put("Tests",dtest.getText().toString());
        nestedData.put("Any_comments_Tests",dDoctorcomments2.getText().toString());
        nestedData.put("Radiology",dRadiologyType.getText().toString());
        nestedData.put("Radiology_on",don.getText().toString());
        nestedData.put("Any_comments_Radiology",dDoctorcomments3.getText().toString());

        db.collection("patient").document(tok)
                .set(nestedData, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //toast not added

                    }
                });


    }

}
