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

public class MainActivity4 extends AppCompatActivity {
    public static final String TAG = "TAG";
Button submit,viewenterprise;
EditText EFullName,EMobileNumber,EEmailAddress,ECompanyName,EUserID,EAddress;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    String registerenterprise;
   @ServerTimestamp Date Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        EFullName=findViewById(R.id.Name);
        EMobileNumber=findViewById(R.id.Phone);
        EEmailAddress=findViewById(R.id.email);
        EUserID=findViewById(R.id.password);
        ECompanyName=findViewById(R.id.companyName);
        EAddress=findViewById(R.id.address);
        submit=findViewById(R.id.submit);
        viewenterprise=findViewById(R.id.viewenterprise);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        viewenterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity4.this,MainActivity7.class);
                startActivity(i);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it=new Intent(MainActivity4.this,MainActivity6.class);
                startActivity(it);

                    final String eemailadsress = EEmailAddress.getText().toString().trim();
                    final String euserid = EUserID.getText().toString().trim();
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
                    if (TextUtils.isEmpty(euserid)) {
                        EUserID.setError("Password is required.");
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
                    fAuth.createUserWithEmailAndPassword(eemailadsress,euserid).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity4.this,"User Created",Toast.LENGTH_SHORT).show();
                                registerenterprise = fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("EnterpriseUser").document(registerenterprise);
                                final Map<String, Object> Enterprice = new HashMap<>();
                                Enterprice.put("CreatedDate", FieldValue.serverTimestamp());
                                Enterprice.put("EFullName", efullname);
                                Enterprice.put("EEmailAddress", eemailadsress);
                                Enterprice.put("EMobileNumber", emobilenumber);
                                Enterprice.put("EUserId", euserid);
                                Enterprice.put("ECompanyName", ecompanyname);
                                Enterprice.put("EAddress", eaddress);
                                documentReference.set(Enterprice).addOnSuccessListener(new OnSuccessListener<Void>() {
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
                                Toast.makeText(MainActivity4.this, "Error!!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }

        });
    }
}