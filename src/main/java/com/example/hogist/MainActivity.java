package com.example.hogist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button login;
    EditText name, password;
    TextView forgot;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String Loginhogist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        name = findViewById(R.id.Name);
        password = findViewById(R.id.Password);
        forgot = findViewById(R.id.forgot);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() != null) {
            finish();
        }

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, MainActivity3.class);
                    startActivity(i);
                    final String Name = name.getText().toString().trim();
                    final String Password = password.getText().toString().trim();

                    if (TextUtils.isEmpty(Name)) {
                        name.setError("Name is required");
                        return;
                    }
                    if (TextUtils.isEmpty(Password)) {
                        password.setError("password must be >=6 character");
                        return;
                    }
                    fAuth.createUserWithEmailAndPassword(Name, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Sucessfull", Toast.LENGTH_SHORT);
                                Loginhogist = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("Hogist").document(Loginhogist);
                                final Map<String, Object> Hogist = new HashMap<>();
                                Hogist.put("name", Name);
                                Hogist.put("password", Password);
                                documentReference.set(Hogist).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Log.d(TAG, "onsucess: user profile is created for" + Loginhogist);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure:" + e.toString());
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(MainActivity.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });


        }
    }
