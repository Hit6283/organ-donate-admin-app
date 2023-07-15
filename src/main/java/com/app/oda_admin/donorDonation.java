package com.app.oda_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class donorDonation extends AppCompatActivity {

    String[] items = {"All","Eyes","Kidneys (2)","One Kidney","Lungs (2)","One Lung","Liver","Heart","Pancreas","Intestines"};

    AutoCompleteTextView selectDonor;
    ArrayAdapter<String> adapterItems;

    private FirebaseFirestore firebaseFirestore;

    donor_adapter adapter;
    RecyclerView recyclerView;
    List<donorsModel> donorsList = new ArrayList<>();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_donation);

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.donor_menu);
//        Bundle bundle = getIntent().getExtras();
//        String name = bundle.getString("donor_name");
//        Log.i("TAG","..............");
//
//        Log.i("TAG",name);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.recipient_menu:
                    startActivity(new Intent(getApplicationContext(), com.app.oda_admin.recipientDonation.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.doctor_menu:
                    startActivity(new Intent(getApplicationContext(), com.app.oda_admin.doctorDonation.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.donor_menu:
                    return true;
            }
            return false;
        });

        selectDonor = findViewById(R.id.select_donor);

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, items);

        selectDonor.setAdapter(adapterItems);

        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.Donors);

        firebaseFirestore.collection("users").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<DocumentSnapshot> snap = task.getResult().getDocuments();
                for (int i = 0; i < snap.size(); i++) {
                    String id = snap.get(i).getId();

                    firebaseFirestore.collection("users").document(id).collection("user donations").get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            List<DocumentSnapshot> snapshots = task1.getResult().getDocuments();
                            for (int j = 0; j < snapshots.size(); j++) {
                                donorsList.add(new donorsModel(
                                        snapshots.get(j).get("donor_name").toString(),
                                        snapshots.get(j).get("donatedOrgan").toString(),
                                        id));
                            }
                            adapter = new donor_adapter(donorsList, getApplication());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(donorDonation.this));
                        } else {

                        }
                    });
                }

            } else {

            }
        });
    }
}