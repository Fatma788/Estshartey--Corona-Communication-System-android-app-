package com.example.estshartey;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.time.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class History_Doctor extends AppCompatActivity {
    TextView name;
    TextView speciality5;
    TextView date;
    TextView time;
    TextView symptomstext ;


    ImageButton cons5,prof5,his5;

    TextView hdiagnosis;

    TextView hMedicine;
    TextView hDose;
    TextView hEvery;
    TextView hDuration;
    TextView hDoctorcomments;

    TextView htest;
    TextView hDoctorcomments2;
    FirebaseFirestore db;
    SharedPreferences sp;
    TextView hRadiologyType;
    TextView hon;
    TextView hDoctorcomments3;
    RatingBar rt1;
    ImageButton history2,cons2,prof2;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_doctor);
        history2=findViewById(R.id.history5);
        cons2=findViewById(R.id.cons5);
        prof2=findViewById(R.id.prof5);

        rt1 =findViewById(R.id.fratingBarf);
        name =findViewById(R.id.docname);
        speciality5 =findViewById(R.id.spec);
        symptomstext =findViewById(R.id.symmtext);
        hDuration =findViewById(R.id.fdurationtext);

        date =findViewById(R.id.datee);
        LocalDate c=LocalDate.now();
        date.setText(c.toString());
        time =findViewById(R.id.timee);
        LocalTime c1=LocalTime.now();
        time.setText(c1.toString());
        cons5 =findViewById(R.id.cons5);
        prof5 =findViewById(R.id.prof5);
        his5 =findViewById(R.id.history5);
        cons2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History_Doctor.this,session.class);
                startActivity(intent);
            }
        });
        history2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History_Doctor.this, History.class);
                startActivity(intent);
            }
        });
        prof2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(History_Doctor.this, profile_doctor.class);
                startActivity(intent);
            }
        });


        hdiagnosis =findViewById(R.id.fdiag);
        hMedicine =findViewById(R.id.fMedicinetext);
        hDose =findViewById(R.id.fdosetext);
        hEvery =findViewById(R.id.feverytext);
        hDuration =findViewById(R.id.fdurationtext);
        hDoctorcomments =findViewById(R.id.fdoccom);
        htest =findViewById(R.id.fTesttext);
        hDoctorcomments2 =findViewById(R.id.fdoccom2);
        hRadiologyType =findViewById(R.id.fRadiologyTypetext);
        hon =findViewById(R.id.fontext);
       hDoctorcomments3 =findViewById(R.id.fdoccom3);

        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        DocumentReference docRef = db.collection("patient").document(tok);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                rt1.setRating( Float.parseFloat(documentSnapshot.getString("rating")));
                name.setText(documentSnapshot.getString("name"));
                speciality5.setText(documentSnapshot.getString("Speciality"));
                symptomstext.setText(documentSnapshot.getString("Symptoms"));
                hdiagnosis.setText(documentSnapshot.getString("Diagnosis"));
                hMedicine.setText(documentSnapshot.getString("Medicine"));
                hDose.setText(documentSnapshot.getString("Dose"));
                hEvery.setText(documentSnapshot.getString("Every"));
                hDuration.setText(documentSnapshot.getString("Duration"));
                hDoctorcomments.setText(documentSnapshot.getString("Any_comments_MED"));
                htest.setText(documentSnapshot.getString("Tests"));
                hDoctorcomments2.setText(documentSnapshot.getString("Any_comments_Tests"));
                hRadiologyType.setText(documentSnapshot.getString("Radiology"));
                hon.setText(documentSnapshot.getString("Radiology_on"));
                hDoctorcomments3.setText(documentSnapshot.getString("Any_comments_Radiology"));

                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();

            }
        });



















    }
}
