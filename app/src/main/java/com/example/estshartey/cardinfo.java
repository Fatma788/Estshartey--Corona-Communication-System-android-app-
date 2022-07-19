package com.example.estshartey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class cardinfo extends AppCompatActivity {
    Button Savecard;
    EditText Cardnum,validupto, CVV, HolderName;
    CheckBox remembercard;
    FirebaseFirestore db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardinfo);
        // on below line we are creating a variable
        // for our pay view and initializing it.
        Savecard  = findViewById(R.id.Savecard);
        Cardnum=findViewById(R.id.num);
        validupto=findViewById(R.id.validupto);
        HolderName=findViewById(R.id.holdername);
        CVV=findViewById(R.id.CVVtext);
        remembercard=findViewById(R.id.remm);
        db=FirebaseFirestore.getInstance();
        Map<String, Object> nestedData = new HashMap<>();
        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        String number=Cardnum.getText().toString();
        String namecard=HolderName.getText().toString();
        // on below line we are setting pay on listener for our card.
        Savecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Cardnum.length()==16 &&!validupto.equals("") &&!CVV.equals("")&&!HolderName.equals(""))

                nestedData.put("numbercard",number);
                nestedData.put("namecard",namecard);
                db.collection("patient").document(tok)
                        .set(nestedData, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"information saved",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"information not saved try again",Toast.LENGTH_LONG).show();

                            }
                        });

                Intent intent=new Intent(cardinfo.this,consultationPayment.class);
                startActivity(intent);

            }
        });
    }
}