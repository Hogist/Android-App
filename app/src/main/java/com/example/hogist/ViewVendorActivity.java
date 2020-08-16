package com.example.hogist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewVendorActivity extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    protected RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    private static ArrayList<DataModel> data2;
    static View.OnClickListener myOnclickListener;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_details);

        myOnclickListener = new MyOnClickListener(this);
        db=FirebaseFirestore.getInstance();

        recyclerView = (RecyclerView)findViewById(R.id.vendor_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        data2 = new ArrayList<DataModel>();

        db.collection("VendorUser")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            DataModel v=new DataModel(q.getString("VUserID"),
                                    q.getString("VFullName"),
                                    q.getString("VCompanyName"),
                                    q.getString("VEmailAddress"),
                                    q.getString("VMobileNumber"),
                                    q.getString("VAddress"));
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

        db.collection("VendorUser").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value){
                    DataModel v=new DataModel(q.getString("VUserID"),
                            q.getString("VFullName"),
                            q.getString("VCompanyName"),
                            q.getString("VEmailAddress"),
                            q.getString("VMobileNumber"),
                            q.getString("VAddress"));

                    data.add(v);

                    adapter = new ViewAdapter(data);
                    recyclerView.setAdapter(adapter);

                }
            }
        });
    }

    static class MyOnClickListener implements View.OnClickListener{
        private final Context context;
        MyOnClickListener(Context context){
            this.context = context;
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(context, "item Clicked!!", Toast.LENGTH_SHORT).show();
        }
    }

}