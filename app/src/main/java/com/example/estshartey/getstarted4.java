package com.example.estshartey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class getstarted4 extends AppCompatActivity {

    Button getstarted4;
    ImageButton info4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted4);
        getstarted4 = findViewById(R.id.getstarted4);
        info4=findViewById(R.id.imageButton4);

        getstarted4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getstarted4.this, doctororpatient.class);
                startActivity(intent);
            }
        });


        info4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getstarted4.this, doctororpatient.class);
                startActivity(intent);
            }
        });
    }
}