package com.example.hogist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity3 extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button vendor, enterprise;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        vendor = findViewById(R.id.createvendor);
        enterprise = findViewById(R.id.createenterprise);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() != null) {
            finish();
        }
        vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(i);
            }
        });

        enterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(i);
            }
        });
    }
}
