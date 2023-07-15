package com.app.oda_admin;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class donor_adapter extends RecyclerView.Adapter<donor_view_holder> {

    List<donorsModel> list;

    Context context;

    public donor_adapter(List<donorsModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public donor_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View donorView = inflater.inflate(R.layout.list_item_donors, parent, false);

        donor_view_holder viewHolder = new donor_view_holder(donorView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull donor_view_holder viewHolder, int position) {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.donor_name
                .setText(list.get(position).getDonor_name());
        viewHolder.donatedOrgan
                .setText(list.get(position).getDonatedOrgan());

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("TAG",String.valueOf(index));
//                Intent intent = new Intent(context,recipientDonation.class);
//                intent.putExtra("donor_name",list.get(index).getDonor_name());
//                view.getContext().startActivity(intent);
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
