package com.app.oda_admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class add_doctor extends AppCompatActivity {

    TextInputEditText txtDoctorName,txtEmail,txtContact,txtHospitalName,txtAddressHospital,txtProvince,txtState,txtCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        FirebaseUser admin = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        txtDoctorName = findViewById(R.id.txtDoctorName);
        txtEmail = findViewById(R.id.txtEmail);
        txtContact = findViewById(R.id.txtContact);
        txtHospitalName = findViewById(R.id.txtHospitalName);
        txtAddressHospital = findViewById(R.id.txtAddressHospital);
        txtProvince = findViewById(R.id.txtProvince);
        txtState = findViewById(R.id.txtState);
        txtCity = findViewById(R.id.txtCity);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        Map<String, Object> data1 = new HashMap<>();
        data1.put("doctor_id", admin.getUid());

        db.collection("admins").document(admin.getUid()).set(data1);
        Map<String, Object> data = new HashMap<>();
        btnSubmit.setOnClickListener(view -> {
            data.put("doctor_name", txtDoctorName.getText().toString());
            data.put("doctor_email", txtEmail.getText().toString());
            data.put("doctor_contact", txtContact.getText().toString());
            data.put("doctor_hospital", txtHospitalName.getText().toString());
            data.put("doctor_addressHospital", txtAddressHospital.getText().toString());
            data.put("doctor_province", txtProvince.getText().toString());
            data.put("doctor_state", txtState.getText().toString());
            data.put("doctor_city", txtCity.getText().toString());
            db.collection("admins").document(admin.getUid()).collection("doctors").document(txtDoctorName.getText().toString()).set(data);

            Intent intent = new Intent(getApplicationContext(),doctor.class);
            startActivity(intent);
            finish();
        });
    }
}