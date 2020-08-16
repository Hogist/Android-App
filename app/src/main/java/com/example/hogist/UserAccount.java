package com.example.hogist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserAccount extends AppCompatActivity {

    EditText Email,Password;
    Button submit,report;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    String LoginHogist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.useraccount);

        Email=findViewById(R.id.editTextTextemail);
        Password=findViewById(R.id.editTextPassword);
        submit=findViewById(R.id.button12);
        report=findViewById(R.id.button13);
        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = Email.getText().toString().trim();
                final String password = Password.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is required.");
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("password is required.");
                    return;

                }
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(UserAccount.this, "Sucess", Toast.LENGTH_SHORT).show();
                            LoginHogist = firebaseAuth.getCurrentUser().getUid();
                        }else {
                            Toast.makeText(UserAccount.this, "error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = Email.getText().toString().trim();
                final String password = Password.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is required.");
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("password is required.");
                    return;

                }
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(UserAccount.this, "Sucess", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(UserAccount.this,Report.class);
                            startActivity(i);
                            LoginHogist = firebaseAuth.getCurrentUser().getUid();
                        }else {
                            Toast.makeText(UserAccount.this, "error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


    }
}

