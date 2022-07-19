package com.example.estshartey;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
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

public class profile<Private> extends AppCompatActivity {
    TextView name,old,last_cons,namee,phone,email,country,gender,birthday;
Button move1;
    ImageButton history2,cons2,prof2;
    ImageView profpic;
    private static final int PICK_IMAGE=1;
    Uri imageuri;
    FirebaseFirestore db;
    FirebaseStorage storage;
    FirebaseDatabase database;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        storage =FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
        database.getReference()
                .child("userprofilepic").addValueEventListener(new ValueEventListener() {
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
        history2=findViewById(R.id.history);
        cons2=findViewById(R.id.consltt);
        prof2=findViewById(R.id.profile);
        name=findViewById(R.id.name);
       old=findViewById(R.id.old);
       last_cons=findViewById(R.id.rate);
       namee=findViewById(R.id.name3);
       phone=findViewById(R.id.phone2);
       email=findViewById(R.id.email2);
        country=findViewById(R.id.county2);
        gender=findViewById(R.id.gender2);
        birthday=findViewById(R.id.birth2);
        db=FirebaseFirestore.getInstance();

        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        DocumentReference docRef = db.collection("patient").document(tok);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                  country.setText(documentSnapshot.getString("country"));
                  name.setText(documentSnapshot.getString("name"));
                namee.setText(documentSnapshot.getString("name"));
                email.setText(documentSnapshot.getString("email"));
                gender.setText(documentSnapshot.getString("gender"));
                birthday.setText(documentSnapshot.getString("birthday"));
                phone.setText(documentSnapshot.getString("phone"));

                Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();

            }
        });

        move1 = findViewById(R.id.go);
        move1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(profile.this,profile_medi.class);
                startActivity(intent);

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
                        .child("userprofilepic");
                reference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                database.getReference().child("userprofilepic")
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