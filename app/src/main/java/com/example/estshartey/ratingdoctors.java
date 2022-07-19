package com.example.estshartey;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ratingdoctors extends AppCompatActivity {
    RatingBar rt;
Button submit;
    FirebaseFirestore db;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingdoctors);
        submit=findViewById(R.id.submitbutton);
        db=FirebaseFirestore.getInstance();

        sp=getSharedPreferences("token",MODE_PRIVATE);
        String tok=sp.getString("doc","nodoc");
        //binding MainActivity.java with activity_main.xml file
        rt = (RatingBar) findViewById(R.id.ratingBar);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> nestedData = new HashMap<>();
                nestedData.put("rating",rt.getRating());

                db.collection("patient").document(tok)
                        .set(nestedData, SetOptions.merge())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"your rating sent successfully",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"failed try again",Toast.LENGTH_LONG).show();

                            }
                        });

            }
        });
        //finding the specific RatingBar with its unique ID
        LayerDrawable stars=(LayerDrawable)rt.getProgressDrawable();

        //Use for changing the color of RatingBar
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
    }
//    public void Call(View v)
//    {
//        // This function is called when button is clicked.
//        // Display ratings, which is required to be converted into string first.
//        TextView t = (TextView)findViewById(R.id.textView2);
//        t.setText("You Rated :"+String.valueOf(rt.getRating()));
//    }

}