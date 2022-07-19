package com.example.estshartey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class consultationPayment extends AppCompatActivity {
    private static final String TAG = "CardListActivity";
    Button pay, addcard;
    TextView cardnumber, owner;
    ImageButton history2, cons2, prof2;
    FirebaseFirestore db;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_payment);
        pay = findViewById(R.id.pay);
        addcard = findViewById(R.id.addcard);
        cardnumber = findViewById(R.id.cardnumber);
        owner = findViewById(R.id.owner);
        history2 = findViewById(R.id.history);
        cons2 = findViewById(R.id.cons);
        prof2 = findViewById(R.id.prof);
        db = FirebaseFirestore.getInstance();

        history2 = findViewById(R.id.historyys);
        cons2 = findViewById(R.id.consltt);
        prof2 = findViewById(R.id.profill);
        sp = getSharedPreferences("token", MODE_PRIVATE);
        String tok = sp.getString("doc", "nodoc");
        DocumentReference docRef = db.collection("patient").document(tok);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                cardnumber.setText(documentSnapshot.getString("numbercard"));
                owner.setText(documentSnapshot.getString("namecard"));
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();

            }
        });


        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(consultationPayment.this, cardinfo.class);
                startActivity(intent);
            }
        });
       pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(consultationPayment.this, cardinfo.class);
//                startActivity(intent);
            }
        });

        cons2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(consultationPayment.this, consulation.class);
                startActivity(intent);
            }
        });
        history2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(consultationPayment.this, History.class);
                startActivity(intent);
            }
        });
        prof2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(consultationPayment.this, profile_doctor.class);
                startActivity(intent);
            }
        });
    }
}