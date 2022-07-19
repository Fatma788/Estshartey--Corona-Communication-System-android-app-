package com.example.estshartey;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class prescription extends AppCompatActivity {
    TextView diagnosis;

    TextView Medicine;
    TextView Dose;
    TextView Every;
    TextView Duration;
    TextView Doctorcomments;

    TextView test;
    TextView Doctorcomments2;

    TextView RadiologyType;
    TextView on;
    TextView Doctorcomments3;
    FirebaseFirestore db;
    SharedPreferences sp;
    CardView Diagnosis1;
    CardView Medications;
    CardView MedicalTests;
    CardView Radiology;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        diagnosis =findViewById(R.id.diag);
        Medicine =findViewById(R.id.Medicinetext);
        Dose =findViewById(R.id.dosetext);
        Every =findViewById(R.id.everytext);
        Duration =findViewById(R.id.durationtext);
        Doctorcomments =findViewById(R.id.doccom);
        test =findViewById(R.id.Testtext);
        Doctorcomments2 =findViewById(R.id.doccom2);
        RadiologyType =findViewById(R.id.RadiologyTypetext);
        on =findViewById(R.id.ontext);
        Doctorcomments3 =findViewById(R.id.doccom3);

        db= FirebaseFirestore.getInstance();

        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        DocumentReference docRef = db.collection("patient").document(tok);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                diagnosis.setText(documentSnapshot.getString("Diagnosis"));
                Medicine.setText(documentSnapshot.getString("Medicine"));
                Dose.setText(documentSnapshot.getString("Dose"));
                Every.setText(documentSnapshot.getString("Every"));
                Duration.setText(documentSnapshot.getString("Duration"));
                Doctorcomments.setText(documentSnapshot.getString("Any_comments_MED"));
                test.setText(documentSnapshot.getString("Tests"));
                Doctorcomments2.setText(documentSnapshot.getString("Any_comments_Tests"));
                RadiologyType.setText(documentSnapshot.getString("Radiology"));
                on.setText(documentSnapshot.getString("Radiology_on"));
                Doctorcomments3.setText(documentSnapshot.getString("Any_comments_Radiology"));

                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();

            }
        });

    }
}