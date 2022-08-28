package com.rachel.s1timobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.rachel.s1timobileapp.activity_mahasiswa.kehadiran_mhs;
import com.rachel.s1timobileapp.firebase.preferences;

public class Dashboard_mhs extends AppCompatActivity implements View.OnClickListener {

    Button logout;
    CardView profile_mhs,kehadiran, nilai, jadwal, pembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_mhs);

        profile_mhs = (CardView) findViewById(R.id.CVProfile_mhs);
        kehadiran = (CardView) findViewById(R.id.CVKehadiran);
        nilai = (CardView) findViewById(R.id.CVNilai);
        jadwal = (CardView) findViewById(R.id.CVJadwal);
        pembayaran = (CardView) findViewById(R.id.CVPembayaran);
        logout = findViewById(R.id.nav_logout);
        logout = findViewById(R.id.btn_Logout);
        NavigationView navigationView = findViewById(R.id.navigationview_mhs);
        DrawerLayout drawerLayout = findViewById(R.id.DrawerLayout);

        //Click Listener
        kehadiran.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
        Intent i ;
        switch (v.getId()){
            case R.id.CVKehadiran :
                i = new Intent(this, kehadiran_mhs.class); startActivity(i);
                break;
        }
    }


    public void logout(MenuItem item) {
        startActivity(new Intent(this, login.class));
        preferences.clearData(this);
        finish();
    }
}