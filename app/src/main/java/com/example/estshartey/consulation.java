package com.example.estshartey;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class consulation extends AppCompatActivity {
    EditText symp;
    String speciality;
    Button cont;
    FirebaseFirestore db;
    FirebaseStorage storage;
    FirebaseDatabase database;
    SharedPreferences sp;
    Spinner sp1;
    menu_item adpter;
    ImageButton history2,cons2,prof2,attachment;
    String []name={"Psychiatry,Dentistry,Neurology,Nutrition,Dermatology,Andrology"};
    int []image={R.drawable.menu1,R.drawable.menu2,R.drawable.menu3,R.drawable.menu4,R.drawable.menu5,R.drawable.menu6};
    private static final int PICK_IMAGE=1;
    Uri imageuri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulation);
        storage = FirebaseStorage.getInstance();
        database= FirebaseDatabase.getInstance();
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fileintent=new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("image/*");
                fileintent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(fileintent,"select picture"),PICK_IMAGE);

            }

        });
        symp=(EditText)findViewById(R.id.choose);
        db=FirebaseFirestore.getInstance();
        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        String Sympto=symp.getText().toString();
        cont=findViewById(R.id.Continue);
        history2=findViewById(R.id.history);
        cons2=findViewById(R.id.consltt);
        prof2=findViewById(R.id.profile);

        cons2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(consulation.this, consulation.class);
                startActivity(intent);
            }
        });
        history2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(consulation.this, History.class);
                startActivity(intent);
            }
        });
        prof2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(consulation.this, profile_doctor.class);
                startActivity(intent);
            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(symp.equals("")){
                    Toast.makeText(getApplicationContext(), "please enter the Symptoms", Toast.LENGTH_SHORT).show();
                }
                else {
                    Map<String, Object> nestedData = new HashMap<>();
                    nestedData.put("Symptoms",Sympto);
                    nestedData.put("Speciality",speciality);

                    db.collection("patient").document(tok)
                            .set(nestedData, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "information saved successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(consulation.this,consultationPayment.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "please try again", Toast.LENGTH_SHORT).show();

                                }
                            });
                }
            }
        });


        sp1=findViewById(R.id.menu);
        adpter=new menu_item(this,name,image);
        sp1.setAdapter(adpter);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                Toast.makeText(getApplicationContext(),name[i],Toast.LENGTH_LONG).show();
                speciality=sp1.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            imageuri=data.getData();
            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                final StorageReference reference=storage.getReference()
                        .child("attach");
                reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference().child("attach")
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
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}