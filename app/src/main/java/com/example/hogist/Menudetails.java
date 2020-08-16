package com.example.hogist;

import android.content.Context;
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

public class Menudetails extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    protected RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<BDataModel> data;
    static View.OnClickListener myOnclickListener;
    private FirebaseFirestore firestore;
    private Long VendorTagMenuID;
    private Long VendorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_details);

        myOnclickListener = new MyOnClickListener(this);
        firestore= FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView)findViewById(R.id.menu_details_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<BDataModel>();

        firestore.collection("MenuBreakfast")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            BDataModel M=new BDataModel(
                                    q.getString("EPrice"),
                                    q.getString("MenubreakfastID"),
                                    q.getString("Breakfastitemlist"),
                                    q.getString("VCompanyName")
                                    // String.valueOf(q.getDouble("EPrice"))
                            );
                            data.add(M);
                        }
                    }
                });

        adapter = new BMenuAdapter(data);
        recyclerView.setAdapter(adapter);

       firestore.collection("MenuLunch")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            BDataModel M=new BDataModel(
                                    q.getString("EPrice"),
                                    q.getString("MenuLunchID"),
                                    q.getString("LunchItemList"),
                                    q.getString("VCompanyName")
                            );
                            data.add(M);
                        }
                    }
                });
        adapter = new BMenuAdapter(data);
        recyclerView.setAdapter(adapter);


        firestore.collection("MenuSnacks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            BDataModel M=new BDataModel(
                                    q.getString("EPrice"),
                                    q.getString("MenuSnakcsID"),
                                    q.getString("SnacksitemList"),
                                    q.getString("VCompanyName")
                            );
                            data.add(M);
                        }
                    }
                });
        adapter = new BMenuAdapter(data);
        recyclerView.setAdapter(adapter);


        firestore.collection("MenuDinner")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            BDataModel M=new BDataModel(
                                    q.getString("EPrice"),
                                    q.getString("MenuDinnerID"),
                                    q.getString("DinnerItemList"),
                                    q.getString("VCompanyName")
                            );
                            data.add(M);
                        }
                    }
                });

        adapter = new BMenuAdapter(data);
        recyclerView.setAdapter(adapter);










    }


    @Override
    protected void onStart() {
        super.onStart();

        firestore.collection("MenuBreakfast").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value) {
                    BDataModel M=new BDataModel(
                            q.getString("EPrice"),
                            q.getString("MenubreakfastID"),
                            q.getString("Breakfastitemlist"),
                            q.getString("VCompanyName")
                            // String.valueOf(q.getDouble("EPrice"))
                    );
                    data.add(M);

                    adapter = new BMenuAdapter(data);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

       firestore.collection("MenuLunch").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value) {
                    BDataModel M=new BDataModel(
                            q.getString("EPrice"),
                            q.getString("MenuLunchID"),
                            q.getString("LunchItemList"),
                            q.getString("VCompanyName")
                    );
                    data.add(M);

                    adapter = new BMenuAdapter(data);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        firestore.collection("MenuSnacks").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value) {
                    BDataModel M=new BDataModel(
                            q.getString("EPrice"),
                            q.getString("MenuSnacksID"),
                            q.getString("SnacksitemList"),
                            q.getString("VCompanyName")
                            );
                    data.add(M);

                    adapter = new BMenuAdapter(data);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        firestore.collection("MenuDinner").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value) {
                    BDataModel M=new BDataModel(
                            q.getString("EPrice"),
                            q.getString("MenuDinnerID"),
                            q.getString("DinnerItemList"),
                            q.getString("VCompanyName")
                    );
                    data.add(M);

                    adapter = new BMenuAdapter(data);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

        DocumentReference tagMenuIdRef = firestore.collection("VendorTagMenuID").document("vmID");
        tagMenuIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentVM = task.getResult();
                    assert documentVM != null;
                    if (documentVM.exists()) {
                        VendorTagMenuID = documentVM.getLong("tagmenu_counter");
                        Log.v("TAG", "tagMenu_counter: " + documentVM.getData());
                    } else {
                        Log.v("TAG", "No such document");
                    }
                } else {
                    Log.v("TAG", "get failed with ", task.getException());
                }
            }
        });

        DocumentReference vIdRef = firestore.collection("VendorUserID").document("vID");
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

    }


    public class MyOnClickListener implements View.OnClickListener{
        private final Context context;
        MyOnClickListener(Context context){
            this.context = context;
        }
        @Override
        public void onClick(View view) {
            int itemPosition = recyclerView.getChildLayoutPosition(view);
            BDataModel item = data.get(itemPosition);
            String id = item.getID();
            Toast.makeText(context, id, Toast.LENGTH_LONG).show();
            DocumentReference tagMenuIdRef = firestore.collection("VendorTagMenuID").document("vmID");
//            tagMenuIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if (task.isSuccessful()) {
//                        DocumentSnapshot documentVM = task.getResult();
//                        assert documentVM != null;
//                        if (documentVM.exists()) {
//                            VendorTagMenuID = documentVM.getLong("tagmenu_counter");
//                            Log.v("TAG", "tagMenu_counter: " + documentVM.getData());
//                        } else {
//                            Log.v("TAG", "No such document");
//                        }
//                    } else {
//                        Log.v("TAG", "get failed with ", task.getException());
//                    }
//                }
//            });
            tagMenuIdRef.update("tagmenu_counter", FieldValue.increment(1));
            VendorTagMenuID++;
            DocumentReference tagMenuRef = firestore.collection("VendorTagMenu").document();
            String vendorTagMenuID = VendorTagMenuID.toString();
            String vendorID = VendorID.toString();
            Map<String,Object> tagmenu = new HashMap<>();
            tagmenu.put("TaggedAt",FieldValue.serverTimestamp());
            tagmenu.put("VTagMenuID",vendorTagMenuID);
            tagmenu.put("VUserID",vendorID);
            tagmenu.put("MenuID",id);
            tagMenuRef.set(tagmenu).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("TAG", "onsucess: menu tagged" );
                    Toast.makeText(context, "menutagged!!", Toast.LENGTH_SHORT).show();
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
