package com.codewithsrv.drpetvet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorDashboard extends AppCompatActivity {

    CardView dcard1,dcard2,dcard3,dcard4;

    ImageButton cal, time, pay, profile,logout;
    TextView comp, sol, makepay, drProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.register_bk_color));
        }

        dcard1 = (CardView) findViewById(R.id.dc1);
        dcard2 = (CardView) findViewById(R.id.dc2);
        dcard3 = (CardView) findViewById(R.id.dc3);
        dcard4 = (CardView) findViewById(R.id.dc4);

        dcard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent complaintPage = new Intent(getApplicationContext(),Complaint.class);
                startActivity(complaintPage);
            }
        });
        dcard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent probSolPage = new Intent(getApplicationContext(),ProblemSol.class);
                startActivity(probSolPage);
            }
        });
        dcard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent payPage = new Intent(getApplicationContext(),Payment.class);
                startActivity(payPage);
            }
        });
        dcard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Doctor = new Intent(getApplicationContext(),DoctorProfile.class);
                startActivity(Doctor);
            }
        });

        logout = findViewById(R.id.DocLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences settings = getSharedPreferences("demo", MODE_PRIVATE);
                settings.edit().clear().commit();
                Intent intent = new Intent(DoctorDashboard.this,Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                Toast.makeText(DoctorDashboard.this, "Logged Out", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("alert!")
                .setMessage("Do you Really Want to Exit?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int whichButton) {
                        DoctorDashboard.super.onBackPressed();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }
}