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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateVendors extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button tagmenu, submit, view, tagenterprise;
    EditText VFullName,VMobileNumber,VEmailAddress,VCompanyName,VPassword,VAddress;
    FirebaseFirestore fstore;
    private FirebaseAuth fAuth;
    String registervendor;
    private Long VendorID;
    private Long VendorTagMenuID;
@ServerTimestamp Date Time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createvendors);
        submit = findViewById(R.id.submit);
        view = findViewById(R.id.view);
        tagenterprise = findViewById(R.id.tagenterpise);
        tagmenu = findViewById(R.id.tagmenu);
        VFullName = findViewById(R.id.Name);
        VEmailAddress = findViewById(R.id.email);
        VPassword = findViewById(R.id.UserId);
        VMobileNumber = findViewById(R.id.Phone);
        VCompanyName = findViewById(R.id.companyName);
        VAddress = findViewById(R.id.address);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        DocumentReference vIdRef = fstore.collection("VendorUserID").document("vID");
        vIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentV = task.getResult();
                    assert documentV != null;
                    if (documentV.exists()) {
                        VendorID = documentV.getLong("vendor_counter");
                        Log.v("TAG", "enterprise_counter: " + documentV.getData());
                    } else {
                        Log.v("TAG", "No such document");
                    }
                } else {
                    Log.v("TAG", "get failed with ", task.getException());
                }
            }
        });


        tagmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String vemailaddress =VEmailAddress .getText().toString().trim();
                Intent i = new Intent(CreateVendors.this, Menudetails.class);
                i.putExtra("VendorEmailID",vemailaddress);
                startActivity(i);


            }
        });
        tagenterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String vemailaddress =VEmailAddress .getText().toString().trim();
                Intent e = new Intent(CreateVendors.this,ViewEnterpriseActivity.class);
                e.putExtra("VendorEmailID",vemailaddress);
                startActivity(e);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(CreateVendors.this,ViewVendorActivity.class);
                startActivity(it);
            }
        });


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        final String vemailaddress =VEmailAddress .getText().toString().trim();
                        final String vpassword = VPassword.getText().toString().trim();
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
                        if (TextUtils.isEmpty(vpassword)) {
                            VPassword.setError("Userid is required.");
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
                    fAuth.createUserWithEmailAndPassword(vemailaddress,vpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CreateVendors.this,"User Created",Toast.LENGTH_SHORT).show();
                                DocumentReference vIdRef = fstore.collection("VendorUserID").document("vID");
                                vIdRef.update("vendor_counter",FieldValue.increment(1));
                                VendorID++;
                                String vendorID = VendorID.toString();
                                registervendor = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("VendorUser").document(registervendor);
                                final Map<String, Object> vendor = new HashMap<>();
                                vendor.put("CreatedDate", FieldValue.serverTimestamp());
                                vendor.put("VFullName",vfullname );
                                vendor.put("VEmailAddress", vemailaddress);
                                vendor.put("VMobileNumber", vmobilenumber);
                                vendor.put("VPassword", vpassword);
                                vendor.put("VCompanyName",vcompanyname );
                                vendor.put("VAddress", vaddress);
                                vendor.put("VUserID",vendorID);

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
                                Toast.makeText(CreateVendors.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            });
            }
        }
