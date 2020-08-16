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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.type.Date;

import java.util.HashMap;
import java.util.Map;

public class CreateMenu extends AppCompatActivity {
    public static final String LOG= "LOG";
    private Button viewMenu,submit;
    private EditText mBreakfast, mLunch, mSnacks, mDinner, mVendorPrice, mEnterPrisePrice;
    private FirebaseFirestore menuStore;
    private @ServerTimestamp Date timeStamp;
    private Long BreakfastId;
    private Long LunchId;
    private Long SnacksId;
    private Long DinnerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createmenu);
        mBreakfast= findViewById(R.id.editTextTextPersonName4);
        mLunch= findViewById(R.id.editTextTextPersonName6);
        mSnacks= findViewById(R.id.editTextTextPersonName7);
        mDinner= findViewById(R.id.editTextTextPersonName5);
        mVendorPrice= findViewById(R.id.editTextTextPersonName8);
        mEnterPrisePrice= findViewById(R.id.editTextTextPersonName9);
        submit = (Button) findViewById(R.id.create_menu_submit);
        viewMenu=(Button)findViewById(R.id.create_menu_viewMenu);
        menuStore= FirebaseFirestore.getInstance();

        DocumentReference bIdRef = menuStore.collection("BreakfastID").document("bID");
        bIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentB = task.getResult();
                    assert documentB != null;
                    if (documentB.exists()) {
                        BreakfastId = documentB.getLong("breakfast_counter");
                        Log.v("TAG", "breakfast_counter: " + documentB.getData());
                    } else {
                        Log.v("TAG", "No such document");
                    }
                } else {
                    Log.v("TAG", "get failed with ", task.getException());
                }
            }
        });

        DocumentReference lunchIdRef = menuStore.collection("LunchID").document("lunchID");
        lunchIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentL = task.getResult();
                    assert documentL != null;
                    if (documentL.exists()) {
                        LunchId = documentL.getLong("lunch_counter");
                        Log.v("TAG", "lunch_counter: " + documentL.getData());
                    } else {
                        Log.v("TAG", "No such document");
                    }
                } else {
                    Log.v("TAG", "get failed with ", task.getException());
                }
            }
        });

        DocumentReference sIdRef = menuStore.collection("SnacksID").document("sID");
        sIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentS = task.getResult();
                    assert documentS != null;
                    if (documentS.exists()) {
                        SnacksId = documentS.getLong("snacks_counter");
                        Log.v("TAG", "breakfast_counter: " + documentS.getData());
                    } else {
                        Log.v("TAG", "No such document");
                    }
                } else {
                    Log.v("TAG", "get failed with ", task.getException());
                }
            }
        });

        DocumentReference dIdRef = menuStore.collection("DinnerID").document("dID");
        dIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentD = task.getResult();
                    assert documentD != null;
                    if (documentD.exists()) {
                        DinnerId = documentD.getLong("dinner_counter");
                        Log.v("TAG", "dinner_counter: " + documentD.getData());
                    } else {
                        Log.v("TAG", "No such document");
                    }
                } else {
                    Log.v("TAG", "get failed with ", task.getException());
                }
            }
        });



        viewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateMenu.this,Menudetails.class));
            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              final String breakfast= mBreakfast.getText().toString().trim();
              final String lunch= mLunch.getText().toString().trim();
              final String snacks= mSnacks.getText().toString().trim();
              final String dinner= mDinner.getText().toString().trim();
              final String vendorPrice= mVendorPrice.getText().toString();
              final String enterPrisePrice= mEnterPrisePrice.getText().toString();

              if(TextUtils.isEmpty(breakfast)){
                  mBreakfast.setError("BreakFast Menu is Required");
                  return;
              }
              if(TextUtils.isEmpty(lunch)){
                  mLunch.setError("Lunch Menu is Required");
                  return;
              }
              if(TextUtils.isEmpty(snacks)){
                  mSnacks.setError("Snacks Menu is Required");
                  return;
              }
              if(TextUtils.isEmpty(dinner)){
                  mDinner.setError("Dinner Menu is Required");
                  return;
              }
              if(TextUtils.isEmpty(vendorPrice)){
                  mVendorPrice.setError("Vendor Price Required");
                  return;
              }
              if(TextUtils.isEmpty(enterPrisePrice)){
                  mEnterPrisePrice.setError("EnterPrise Price is Required");
                  return;
              }
                DocumentReference bIdRef = menuStore.collection("BreakfastID").document("bID");
              bIdRef.update("breakfast_counter",FieldValue.increment(1));
              BreakfastId ++;
                DocumentReference bRef= menuStore.collection("MenuBreakfast").document();
                final String breakfastId= BreakfastId.toString();
                final Map<String,Object> bf= new HashMap<>();
                bf.put("LatestChangesMade", FieldValue.serverTimestamp());
                bf.put("Breakfastitemlist",breakfast);
                bf.put("EPrice",enterPrisePrice);
                bf.put("VPrice",vendorPrice);
                bf.put("MenubreakfastID",breakfastId);
                bRef.set(bf).addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(LOG,"onSuccess: breakfast menu is created in "+breakfastId);
                        Toast.makeText(CreateMenu.this, "breakfast menu created ", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(LOG, "onFailure:" + e.toString());
                        Toast.makeText(CreateMenu.this, "problem in breakfast menu creation ", Toast.LENGTH_SHORT).show();
                    }
                });

                DocumentReference lunchIdRef = menuStore.collection("LunchID").document("lunchID");
                lunchIdRef.update("lunch_counter",FieldValue.increment(1));
                LunchId++;
                DocumentReference lRef= menuStore.collection("MenuLunch").document();
                final String lunchId= LunchId.toString();
                Map<String,Object> lunchMap= new HashMap<>();
                lunchMap.put("LatestChangesMade", FieldValue.serverTimestamp());
                lunchMap.put("LunchItemList",lunch);
                lunchMap.put("EPrice",enterPrisePrice);
                lunchMap.put("VPrice",vendorPrice);
                lunchMap.put("MenuLunchID",lunchId);
                lRef.set(lunchMap).addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("CreateLunchMenu","onSuccess: lunch menu is created in "+lunchId);
                        Toast.makeText(CreateMenu.this, "Lunch menu created ", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FailedLunchMenu", "onFailure:" + e.toString());
                        Toast.makeText(CreateMenu.this, "problem in lunch menu creation", Toast.LENGTH_SHORT).show();
                    }
                });

                DocumentReference sIdRef = menuStore.collection("SnacksID").document("sID");
                sIdRef.update("snacks_counter",FieldValue.increment(1));
                SnacksId++;
                DocumentReference sRef= menuStore.collection("MenuSnacks").document();
                final String snacksId= SnacksId.toString();
                Map<String,Object> snacksMap= new HashMap<>();
                snacksMap.put("LatestChangesMade", FieldValue.serverTimestamp());
                snacksMap.put("SnacksitemList",snacks);
                snacksMap.put("EPrice",enterPrisePrice);
                snacksMap.put("VPrice",vendorPrice);
                snacksMap.put("MenuSnacksID",snacksId);
                sRef.set(snacksMap).addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("CreateSnacksMenu","onSuccess: snacks menu is created in "+snacksId);
                        Toast.makeText(CreateMenu.this, "snacks menu created", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FailedSnacksMenu", "onFailure:" + e.toString());
                        Toast.makeText(CreateMenu.this, "problem in snacks menu creation", Toast.LENGTH_SHORT).show();
                    }
                });

                DocumentReference dIdRef = menuStore.collection("DinnerID").document("dID");
                dIdRef.update("dinner_counter",FieldValue.increment(1));
                DinnerId++;
                DocumentReference dRef= menuStore.collection("MenuDinner").document();
                final String dinnerId= DinnerId.toString();
                Map<String,Object> dinnerMap= new HashMap<>();
                dinnerMap.put("LatestChangesMade", FieldValue.serverTimestamp());
                dinnerMap.put("DinnerItemList",dinner);
                dinnerMap.put("EPrice",enterPrisePrice);
                dinnerMap.put("VPrice",vendorPrice);
                dinnerMap.put("MenuDinnerID",dinnerId);
                dRef.set(dinnerMap).addOnSuccessListener(new OnSuccessListener<Void>(){
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("CreateDinnerMenu","onSuccess: dinner menu is created in "+dinnerId);
                        Toast.makeText(CreateMenu.this, "Dinner menu created", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FailedDinnerMenu", "onFailure:" + e.toString());
                        Toast.makeText(CreateMenu.this, "problem in dinner menu creation", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}