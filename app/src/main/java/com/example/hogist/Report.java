package com.example.hogist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Report extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] reportfilterArray = { "Filter","Filter","Filter","Filter","Filter"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        Spinner spinner = findViewById(R.id.spinner_report);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, reportfilterArray);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(ad);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Item Selected!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}