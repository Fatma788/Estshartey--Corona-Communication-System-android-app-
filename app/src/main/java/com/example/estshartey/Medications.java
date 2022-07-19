package com.example.estshartey;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Medications extends AppCompatActivity {
  CheckBox idont,ido;
  String take_med;
  EditText medic;
    Button move;
    FirebaseFirestore db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);
        idont=findViewById(R.id.i_don_t_tak);
        ido=findViewById(R.id.i__tak2);
        medic=(EditText) findViewById(R.id.typemedica);
        if(idont.isChecked()){
            take_med="don't take medicine";
        }
        else if(ido.isChecked()){

                take_med=medic.getText().toString();


        }
        move = findViewById(R.id.button_save);
        db= FirebaseFirestore.getInstance();

        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!idont.isChecked()&& !ido.isChecked()){
                    Toast.makeText(getApplicationContext(), "please select one", Toast.LENGTH_SHORT).show();
                }
                else if(ido.isChecked()&&medic.equals("")){
                    Toast.makeText(getApplicationContext(), "please enter your medicines", Toast.LENGTH_SHORT).show();
                }

                else {
                    Map<String, Object> nestedData = new HashMap<>();
                    nestedData.put("take_med", take_med);

                    db.collection("patient").document(tok)
                            .set(nestedData, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "information saved successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Medications.this, smoking.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "information not saved try again", Toast.LENGTH_SHORT).show();

                                }
                            });
                }   }
        });
    }
}