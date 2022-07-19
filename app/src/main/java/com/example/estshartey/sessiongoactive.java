package com.example.estshartey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class sessiongoactive extends AppCompatActivity {
    Button go;
    ImageButton history2,cons2,prof2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessiongoactive);
        go=findViewById(R.id.goac);
        history2=findViewById(R.id.historyys);
        cons2=findViewById(R.id.consltt);
        prof2=findViewById(R.id.profill);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//intent
            }
        });

        cons2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sessiongoactive.this, consulation.class);
                startActivity(intent);
            }
        });
        history2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sessiongoactive.this, History.class);
                startActivity(intent);
            }
        });
        prof2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sessiongoactive.this, profile_doctor.class);
                startActivity(intent);
            }
        });
    }
}