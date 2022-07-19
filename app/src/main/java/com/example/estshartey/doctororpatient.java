package com.example.estshartey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class doctororpatient extends AppCompatActivity {
    Button Doctor,patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctororpatient);
        patient  = findViewById(R.id.patient);
        Doctor=findViewById(R.id.Doctor);

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(doctororpatient.this, loginac.class);
                intent.putExtra("Choose","Patient");
                startActivity(intent);
            }
        });
        Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(doctororpatient.this, loginac.class);
                intent.putExtra("Choose","Doctor");
                startActivity(intent);
            }
        });




    }
}