package com.example.hogist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Hyginecheckstatus extends AppCompatActivity {

    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hyginecheckstatus);

        edit = findViewById(R.id.hygiene_check_status_editbutton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hyginecheckstatus.this,Covidhyginecheck.class);
                startActivity(intent);
            }
        });

    }
}