package com.example.estshartey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class blood extends AppCompatActivity {
    CheckBox a1,a2,b1,b2,o1,o2,ab1,ab2;
    String blood;
    Button move;
    FirebaseFirestore db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boold);
        a1=findViewById(R.id.a_);
        a2=findViewById(R.id.a_2);
        b1=findViewById(R.id.b_3);
       b2=findViewById(R.id.a_4);
        o1=findViewById(R.id.a_5);
        o2=findViewById(R.id.a_6);
        ab1=findViewById(R.id.a_8);
        ab2=findViewById(R.id.a_7);
        move = findViewById(R.id.button_save);
        if(a1.isChecked()){
            blood="A+";
        }
        else if(a2.isChecked()){
            blood="A-";
        }
        else if(b1.isChecked()){
            blood="B+";
        }
        else if(b2.isChecked()){
            blood="B-";
        }
        else if(o1.isChecked()){
            blood="O+";
        }

        else if(o2.isChecked()){
            blood="O-";
        }
        else if(ab1.isChecked()){
            blood="AB+";
        }
        else if(ab2.isChecked()){
            blood="AB-";
        }
        db= FirebaseFirestore.getInstance();

        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!a1.isChecked()&& !a2.isChecked()&&!b1.isChecked()&&
                        !b2.isChecked()&&!o1.isChecked()&&!o2.isChecked()
                        &&!ab1.isChecked()&&!ab2.isChecked()){
                    Toast.makeText(getApplicationContext(), "please select one", Toast.LENGTH_SHORT).show();
                }
                else{

                    Map<String, Object> nestedData = new HashMap<>();
                    nestedData.put("blood",blood);

                    db.collection("patient").document(tok)
                            .set(nestedData, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "information saved successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(com.example.estshartey.blood.this,Medications.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "information not saved try again", Toast.LENGTH_SHORT).show();

                                }
                            });

            }
        }

    });
}
}