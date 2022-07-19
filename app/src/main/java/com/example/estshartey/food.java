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

public class food extends AppCompatActivity {
    Button move;
    CheckBox egg,wheat,peanut,treenut,soy,Shellfish,fish,DrugAllergies,other,apple;
    String food;
    FirebaseFirestore db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        egg=findViewById(R.id.optionegge);
        wheat=findViewById(R.id.optionw);
        peanut=findViewById(R.id.optionpe);
        treenut=findViewById(R.id.optiontree);
        soy=findViewById(R.id.optionsoy);
        Shellfish=findViewById(R.id.optionShellfish);
        fish=findViewById(R.id.optionw);
//        DrugAllergies=findViewById(R.id.optionOther);
        apple=findViewById(R.id.apple);
        other=findViewById(R.id.optionOthers);
        db=FirebaseFirestore.getInstance();

        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        if(egg.isChecked()){
            food="egg";
        }
       else if(wheat.isChecked()){
            food="wheat";
        }
        else if(peanut.isChecked()){
            food="peanut";
        }
        else if(treenut.isChecked()){
            food="treenut";
        }
        else if(soy.isChecked()){
            food="soy";
        }

        else if(Shellfish.isChecked()){
            food="Shellfish";
        }
        else if(fish.isChecked()){
            food="fish";
        }
//        else if(DrugAllergies.isChecked()){
//            food="DrugAllergies";
//        }
        else if(apple.isChecked()){
            food="apple";
        }
        else if(other.isChecked()){
            food="other";
        }

        move = findViewById(R.id.button_save);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!egg.isChecked()&& !wheat.isChecked()&&!other.isChecked()&&
                        !apple.isChecked()&&!fish.isChecked()
                        &&!Shellfish.isChecked()&&!soy.isChecked()&&!treenut.isChecked()&&!peanut.isChecked()){
                    Toast.makeText(getApplicationContext(), "please select one", Toast.LENGTH_SHORT).show();
                }
                else{

                    Map<String, Object> nestedData = new HashMap<>();
                    nestedData.put("food",food);

                    db.collection("data").document("one")
                            .set(tok, SetOptions.merge())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "information saved successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(food.this, blood.class);
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
