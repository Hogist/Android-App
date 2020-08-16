package com.example.hogist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Loginpage extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button login;
    EditText email, password;
    TextView forgot;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String Loginhogist;
    @ServerTimestamp
    Date Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = findViewById(R.id.login);
        email = findViewById(R.id.Name);
        password = findViewById(R.id.Password);
        forgot = findViewById(R.id.forgot);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    final String Email = email.getText().toString().trim();
                    final String Password = password.getText().toString().trim();

                    if (TextUtils.isEmpty(Email)) {
                        email.setError("Name is required");
                        return;
                    }
                    if (TextUtils.isEmpty(Password)) {
                        password.setError("password is required");
                        return;
                    }
                    Intent intent = new Intent(Loginpage.this, Operation.class);
                    startActivity(intent);


                    fAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {


                                Toast.makeText(Loginpage.this, "Sucessfull", Toast.LENGTH_SHORT).show();
                                Loginhogist = fAuth.getCurrentUser().getUid();

                                DocumentReference documentReference = fstore.collection("Hogist").document(Loginhogist);
                                final Map<String, Object> Hogist = new HashMap<>();
                                Hogist.put("CreatedDate", FieldValue.serverTimestamp());
                                Hogist.put("email", Email);
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

                            } else {
                                Toast.makeText(Loginpage.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                }


            });
        }


    }

