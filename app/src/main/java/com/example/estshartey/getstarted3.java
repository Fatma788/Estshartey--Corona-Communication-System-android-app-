package com.example.estshartey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class getstarted3 extends AppCompatActivity {

    Button getstarted3;
    ImageButton info3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted3);
        getstarted3 = findViewById(R.id.getstarted3);
        info3=findViewById(R.id.imageButton3);

        getstarted3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getstarted3.this, doctororpatient.class);
                startActivity(intent);
            }
        });


        info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getstarted3.this, getstarted4.class);
                startActivity(intent);
            }
        });
    }
}