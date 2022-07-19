package com.example.estshartey;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class signup_doctor extends AppCompatActivity {
    EditText phone,names,national;
    TextView txtphone,txtemail;
    FirebaseFirestore db;
    SharedPreferences sp1;
    private FirebaseAuth mAuth;
    SharedPreferences.Editor  edit;
    TextView success4;
    Spinner success3;
    Spinner success33;
    Button move;
    int old;
    Button success5d;
    Button success6d;
    ImageButton PL,NI;
    private static final int PICK_IMAGE=1;
    Uri imageuri,imageuri2;
    Boolean plclick,niclick=false;
    FirebaseStorage storage;
    FirebaseDatabase database;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_doctor);
        storage =FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        PL=(ImageButton)findViewById(R.id.up1);
        NI=(ImageButton)findViewById(R.id.up2);
        PL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fileintent=new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("image/*");
                fileintent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(fileintent,"select picture"),PICK_IMAGE);
                plclick=true;

            }

        });
        NI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fileintent=new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("image/*");
                fileintent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(fileintent,"select picture"),PICK_IMAGE);
                niclick=true;

            }

        });
        phone = findViewById(R.id.success1d);
        sp1 = getSharedPreferences("token_doctor", MODE_PRIVATE);
        edit = sp1.edit();
        mAuth = FirebaseAuth.getInstance();
        success4 = findViewById(R.id.success4d);
        national = findViewById(R.id.up2);
        success5d = findViewById(R.id.success5d);
        success3 = findViewById(R.id.success3d);
        success33 = findViewById(R.id.phys);
        names = findViewById(R.id.success2d);
        move = findViewById(R.id.next);
        txtemail = findViewById(R.id.tet_email);
        success6d = findViewById(R.id.success6d);
        txtphone = findViewById(R.id.tet_phone);
        String birthdate = success4.getText().toString();
        Intent c = getIntent();
        String email = c.getStringExtra("email");
        String passwords = c.getStringExtra("password");
        String Gender = success3.getSelectedItem().toString();
        String special = success33.getSelectedItem().toString();
        String phone_doctor = phone.getText().toString();
        String name = names.getText().toString();
        String id = national.getText().toString();
        success6d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.equals("") || phone_doctor.equals("") || Gender.equals("") || id.equals("") || special.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_LONG).show();
                } else {
                    Map<String, Object> note = new HashMap<>();
                    note.put("name", name);
                    //photo
                    note.put("phone", phone_doctor);
                    note.put("old",old);
                    note.put("gender", Gender);
                    note.put("birthday", birthdate);
                    note.put("national", id);
                    note.put("country","egypt");
                    note.put("specitity", special);
                    note.put("email", email);
                    note.put("password", passwords);
                    db = FirebaseFirestore.getInstance();
                    db.collection("doctor")
                            .add(note)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    edit.putString("doc_doctor", documentReference.getId());
                                    edit.apply();
                                    mAuth.createUserWithEmailAndPassword(email, passwords);
                                    Toast.makeText(getApplicationContext(),"registered successfully",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(signup_doctor.this, loginac.class);
                                    startActivity(intent);

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(),"registertion failed try again",Toast.LENGTH_LONG).show();

                                }
                            });
                    success5d.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            showDatePickerDialog();

                        }
                    });
                }


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

        success4.setText(date);
        old=2022-year;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            imageuri=data.getData();

            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                if(plclick){
                    final StorageReference reference=storage.getReference()
                            .child("doctorprolicence");
                    reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    database.getReference().child("doctorprolicence")
                                            .setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getApplicationContext(),"image uploaded",Toast.LENGTH_LONG).show();

                                        }
                                    });


                                }
                            });

                        }
                    });
                }
                else if (niclick){
                    final StorageReference reference=storage.getReference()
                            .child("doctornationalid");
                    reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    database.getReference().child("doctornationalid")
                                            .setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getApplicationContext(),"image uploaded",Toast.LENGTH_LONG).show();

                                        }
                                    });


                                }
                            });

                        }
                    });
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}













