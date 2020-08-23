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

public class CreateEnterprise extends AppCompatActivity {
    public static final String TAG = "TAG";
Button submit,viewenterprise,tagMenu;
EditText EFullName,EMobileNumber,EEmailAddress,ECompanyName,EPassword,EAddress;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String registerenterprise;
    Long EnterpriseID;
   @ServerTimestamp Date Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createenterprise);
        EFullName=findViewById(R.id.Name);
        EMobileNumber=findViewById(R.id.Phone);
        EEmailAddress=findViewById(R.id.email);
        EPassword=findViewById(R.id.password);
        ECompanyName=findViewById(R.id.companyName);
        EAddress=findViewById(R.id.address);
        submit=findViewById(R.id.submit);
        viewenterprise=findViewById(R.id.viewenterprise);
        tagMenu = findViewById(R.id.tagMenuEnterprise);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        DocumentReference eIdRef = fstore.collection("EnterpriseUserID").document("eID");
        eIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentE = task.getResult();
                    assert documentE != null;
                    if (documentE.exists()) {
                        EnterpriseID = documentE.getLong("enterprise_counter");
                        Log.v("TAG", "enterprise_counter: " + documentE.getData());
                    } else {
                        Log.v("TAG", "No such document");
                    }
                } else {
                    Log.v("TAG", "get failed with ", task.getException());
                }
            }
        });

        viewenterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(CreateEnterprise.this,ViewEnterpriseActivity.class);
                startActivity(i);
            }
        });

        tagMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String eemailadsress = EEmailAddress.getText().toString().trim();
//                Intent i=new Intent(CreateEnterprise.this,Menudetails.class);
//                i.putExtra("EEmailID",eemailadsress);
//                startActivity(i);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    final String eemailadsress = EEmailAddress.getText().toString().trim();
                    final String epassword = EPassword.getText().toString().trim();
                    final String efullname = EFullName.getText().toString();
                    final String emobilenumber = EMobileNumber.getText().toString();
                    final String eaddress = EAddress.getText().toString();
                    final String ecompanyname = ECompanyName.getText().toString();

                    if(TextUtils.isEmpty(efullname)){
                        EFullName.setError("Name is required.");
                        return;
                    }

                    if (TextUtils.isEmpty(eemailadsress)) {
                        EEmailAddress.setError("Email is required.");
                        return;

                    }
                    if (TextUtils.isEmpty(epassword)) {
                        EPassword.setError("Password is required.");
                        return;
                    }

                    if (TextUtils.isEmpty(ecompanyname)) {
                        ECompanyName.setError("CompanyName is required.");
                        return;
                    }
                    if (TextUtils.isEmpty(eaddress)) {
                        EAddress.setError("City is required.");
                        return;
                    }
                    fAuth.createUserWithEmailAndPassword(eemailadsress,epassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {Log.v(TAG,"USER CREATED");

                                Toast.makeText(CreateEnterprise.this,"User Created",Toast.LENGTH_SHORT).show();
                                Intent it=new Intent(CreateEnterprise.this,UserAccount.class);
                                startActivity(it);
                                DocumentReference eIdRef = fstore.collection("EnterpriseUserID").document("eID");
                                eIdRef.update("enterprise_counter",FieldValue.increment(1));
                                EnterpriseID++;
                                String enterpriseID = EnterpriseID.toString();
                                registerenterprise = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("EnterpriseUser").document(registerenterprise);
                                final Map<String, Object> Enterprise = new HashMap<>();
                                Enterprise.put("CreatedDate", FieldValue.serverTimestamp());
                                Enterprise.put("EFullName", efullname);
                                Enterprise.put("EEmailAddress", eemailadsress);
                                Enterprise.put("EMobileNumber", emobilenumber);
                                Enterprise.put("EPassword", epassword);
                                Enterprise.put("ECompanyName", ecompanyname);
                                Enterprise.put("EAddress", eaddress);
                                Enterprise.put("EUserID",enterpriseID);
                                documentReference.set(Enterprise).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Log.d(TAG, "onsucess: user profile is created for" + registerenterprise);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure:" + e.toString());
                                    }
                                });


                            } else {
                                Toast.makeText(CreateEnterprise.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }

        });
    }
}