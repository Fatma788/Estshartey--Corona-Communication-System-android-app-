package com.example.estshartey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerActivity1 extends AppCompatActivity {

    EditText Emaill, pass;

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        Emaill = findViewById(R.id.Email2);
        String mail = Emaill.getText().toString();
        pass=findViewById(R.id.password2);
        String passw=pass.getText().toString();
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passw.length()!=8 && ! passw.matches("(.*[A-Z].*)"))
                {
                    Toast.makeText(getApplicationContext(), "please enter 8 characters password and contain upper case character ", Toast.LENGTH_LONG).show();
                }
                boolean vaild = emailValidator(mail);
                if(! vaild){

                    Toast.makeText(getApplicationContext(), "not vaild email", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();


                    Intent intent = new Intent(registerActivity1.this, registerac.class);
                    intent.putExtra("email",mail);
                    intent.putExtra("password",passw);
                    startActivity(intent);
                }
            }

        });
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)(\\.[A-Za-z]{2,})$";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }

}