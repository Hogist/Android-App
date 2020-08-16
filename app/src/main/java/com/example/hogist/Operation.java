package com.example.hogist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Operation extends AppCompatActivity {
    public static final String TAG = "TAG";
    Button vendor, enterprise;
    private TextView CancelOrderRequest;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;
    CardView menuchange, cancelrequest, createMenu;
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operation);
        vendor = findViewById(R.id.createvendor);
        enterprise = findViewById(R.id.createenterprise);
        createMenu = findViewById(R.id.createmenu);
        logout = findViewById(R.id.logout);
        menuchange = findViewById(R.id.menuchangerequestcw);
        cancelrequest = findViewById(R.id.cancelrequestcw);
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Operation.this)
                        .setTitle("Exit")
                        .setMessage("Do you want to exit")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(Operation.this,Loginpage.class));
                                finish();
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .create()
                        .show();

            }
        });
        if (fAuth.getCurrentUser() != null) {
            finish();
        }
        vendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Operation.this, CreateVendors.class);
                startActivity(i);
            }
        });

        enterprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Operation.this, CreateEnterprise.class);
                startActivity(i);
            }
        });
        createMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Operation.this, CreateMenu.class);
                startActivity(i);
            }
        });


        menuchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Operation.this, MenuRequestDetails.class);
                startActivity(i);
            }
        });
        cancelrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Operation.this, CancelRequestDetails.class);
                startActivity(i);
            }
        });

    }
}
