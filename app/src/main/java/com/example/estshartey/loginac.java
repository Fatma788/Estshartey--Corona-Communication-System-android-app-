package com.example.estshartey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;

public class loginac extends AppCompatActivity {
    EditText email, pass;
    Button login;
    TextView signup;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginac);
        email =findViewById(R.id.try1);//email
        pass =findViewById(R.id.try2);//pass
        login =findViewById(R.id.try4);//button
        signup =findViewById(R.id.try5);//signup
        mAuth = FirebaseAuth.getInstance();
        String choose= getIntent().getStringExtra("Choose");
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choose.equals("Patient")){
                    Intent intent=new Intent(loginac.this, registerActivity1.class);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(loginac.this, signupdoctor.class);
                    startActivity(intent);
                } }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emm = email.getText().toString();
                String lgpass = pass.getText().toString();
                if (emm.equals("") || lgpass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_LONG).show();
                } else {
                    // mAuth.signInWithEmailAndPassword(emm, lgpass)
                    mAuth.signInWithEmailAndPassword(emm, lgpass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(getApplicationContext(),"successful login ",Toast.LENGTH_LONG).show();
                            if(choose.equals("Patient")){
                                Intent intent=new Intent(loginac.this, Chronic.class);
                                startActivity(intent);
                            }
                            else{
                                Intent intent=new Intent(loginac.this, session.class);
                                startActivity(intent);
                            }
                        }
                    });
                    mAuth.signInWithEmailAndPassword(emm, lgpass).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"failed to login try again",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}