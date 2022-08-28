package com.rachel.s1timobileapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rachel.s1timobileapp.activity_dosen.Dashboard_dsn;
import com.rachel.s1timobileapp.firebase.preferences;

public class login extends AppCompatActivity {

    EditText username, password;
    Button Login;
    ProgressDialog progressDialog;
    Switch active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.edtusername);
        password = findViewById(R.id.edtpassword);
        Login = findViewById(R.id.btn_Login);
        active = findViewById(R.id.active);
        progressDialog = new ProgressDialog(login.this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("login").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String input1 = username.getText().toString();
                        String input2 = password.getText().toString();

                        if(dataSnapshot.child(input1).exists()) {
                            if (dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)) {
                                if (active.isChecked()){
                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("dosen")) {
                                        preferences.setDataLogin(login.this,true);
                                        preferences.setDataAs(login.this,"dosen");
                                        startActivity(new Intent(login.this, Dashboard_dsn.class));
                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("mahasiswa")) {
                                        preferences.setDataLogin(login.this,true);
                                        preferences.setDataAs(login.this,"mahasiswa");
                                        startActivity(new Intent(login.this, Dashboard_mhs.class));

                                    }
                                } else {
                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("dosen")) {
                                        preferences.setDataLogin(login.this, false);
                                        startActivity(new Intent(login.this, Dashboard_dsn.class));

                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("mahasiswa")) {
                                        preferences.setDataLogin(login.this, false);
                                        startActivity(new Intent(login.this, Dashboard_mhs.class));

                                    }
                                }

                            } else {
                                Toast.makeText(login.this,"Kata sandi salah",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(login.this,"Data belum terdaftar",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(preferences.getDataLogin(this)) {
            if(preferences.getDataAs(this).equals("dosen")){
                startActivity(new Intent(login.this, Dashboard_dsn.class));
            } else if(preferences.getDataAs(this).equals("mahasiswa")) {
                startActivity(new Intent(login.this, Dashboard_mhs.class));
                finish();
            }
        }
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}