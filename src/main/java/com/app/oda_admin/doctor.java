package com.app.oda_admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class doctor extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mDoctorsList;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        FirebaseUser admin = FirebaseAuth.getInstance().getCurrentUser();

        firebaseFirestore = FirebaseFirestore.getInstance();
        mDoctorsList = findViewById(R.id.mDoctorsList);

        //Query
        Query query = firebaseFirestore.collection("admins").document(admin.getUid()).collection("doctors");
        //RecyclerOptions
        FirestoreRecyclerOptions<doctorsModel> options = new FirestoreRecyclerOptions.Builder<doctorsModel>()
                .setQuery(query, doctorsModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<doctorsModel, doctorsViewHolder>(options) {
            @NonNull
            @Override
            public doctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_doctor, parent, false);
                return new doctorsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull doctorsViewHolder holder, int position, @NonNull doctorsModel model) {
                holder.list_name.setText(model.getDoctor_name());
                holder.list_province.setText(model.getDoctor_province());
            }
        };

        mDoctorsList.setHasFixedSize(false);
        mDoctorsList.setLayoutManager(new LinearLayoutManager(this));
        mDoctorsList.setAdapter(adapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab_addDoctor);

        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), add_doctor.class);
            startActivity(intent);
        });
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