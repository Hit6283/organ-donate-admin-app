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

public class recipient_adapter extends RecyclerView.Adapter<recipient_view_holder> {

    List<recipientsModel> list;

    Context context;

    public recipient_adapter(List<recipientsModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public recipient_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View recipientView = inflater.inflate(R.layout.list_item_recipients, parent, false);

        recipient_view_holder viewHolder = new recipient_view_holder(recipientView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recipient_view_holder viewHolder, int position) {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.recipient_name
                .setText(list.get(position).getRecipient_name());
        viewHolder.requestedOrgan
                .setText(list.get(position).getRequestedOrgan());
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG",String.valueOf(index));

                Intent intent = new Intent(context, recipientDonation.class);
                intent.putExtra("recipient_name",list.get(index).getRecipient_name());
                intent.putExtra("requestedOrgan",list.get(index).getRequestedOrgan());
                intent.putExtra("recipient_bloodGroup",list.get(index).getRecipient_bloodGroup());
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
