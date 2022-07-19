package com.example.estshartey;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class session extends AppCompatActivity {
TextView name;
TextView speca;
TextView symptoms;
FirebaseFirestore db;
SharedPreferences sp;
ImageView attach;
Button goactive,accept;
ImageButton doctor,prof,his;
FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        attach=(ImageView)findViewById(R.id.frame_97);
        database= FirebaseDatabase.getInstance();
        database.getReference()
                .child("attach").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String profileimage =snapshot.getValue(String.class);
                Picasso.get()
                        .load(profileimage)
                        .into(attach);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        name=findViewById(R.id.name);
        speca=findViewById(R.id.dermatology);
        symptoms=findViewById(R.id.my_neck_ski);
        db=FirebaseFirestore.getInstance();
        his=findViewById(R.id.history);
        doctor=findViewById(R.id.doctor);
        prof=findViewById(R.id.profile);
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(session.this, session.class);
                startActivity(intent);
            }
        });
        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(session.this, History_Doctor.class);
                startActivity(intent);
            }
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(session.this, profile_doctor.class);
                startActivity(intent);
            }
        });
        goactive=findViewById(R.id.start);
        accept=findViewById(R.id.main_btn);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        DocumentReference docRef = db.collection("patient").document(tok);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                name.setText(documentSnapshot.getString("name"));
               speca.setText(documentSnapshot.getString("Speciality"));
               symptoms.setText(documentSnapshot.getString("Symptoms"));
                //toaste
                //photo

            }
        });
goactive.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(session.this, sessiongoactive.class);
        startActivity(intent);
    }
});



    }
}