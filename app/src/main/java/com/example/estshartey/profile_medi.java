package com.example.estshartey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class profile_medi extends AppCompatActivity {
 TextView dieases,allg,bloodtyp,smoker,medic;
    FirebaseFirestore db;
    SharedPreferences sp;
    ImageButton history2,cons2,prof2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_medi);
        dieases=findViewById(R.id.chronic_txt);
        allg=findViewById(R.id.allg_txt);
        bloodtyp=findViewById(R.id.blood_txt);
        smoker=findViewById(R.id.pastot_smoke);
        medic=findViewById(R.id.past2);
        history2=findViewById(R.id.imageButton_history);
        cons2=findViewById(R.id.conslt);
        prof2=findViewById(R.id.profile);
        cons2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_medi.this, consulation.class);
                startActivity(intent);
            }
        });
        history2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_medi.this, History.class);
                startActivity(intent);
            }
        });
        prof2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_medi.this, profile_doctor.class);
                startActivity(intent);
            }
        });
        db=FirebaseFirestore.getInstance();

        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        DocumentReference docRef = db.collection("patient").document(tok);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                dieases.setText(documentSnapshot.getString("chronic_diseases"));
                allg.setText(documentSnapshot.getString("food"));
                bloodtyp.setText(documentSnapshot.getString("blood"));
                smoker.setText(documentSnapshot.getString("smoke"));
                medic.setText(documentSnapshot.getString("take_med"));

                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
            }
        });




    }
}