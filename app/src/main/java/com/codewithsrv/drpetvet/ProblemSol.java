package com.codewithsrv.drpetvet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class ProblemSol extends AppCompatActivity {

    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_sol);

        Submit = (Button) findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sub = new Intent(getApplicationContext(),DoctorDashboard.class);
                startActivity(sub);
                Toast.makeText(ProblemSol.this, "Your solution is sent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}