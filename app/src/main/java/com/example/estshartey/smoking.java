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

public class smoking extends AppCompatActivity {
    CheckBox somek1,somek2,somek3,somek4;
    String smoke="";
    Button save;
    FirebaseFirestore db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoking);
        somek1=findViewById(R.id.i_don_t_smo);
        somek2=findViewById(R.id.light_smoke);
        somek3=findViewById(R.id.intermediat);
        somek4=findViewById(R.id.heavy_smoke);
        save=findViewById(R.id.button_save);
        db=FirebaseFirestore.getInstance();

        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");

        if(somek1.isChecked()){
            smoke="i_don_t_smo";
        }
        else if(somek2.isChecked()){
            smoke="light_smoke";
        }
        else if(somek3.isChecked()){
            smoke="intermediat";
        }
        else if(somek4.isChecked()){
            smoke="heavy_smoke";
        }


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!somek1.isChecked()&&!somek2.isChecked()&&somek3.isChecked()&&somek4.isChecked()){
                    Toast.makeText(getApplicationContext(), "please select one", Toast.LENGTH_SHORT).show();
                }
                else{
                    Map<String, Object> nestedData = new HashMap<>();
                    nestedData.put("smoke",smoke);

                    db.collection("patient").document(tok)
                            .set(nestedData, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "information saved successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(smoking.this, consulation.class);
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