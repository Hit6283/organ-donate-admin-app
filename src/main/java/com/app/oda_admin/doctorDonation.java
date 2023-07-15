package com.app.oda_admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class doctorDonation extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView DoctorsList;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_donation);

        //Initialize And Assign Variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.doctor_menu);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.recipient_menu:
                    startActivity(new Intent(getApplicationContext(), com.app.oda_admin.recipientDonation.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.doctor_menu:
                    return true;
                case R.id.donor_menu:
                    startActivity(new Intent(getApplicationContext(), com.app.oda_admin.donorDonation.class));
                    finish();
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });

        FirebaseUser admin = FirebaseAuth.getInstance().getCurrentUser();

        firebaseFirestore = FirebaseFirestore.getInstance();
        DoctorsList = findViewById(R.id.DoctorsList);

        //Query
        Query query = firebaseFirestore.collection("admins").document(admin.getUid()).collection("doctors");
        //RecyclerOptions
        FirestoreRecyclerOptions<doctorsModel> options = new FirestoreRecyclerOptions.Builder<doctorsModel>()
                .setQuery(query, doctorsModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<doctorsModel, doctorDonation.doctorsViewHolder>(options) {
            @NonNull
            @Override
            public doctorDonation.doctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_doctor, parent, false);
                return new doctorDonation.doctorsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull doctorDonation.doctorsViewHolder holder, int position, @NonNull doctorsModel model) {
                holder.list_name.setText(model.getDoctor_name());
                holder.list_province.setText(model.getDoctor_province());
            }
        };

        DoctorsList.setHasFixedSize(false);
        DoctorsList.setLayoutManager(new LinearLayoutManager(this));
        DoctorsList.setAdapter(adapter);
    }

    private static class doctorsViewHolder extends RecyclerView.ViewHolder {

        private final TextView list_name;
        private final TextView list_province;

        public doctorsViewHolder(@NonNull View itemView) {
            super(itemView);

            list_name = itemView.findViewById(R.id.list_name);
            list_province = itemView.findViewById(R.id.list_province);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}