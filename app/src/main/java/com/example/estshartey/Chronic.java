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

public class Chronic extends AppCompatActivity {
   Button move;
   String disease;
   CheckBox cardiovascu,Obesity,Arthritis,Cancer,Diabete;
    FirebaseFirestore db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellect_all);
        cardiovascu=findViewById(R.id.cardiovascu);
        Obesity=findViewById(R.id.obe);
        Arthritis=findViewById(R.id.arth);
        Cancer=findViewById(R.id.cance);
        Diabete=findViewById(R.id.diabetes);
        db=FirebaseFirestore.getInstance();
        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        if(cardiovascu.isChecked()){
            disease="cardiovascu";
        }
        else  if( Obesity.isChecked()){
            disease="Obesity";
        }
        else if(Arthritis.isChecked()) {
            disease="Arthritis";
        }
        else if(Cancer.isChecked()) {
            disease="Cancer";
        }
        else if(Diabete.isChecked()) {
            disease="Diabete";
        }

       move = findViewById(R.id.button_save);
       move.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!cardiovascu.isChecked()&& !Diabete.isChecked()&&!Cancer.isChecked()&&
                       !Arthritis.isChecked()&&!Obesity.isChecked()){
                   Toast.makeText(getApplicationContext(), "please select one", Toast.LENGTH_SHORT).show();
               }
               else{
                   Map<String, Object> nestedData = new HashMap<>();
                   nestedData.put("chronic_diseases",disease);
                   db.collection("patient").document(tok)
                           .set(nestedData, SetOptions.merge())
                           .addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void aVoid) {
                                   Toast.makeText(getApplicationContext(), "information saved successfully", Toast.LENGTH_SHORT).show();
                                   Intent intent=new Intent(Chronic.this,food.class);
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