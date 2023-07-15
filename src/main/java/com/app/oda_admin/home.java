package com.app.oda_admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    Toolbar toolbar;
    FirebaseAuth mAuth;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CardView cardView1 = findViewById(R.id.cardView1);

        cardView1.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), donor.class);
            startActivity(intent1);
        });

        CardView cardView2 = findViewById(R.id.cardView2);

        cardView2.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplicationContext(), doctor.class);
            startActivity(intent2);
        });

        CardView cardView3 = findViewById(R.id.cardView3);

        cardView3.setOnClickListener(view -> {
            Intent intent3 = new Intent(getApplicationContext(), recipient.class);
            startActivity(intent3);
        });

        CardView cardView4 = findViewById(R.id.cardView4);

        cardView4.setOnClickListener(view -> {
            Intent intent4 = new Intent(getApplicationContext(), user.class);
            startActivity(intent4);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            mAuth.signOut();
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        Intent intent = new Intent(home.this, login.class);
        startActivity(intent);
        finish();

    }
}

