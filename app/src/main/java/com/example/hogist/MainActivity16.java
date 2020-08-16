package com.example.hogist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity16 extends AppCompatActivity {


    private static RecyclerView.Adapter adapter;
    protected RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<CancelOrderRequestDataModel> data;
    static View.OnClickListener myOnclickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);

        myOnclickListener= new MyOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.cance_order_recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<CancelOrderRequestDataModel>();
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));
        data.add(new CancelOrderRequestDataModel("Request id","Vendor Name","Reason","New Menu","Status"));

        adapter = new CancelOrderDetailsAdapter(data);
        recyclerView.setAdapter(adapter);

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