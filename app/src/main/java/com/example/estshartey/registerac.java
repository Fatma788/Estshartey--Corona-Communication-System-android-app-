package com.example.estshartey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class registerac extends AppCompatActivity {
    EditText success1,success2;
    TextView success4;
    Button success6;
    ImageButton success5;
    Spinner success3;
    FirebaseFirestore db;
    SharedPreferences sp;
    SharedPreferences.Editor  edit;
    int old;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerac);
        success1=findViewById(R.id.success1);//username

        success2=findViewById(R.id.success2);//phone
        success4=findViewById(R.id.success4);//birthday
        success3 =findViewById(R.id.success3);//gender
        success5=findViewById(R.id.success5);//calendar
        success6 =findViewById(R.id.success6);//register button
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        sp =getSharedPreferences("token",MODE_PRIVATE);
        edit=sp.edit();
        mAuth = FirebaseAuth.getInstance();
        Intent c=getIntent();
        String  email=   c.getStringExtra("email");
        String  passwords=    c.getStringExtra("password");
        success6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=success1.getText().toString();
                String birthdate=success4.getText().toString();
                String Gender=success3.getSelectedItem().toString();
                String phones=success2.getText().toString();
                if(user.equals("")||birthdate.equals("")||Gender.equals("")||phones.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Enter All Fields",Toast.LENGTH_LONG).show();
                }
                else{
                    //intent to login
                    Map<String,Object> note=new HashMap<>();
                    note.put("name",user);
                    note.put("phone",phones);
                    note.put("gender",Gender);
                    note.put("birthday",birthdate);
                    note.put("old",old);
                    note.put("country","Egypt");
                    db=FirebaseFirestore.getInstance();
                    db.collection("patient")
                            .add(note)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    edit.putString("doc",documentReference.getId());
                                  edit.apply();
                                    mAuth.createUserWithEmailAndPassword(email, passwords);
                                    Toast.makeText(getApplicationContext(),"registered successful",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(registerac.this, loginac.class);
                                    startActivity(intent);

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(),"failed to register try again",Toast.LENGTH_LONG).show();

                                }
                            });
                }
            }
        });

        success5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog= new DatePickerDialog(this,
                this::onDateSet,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String date = day+"/"+(month+1)+"/"+year;
        old=2022-year;
        success4.setText(date);
    }

}