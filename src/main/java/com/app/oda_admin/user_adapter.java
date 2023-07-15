package com.app.oda_admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class user_adapter extends RecyclerView.Adapter<user_view_holder> {

    List<usersModel> list;

    Context context;

    public user_adapter(List<usersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public user_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.list_item_users, parent, false);

        user_view_holder viewHolder = new user_view_holder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull user_view_holder viewHolder, int position) {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.name
                .setText(list.get(position).getName());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG",String.valueOf(index));

                Intent intent = new Intent(context, recipientDonation.class);
                intent.putExtra("name",list.get(index).getName());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
