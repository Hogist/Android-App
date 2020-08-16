package com.example.hogist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class MainActivity2 extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button tagmenu, submit, view, tagenterprise;
    EditText VFullName,VMobileNumber,VEmailAddress,VCompanyName,VUserID,VAddress;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String registervendor;
@ServerTimestamp Date Time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        submit = findViewById(R.id.submit);
        view = findViewById(R.id.view);
        tagenterprise = findViewById(R.id.tagenterpise);
        tagmenu = findViewById(R.id.tagmenu);
        VFullName = findViewById(R.id.Name);
        VEmailAddress = findViewById(R.id.email);
        VUserID = findViewById(R.id.UserId);
        VMobileNumber = findViewById(R.id.Phone);
        VCompanyName = findViewById(R.id.companyName);
        VAddress = findViewById(R.id.address);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        tagmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity2.this, MainActivity8.class);
                startActivity(i);

            }
        });


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        final String vemailaddress =VEmailAddress .getText().toString().trim();
                        final String vuserid = VUserID.getText().toString().trim();
                        final String vfullname =VFullName.getText().toString();
                        final String vmobilenumber =  VMobileNumber.getText().toString();
                        final String vaddress = VAddress.getText().toString();
                        final String vcompanyname = VCompanyName.getText().toString();

                        if(TextUtils.isEmpty(vfullname)){
                            VFullName.setError("Name is required.");
                            return;
                        }

                        if (TextUtils.isEmpty(vemailaddress)) {
                            VEmailAddress.setError("Email is required.");
                            return;

                        }
                        if (TextUtils.isEmpty(vuserid)) {
                            VUserID.setError("Userid is required.");
                            return;
                        }

                        if (TextUtils.isEmpty(vmobilenumber)) {
                            VMobileNumber.setError("mobile is required.");
                            return;
                        }
                        if (TextUtils.isEmpty(vaddress)) {
                            VAddress.setError("City is required.");
                            return;
                        }
                        if (TextUtils.isEmpty(vcompanyname)){
                            VCompanyName.setError("Company name is required");
                        }
                    fAuth.createUserWithEmailAndPassword(vemailaddress,vuserid).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity2.this,"User Created",Toast.LENGTH_SHORT).show();
                                registervendor = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("VendorUser").document(registervendor);
                                final Map<String, Object> vendor = new HashMap<>();
                                vendor.put("CreatedDate", FieldValue.serverTimestamp());
                                vendor.put("VFullName",vfullname );
                                vendor.put("VEmailAddress", vemailaddress);
                                vendor.put("VMobileNumber", vmobilenumber);
                                vendor.put("VUserID", vuserid);
                                vendor.put("VCompanyName",vcompanyname );
                                vendor.put("VAddress", vaddress);


                                documentReference.set(vendor).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Log.d(TAG, "onsucess: user profile is created for" + registervendor);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure:" + e.toString());
                                    }
                                });

                            } else {
                                Toast.makeText(MainActivity2.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            });
            }
        }
