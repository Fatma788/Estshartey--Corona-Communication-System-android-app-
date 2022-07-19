package com.example.estshartey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class History extends AppCompatActivity {

    ImageButton history1,cons1,prof1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        history1=findViewById(R.id.historyy);
        cons1=findViewById(R.id.conslt);
        prof1=findViewById(R.id.profi);

        history1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(History.this, History.class);
                startActivity(intent);
            }
        });
        cons1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(History.this, consulation.class);
                startActivity(intent);
            }
        });
       prof1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(History.this, profile.class);
                startActivity(intent);
            }
        });
    }
}