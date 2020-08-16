package com.example.hogist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RequestView extends AppCompatActivity {

    Button accept,reject;
    TextView requestID, eUserID, enterpriseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestview);

        Intent intent = getIntent();
        String RequestID = intent.getStringExtra("RequestID");
        String EUserID = intent.getStringExtra("EUserID");
        String EnterpriseName = intent.getStringExtra("EnterpriseName");
        requestID = findViewById(R.id.requestID);
        eUserID = findViewById(R.id.eUserID);
        enterpriseName = findViewById(R.id.enterpriseName);
        accept = findViewById(R.id.request_view_accept);
        reject = findViewById(R.id.request_view_reject);
        requestID.setText(RequestID);
        eUserID.setText(EUserID);
        enterpriseName.setText(EnterpriseName);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RequestView.this, "Accepted", Toast.LENGTH_SHORT).show();
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RequestView.this, "Reject", Toast.LENGTH_SHORT).show();
            }
        });
    }
}