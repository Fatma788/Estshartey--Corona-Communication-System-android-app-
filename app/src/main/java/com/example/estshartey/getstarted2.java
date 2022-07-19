package com.example.estshartey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class getstarted2 extends AppCompatActivity {


    Button getstarted2;
    ImageButton info2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted2);
        getstarted2 = findViewById(R.id.getstarted2);
        info2=findViewById(R.id.imageButton2);

        getstarted2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getstarted2.this, doctororpatient.class);
                startActivity(intent);
            }
        });


        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getstarted2.this, getstarted3.class);
                startActivity(intent);
            }
        });
    }
}