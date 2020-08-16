package com.example.hogist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity17 extends AppCompatActivity {

    Button accept,reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);

        accept = findViewById(R.id.cancel_order_request_accept);
        reject = findViewById(R.id.cancel_order_request_reject);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity17.this, "Accepted!!", Toast.LENGTH_SHORT).show();
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity17.this, "Rejected!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}