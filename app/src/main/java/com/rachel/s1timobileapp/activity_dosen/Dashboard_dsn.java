package com.rachel.s1timobileapp.activity_dosen;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.rachel.s1timobileapp.R;
import com.rachel.s1timobileapp.firebase.preferences;
import com.rachel.s1timobileapp.login;

public class Dashboard_dsn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_dsn);

    }
    public void logout(MenuItem item) {
        startActivity(new Intent(this, login.class));
        preferences.clearData(this);
        finish();
    }
}