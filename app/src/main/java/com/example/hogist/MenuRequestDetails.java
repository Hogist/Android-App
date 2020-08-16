package com.example.hogist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MenuRequestDetails extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    protected RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore firestore;
    private Button View;
    private static RecyclerView recyclerView;
    private static ArrayList<MenuChangeRequestDataModel> data;
    static View.OnClickListener myOnclickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_request_details);

        firestore= FirebaseFirestore.getInstance();
        View=findViewById(R.id.view);



        myOnclickListener= new MyOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.menu_change_request_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        data = new ArrayList<MenuChangeRequestDataModel>();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        firestore.collection("MenuChangeRequest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q:task.getResult()){
                            MenuChangeRequestDataModel M=new MenuChangeRequestDataModel(String.valueOf(q.getDouble("MenuChangeRequestID")),
                                    String.valueOf(q.getDouble("EUserID")),
                                    q.getString("Reason"),
                                    q.getString("NewMenu"),
                                    q.getString("EFullName"));
                            data.add(M);
                        }
                    }
                });
        adapter = new MenuChangeRequestAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        firestore.collection("MenuChangeRequest").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    return;
                }

                for(QueryDocumentSnapshot q:value) {
                    MenuChangeRequestDataModel M=new MenuChangeRequestDataModel(String.valueOf(q.getDouble("MenuChangeRequestID")),
                            String.valueOf(q.getDouble("EUserID")),
                            q.getString("Reason"),
                            q.getString("NewMenu"),
                            q.getString("EFullName"));
                    data.add(M);
                    adapter = new MenuChangeRequestAdapter(data);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Intent intent = new Intent(MenuRequestDetails.this, RequestView.class);
                startActivity(intent);
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
            MenuChangeRequestDataModel item = data.get(itemPosition);
            String requestID = item.getRequestID();
            String eUserID = item.getEUserID();
            String enterpriseName = item.getEnterpriseName();
            Intent intent = new Intent(MenuRequestDetails.this,RequestView.class);
            intent.putExtra("RequestID",requestID);
            intent.putExtra("EUserID",eUserID);
            intent.putExtra("EnterpriseName",enterpriseName);
            startActivity(intent);
        }

    }
}