package com.app.oda_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class recipientDonation extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    TextView txtRecipientName, txtRecipientBGroup, txtRecipientOrgan, txtDoctorName, txtDonorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient_donation);

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.recipient_menu);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("recipient_name");
        String bg = bundle.getString("recipient_bloodGroup");
        String ro = bundle.getString("requestedOrgan");
//        String doctor_name = bundle.getString("doctor_name");
//        String donor_name = bundle.getString("donor_name");

        Log.i("TAG","..............");

        Log.i("TAG",name);
        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.recipient_menu:
                    return true;
                case R.id.doctor_menu:
                    startActivity(new Intent(getApplicationContext(), com.app.oda_admin.doctorDonation.class));
                    finish();
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.donor_menu:
                    startActivity(new Intent(getApplicationContext(), com.app.oda_admin.donorDonation.class));
                    finish();
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        txtRecipientName = findViewById(R.id.txtRecipientName);
        txtRecipientBGroup = findViewById(R.id.txtRecipientBGroup);
        txtRecipientOrgan = findViewById(R.id.txtRecipientOrgan);
        txtDoctorName = findViewById(R.id.txtDoctorName);
        txtDonorName = findViewById(R.id.txtDonorName);

        //Display recipient details
        txtRecipientName.setText(name);
        txtRecipientOrgan.setText(ro);
        txtRecipientBGroup.setText(bg);
//        txtDoctorName.setText(doctor_name);
//        txtDonorName.setText(donor_name);

        CardView cardViewRecipient2 = findViewById(R.id.cardViewRecipient2);
        cardViewRecipient2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","asdf");
                Intent intent = new Intent(getApplicationContext(),doctorDonation.class);
                startActivity(intent);
            }
        });

        CardView cardViewRecipient3 = findViewById(R.id.cardViewRecipient3);
        cardViewRecipient3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","zxcv");
                Intent intent = new Intent(getApplicationContext(),donorDonation.class);
                startActivity(intent);
            }
        });
    }
}