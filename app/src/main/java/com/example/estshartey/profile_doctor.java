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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.IOException;


public class profile_doctor extends AppCompatActivity {
    TextView name,old ,rete,name2,phone2,email2,counry2,gender2,birth;
    ImageView profpic,pro,id;
    private static final int PICK_IMAGE=1;
    Uri imageuri;
    SharedPreferences sp1;
    SharedPreferences sp;
    FirebaseFirestore db;
    FirebaseStorage storage;
    FirebaseDatabase database;
    ImageButton prof,his,doc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);
        pro=(ImageView)findViewById(R.id.frame_97);
        id=(ImageView)findViewById(R.id.frame_77);
        storage =FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        database.getReference()
                .child("doctorprofilepic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String profileimage =snapshot.getValue(String.class);
                Picasso.get()
                        .load(profileimage)
                        .into(profpic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference()
                .child("doctorprolicence").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String profileimage =snapshot.getValue(String.class);
                Picasso.get()
                        .load(profileimage)
                        .into(pro);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.getReference()
                .child("doctornationalid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String profileimage =snapshot.getValue(String.class);
                Picasso.get()
                        .load(profileimage)
                        .into(id);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        profpic=(ImageView) findViewById(R.id.doc);
        profpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fileintent=new Intent(Intent.ACTION_GET_CONTENT);
                fileintent.setType("image/*");
                fileintent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(fileintent,"select picture"),PICK_IMAGE);

            }
        });
        sp1 = getSharedPreferences("token_doctor", MODE_PRIVATE);
        String tok_doctor=sp1.getString("doc_doctor","cv");
        his=findViewById(R.id.history);
        doc=findViewById(R.id.doctor);
        prof=findViewById(R.id.profile);
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_doctor.this, session.class);
                startActivity(intent);
            }
        });
        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_doctor.this, History_Doctor.class);
                startActivity(intent);
            }
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile_doctor.this, profile_doctor.class);
                startActivity(intent);
            }
        });
        name=findViewById(R.id.name);
        old=findViewById(R.id.old);
        rete=findViewById(R.id.rate);
        name2=findViewById(R.id.name3);
        phone2=findViewById(R.id.phone2);
        email2=findViewById(R.id.email2);
        counry2=findViewById(R.id.county2);
        gender2=findViewById(R.id.gender2);
        birth=findViewById(R.id.birth2);


        db= FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("doctor").document(tok_doctor);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                counry2.setText(documentSnapshot.getString("country"));
                name.setText(documentSnapshot.getString("name"));
                birth.setText(documentSnapshot.getString("birthday"));
                gender2.setText(documentSnapshot.getString("gender"));
                name2.setText(documentSnapshot.getString("name"));
                phone2.setText(documentSnapshot.getString("phone"));
                email2.setText(documentSnapshot.getString("email"));
                old.setText(documentSnapshot.getString("old"));

            }
        });
        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        DocumentReference docRef2 = db.collection("patient").document(tok);
        docRef2.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                rete.setText(documentSnapshot.getString("rating"));
                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();

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
                        .child("doctorprofilepic");
                reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference().child("doctorprofilepic")
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