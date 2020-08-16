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

public class VendorDetails extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    protected RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnclickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_details);

        myOnclickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.vendor_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        for (int i = 0; i < DataSet.VendorID.length; i++) {
            data.add(new DataModel(
                    DataSet.VendorID[i], DataSet.VendorFullName[i], DataSet.CompanyName[i], DataSet.EnterpriseID[i], DataSet.EnterpriseName[i], DataSet.MenuID[i]
            ));
        }

        adapter = new ViewAdapter(data);
        recyclerView.setAdapter(adapter);

    }

    static class MyOnClickListener implements View.OnClickListener {
        private final Context context;

        MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "item Clicked!!", Toast.LENGTH_SHORT).show();
        }
    }

}
