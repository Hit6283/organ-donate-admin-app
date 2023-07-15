package com.app.oda_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class recipient extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;

    recipient_adapter adapter;
    RecyclerView recyclerView;
    ProgressBar progress_circular;
    List<recipientsModel> recipientsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);

        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.mRecipients);
        progress_circular = findViewById(R.id.progressBar);

        firebaseFirestore.collection("users").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progress_circular.setVisibility(View.VISIBLE);
                List<DocumentSnapshot> snap = task.getResult().getDocuments();
                for (int i = 0; i < snap.size(); i++) {
                    firebaseFirestore.collection("users").document(snap.get(i).getId()).collection("user requests").get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            List<DocumentSnapshot> snapshots = task1.getResult().getDocuments();
                            for (int j = 0; j < snapshots.size(); j++) {
                                recipientsList.add(new recipientsModel(
                                        snapshots.get(j).get("recipient_name").toString(),
                                        snapshots.get(j).get("requestedOrgan").toString(),
                                        snapshots.get(j).get("recipient_bloodGroup").toString()));
                            }
                            adapter = new recipient_adapter(recipientsList, getApplication());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(recipient.this));
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
