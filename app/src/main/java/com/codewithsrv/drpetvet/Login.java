package com.codewithsrv.drpetvet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Login extends AppCompatActivity {

    Context context;
    EditText ed_email,ed_pswd;
    String str_email,str_pswd;
    String URL = "https://drvp.000webhostapp.com/doct_login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_email = findViewById(R.id.ed_email);
        ed_pswd = findViewById(R.id.ed_pswd);

        if (!isConnected()){
            Toast.makeText(getApplicationContext(),"No Internet Connection. Check your Internet Connection",Toast.LENGTH_SHORT).show();
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void OnclickLogin(View view) {

        if(ed_email.getText().toString().equals("")){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        }
        else if(ed_pswd.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");

            progressDialog.show();
            str_email = ed_email.getText().toString().trim();
            str_pswd = ed_pswd.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("Logged in Successfully")){
                        ed_email.setText("");
                        ed_pswd.setText("");
                        startActivity(new Intent(getApplicationContext(),DoctorDashboard.class));
                        Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();

                    params.put("email",str_email);
                    params.put("password",str_pswd);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
            requestQueue.add(request);

        }
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, Signup.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }


    private Boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog =new AlertDialog.Builder(Login.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit app?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

}

/*loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String pswd = etPassword.getText().toString();

                SharedPreferences shrd = getSharedPreferences("demo", MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd.edit();

                if (email.length() == 0) {
                    etEmail.requestFocus();
                    etEmail.setError("Field cannot be empty");
                } else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    etEmail.requestFocus();
                    etEmail.setError("Enter valid email");
                } else if (pswd.length() == 0) {
                    etPassword.requestFocus();
                    etPassword.setError("Field cannot be empty");
                } else if (pswd.length() <= 5) {
                    etPassword.requestFocus();
                    etPassword.setError("Minimum 6 characters required");
                }
            }
        });*/

//Make function for validation and pass all parameters

//boolean check = validateinfo(email, pswd);

                /*if (check == true){
                    Toast.makeText(getApplicationContext(),"Data is Valid",Toast.LENGTH_SHORT).show();
                }*/

//Firebase Database
                /*else {

                    /*if (getUserEmail.getText().toString().equals("D1") && getUserPassword.getText().toString().equals("D1")) {
                        Intent intent = new Intent(Login.this, DoctorDashboard.class);
                        startActivity(intent);
                    } else if (getUserEmail.getText().toString().equals("U1") && getUserPassword.getText().toString().equals("U1")) {
                        Intent intent = new Intent(Login.this, UserDashboard.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                    }

                    //Patient Database
                    Query query = FirebaseDatabase.getInstance().getReference().child("Patient").orderByChild("email").equalTo(etEmail.getText().toString().trim());

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot user : snapshot.getChildren()) {
                                    UserModel studentModel = user.getValue(UserModel.class);
                                    if (studentModel.password.equals(etPassword.getText().toString().trim())) {
                                        String unm = studentModel.username;

                                        if (!isConnected()) {
                                            Toast.makeText(getApplicationContext(), "No Internet access. check you connection", Toast.LENGTH_SHORT).show();
                                        } else {
                                            editor.putString("password", etPassword.getText().toString());
                                            editor.putInt("flag", 1);
                                            editor.apply();
                                            Intent nextpage = new Intent(Login.this, UserDashboard.class);
                                            nextpage.putExtra("UNM", unm);
                                            startActivity(nextpage);
                                            Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                    //Doctor Database
                    Query querydoc = FirebaseDatabase.getInstance().getReference().child("Doctor").orderByChild("email").equalTo(etEmail.getText().toString().trim());

                    querydoc.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot user : snapshot.getChildren()) {
                                    UserModel studentModel = user.getValue(UserModel.class);
                                    if (studentModel.password.equals(etPassword.getText().toString().trim())) {
                                        String unm = studentModel.username;

                                        if (!isConnected()) {
                                            Toast.makeText(getApplicationContext(), "No Internet access. check you connection", Toast.LENGTH_SHORT).show();
                                        } else {
                                            editor.putString("password", etPassword.getText().toString());
                                            editor.putInt("flag", 1);
                                            editor.apply();
                                            Intent nextpage = new Intent(Login.this, DoctorDashboard.class);
                                            nextpage.putExtra("UNM", unm);
                                            startActivity(nextpage);
                                            Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }*/

/*register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);

            }
        });*/

/*private Boolean validateinfo(String email, String pswd) {


        else {
            return true;
        }

    }*/


    /*@Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("alert!")
                .setMessage("Do you Really Want to Exit?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Login.super.onBackPressed();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }*/

    /*public void emailValidator() {

        // extract the entered data from the EditText
        String emailToText = etEmail.getText().toString();

        // Android offers the inbuilt patterns which the entered
        // data from the EditText field needs to be compared with
        // In this case the entered data needs to compared with
        // the EMAIL_ADDRESS, which is implemented same below
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter valid Email address !", Toast.LENGTH_SHORT).show();
        }
    }
     */



