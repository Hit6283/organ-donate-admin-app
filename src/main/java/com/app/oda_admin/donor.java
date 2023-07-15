package com.app.oda_admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class donor extends AppCompatActivity {

    String[] items = {"All","Eyes","Kidneys (2)","One Kidney","Lungs (2)","One Lung","Liver","Heart","Pancreas","Intestines"};

    AutoCompleteTextView selectDonor;
    ArrayAdapter<String> adapterItems;

    private FirebaseFirestore firebaseFirestore;

    donor_adapter adapter;
    RecyclerView recyclerView;
    ProgressBar progress_circular;
    List<donorsModel> donorsModelList = new ArrayList<>();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        selectDonor = findViewById(R.id.select_donor);

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, items);

        selectDonor.setAdapter(adapterItems);

        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.mDonors);
        progress_circular = findViewById(R.id.progressBar);

        firebaseFirestore.collection("users").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progress_circular.setVisibility(View.VISIBLE);
                List<DocumentSnapshot> snap = task.getResult().getDocuments();
                for (int i = 0; i < snap.size(); i++) {
                    String id = snap.get(i).getId();

                    firebaseFirestore.collection("users").document(id).collection("user donations").get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            List<DocumentSnapshot> snapshots = task1.getResult().getDocuments();
                            for (int j = 0; j < snapshots.size(); j++) {
                                donorsModelList.add(new donorsModel(
                                        snapshots.get(j).get("donor_name").toString(),
                                        snapshots.get(j).get("donatedOrgan").toString(),id));
                            }
                            adapter = new donor_adapter(donorsModelList, getApplication());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(donor.this));
                            progress_circular.setVisibility(View.GONE);
                        } else {
                            progress_circular.setVisibility(View.GONE);
                        }
                    });
                }

            } else {
                progress_circular.setVisibility(View.GONE);
            }
        });
    }
}