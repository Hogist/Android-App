package com.example.hogist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Covidhyginecheck extends AppCompatActivity {
    Button update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covidhyginecheck);

        update = findViewById(R.id.covid_hygiene_check_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Covidhyginecheck.this, "Updated!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Covidhyginecheck.this, Hyginecheckstatus.class);
            }
        });



    }
}