package com.codewithsrv.drpetvet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    Context context;
    EditText ed_name,ed_email,ed_pswd,ed_phone,ed_address;
    String str_name,str_email,str_pswd,str_phone,str_address;
    String URL = "https://drvp.000webhostapp.com/doct_signup.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        changeStatusBarColor();

        ed_name = findViewById(R.id.ed_name);
        ed_email = findViewById(R.id.ed_email);
        ed_pswd = findViewById(R.id.ed_pswd);
        ed_phone = findViewById(R.id.ed_phone);
        ed_address = findViewById(R.id.ed_address);

        if (!isConnected()){
            Toast.makeText(getApplicationContext(),"No Internet Connection. Check your Internet Connection",Toast.LENGTH_SHORT).show();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void OnclickSignup(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait..");

        if (ed_name.getText().toString().equals("")){
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if (ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if (ed_pswd.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }else if (ed_phone.getText().toString().equals("")){
            Toast.makeText(this, "Enter Phone Nummber", Toast.LENGTH_SHORT).show();
        }
        else if (ed_address.getText().toString().equals("")){
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.show();
            str_name = ed_name.getText().toString().trim();
            str_email = ed_email.getText().toString().trim();
            str_pswd = ed_pswd.getText().toString().trim();
            str_phone = ed_phone.getText().toString().trim();
            str_address = ed_address.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    ed_name.setText("");
                    ed_email.setText("");
                    ed_pswd.setText("");
                    ed_phone.setText("");
                    ed_address.setText("");
                    Toast.makeText(Signup.this, response, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Signup.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();

                    params.put("username",str_name);
                    params.put("email",str_email);
                    params.put("password",str_pswd);
                    params.put("phone",str_phone);
                    params.put("address",str_address);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Signup.this);
            requestQueue.add(request);

        }
    }

    public void changeStatusBarColor(){
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,Login.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private Boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
    /*ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,usertype);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user = spinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),usertype[position] , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });*/

        /*register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, spinner.getSelectedItemPosition()+"",Toast.LENGTH_SHORT).show();
                String str = "";
                if(spinner.getSelectedItemPosition() == 0)
                {
                    str="Doctor";
                }
                else
                {
                    str="patient";
                }
            }
        });*/

    /*private Boolean validate(String username,String email, String pswd){
        if (username.length()==0){
            gtUsername.requestFocus();
            gtUsername.setError("Field cannot be empty");
            return false;
        }
        else if (email.length()==0){
            gtEmail.requestFocus();
            gtEmail.setError("Field cannot be empty");
            return false;
        }
        else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            gtEmail.requestFocus();
            gtEmail.setError("Enter valid email");
            return false;
        }
        else if (pswd.length()==0){
            gtPassword.requestFocus();
            gtPassword.setError("Field cannot be empty");
            return false;
        }
        else if (pswd.length()<=5){
            gtPassword.requestFocus();
            gtPassword.setError("Minimum 6 characters required");
            return false;
        }else if (pswd.length()!=6){
            gtPassword.requestFocus();
            gtPassword.setError("Minimum 6 characters required");
            return false;
        }
        else {
            return true;
        }
    }*/

    /*private void insertData(){
        Map<String,Object> map = new HashMap<>();

        //map.put("degree",degree.getText().toString());
        map.put("username",gtUsername.getText().toString());
        map.put("email",gtEmail.getText().toString());
        map.put("password",gtPassword.getText().toString());
        if (gtUsername == null){
            Toast.makeText(getApplicationContext(), "Please Enter Username", Toast.LENGTH_SHORT).show();
        }
        else{
            if(gtEmail == null){
                Toast.makeText(getApplicationContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
            }
            else {
                if(gtPassword == null){
                    Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
            }
            if (user == "Select") {
                Toast.makeText(getApplicationContext(), "Please Select Doctor / User", Toast.LENGTH_LONG).show();
            }
            else {
                map.put("user", user);
        }
            if (user == "Doctor"){
                FirebaseDatabase.getInstance().getReference().child("Doctor").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Signup.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                                Intent next = new Intent(getApplicationContext(),DoctorDashboard.class);
                                startActivity(next);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Signup.this,"Insertion failed",Toast.LENGTH_SHORT).show();
                            }
                        });
            }else {
                FirebaseDatabase.getInstance().getReference().child("Patient").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Signup.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                                Intent next = new Intent(getApplicationContext(),UserDashboard.class);
                                startActivity(next);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Signup.this,"Insertion failed",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }*/