package com.codewithsrv.drpetvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Date;

public class UserComplaintReg extends AppCompatActivity {
    Context context;

    EditText gtPetCat;
    String symptoms;
    double gtWeight;
    Date gtDate,gtSuffering;
    Spinner spinner;
    Button regComp;

    String[] usertype = {"Select","Cough","Vomiting","Fever","Weakness"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_complaint_reg);

        spinner = findViewById(R.id.spinnersymp);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, usertype);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                symptoms = spinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), usertype[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}