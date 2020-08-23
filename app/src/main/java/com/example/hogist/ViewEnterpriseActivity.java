package com.example.hogist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewEnterpriseActivity extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    protected RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    private static ArrayList<DataModel> data2;
    static View.OnClickListener myOnclickListener;
    FirebaseFirestore db;
    private Long VendorID;
    private Long TagEnterpriseID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterprise_details);

        myOnclickListener = new MyOnClickListener(this);
        db=FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView)findViewById(R.id.vendor_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        data2 = new ArrayList<DataModel>();

        db.collection("EnterpriseUser")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            DataModel v=new DataModel(q.getString("EUserID"),
                                    q.getString("EFullName"),
                                    q.getString("ECompanyName"),
                                    q.getString("EEmailAddress"),
                                    q.getString("EMobileNumber"),
                                    q.getString("ECity"));
                            data.add(v);
                        }
                    }
                });



        adapter = new ViewAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        db.collection("EnterpriseUser").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value){
                    DataModel v=new DataModel(q.getString("EUserID"),
                            q.getString("EFullName"),
                            q.getString("ECompanyName"),
                            q.getString("EEmailAddress"),
                            q.getString("EMobileNumber"),
                            q.getString("ECity"));


                    data.add(v);

                    adapter = new ViewAdapter(data);
                    recyclerView.setAdapter(adapter);

                }
            }
        });
        DocumentReference vIdRef = db.collection("VendorUserID").document("vID");
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
        DocumentReference tagEnterpriseIdRef = db.collection("VendorTagEnterpriseID").document("veID");
        tagEnterpriseIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentTE = task.getResult();
                    assert documentTE != null;
                    if (documentTE.exists()) {
                        TagEnterpriseID = documentTE.getLong("tagenterprise_counter");
                        Log.v("TAG", "tagenterprise_counter: " + documentTE.getData());
                    } else {
                        Log.v("TAG", "No such document");
                    }
                } else {
                    Log.v("TAG", "get failed with ", task.getException());
                }
            }
        });


    }

    class MyOnClickListener implements View.OnClickListener{
        private final Context context;
        MyOnClickListener(Context context){
            this.context = context;
        }
        @Override
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            DataModel item = data.get(itemPosition);
            String id = item.getEnterpriseID();
            DocumentReference tagEnterpriseIdRef = db.collection("VendorTagEnterpriseID").document("veID");
            tagEnterpriseIdRef.update("tagenterprise_counter", FieldValue.increment(1));
            TagEnterpriseID++;
            String tagEnterpriseID = TagEnterpriseID.toString();
            String vendorID = VendorID.toString();
            Intent intent = getIntent();
            String vendorEmailID = intent.getStringExtra("VendorEmailID");
            Toast.makeText(context,id,Toast.LENGTH_SHORT).show();
            DocumentReference tagEnterpriseRef = db.collection("VendorTagEnterprise").document();
            Map<String,Object> tagenterprise = new HashMap<>();
            tagenterprise.put("TaggedAt",FieldValue.serverTimestamp());
            tagenterprise.put("VTagEnterpriseID",tagEnterpriseID);
            tagenterprise.put("VUserID",vendorID);
            tagenterprise.put("EnterpriseID",id);
            tagenterprise.put("VendorEmailID",vendorEmailID);
            tagEnterpriseRef.set(tagenterprise).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("TAG", "onsucess: enterprise tagged" );
                    Toast.makeText(context, "enterprisetagged!!", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("TAG", "onFailure:" + e.toString());
                }
            });

        }
    }

}