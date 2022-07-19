package com.example.estshartey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class getstartedscreen extends AppCompatActivity {
    Button getstarted;
    ImageButton info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstartedscreen);
        getstarted = findViewById(R.id.getstarted1);
        info=findViewById(R.id.imageButton1);

        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getstartedscreen.this, doctororpatient.class);
                startActivity(intent);
            }
        });


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getstartedscreen.this, getstarted2.class);
                startActivity(intent);
            }
        });
    }
}