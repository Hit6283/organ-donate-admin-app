package com.app.oda_admin;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class donor_view_holder extends RecyclerView.ViewHolder {
    TextView donor_name;
    TextView donatedOrgan;
    View view;
    public donor_view_holder(@NonNull View itemView) {
        super(itemView);
        donor_name
                = (TextView)itemView
                .findViewById(R.id.list_donor_name);
        donatedOrgan
                = (TextView)itemView
                .findViewById(R.id.list_donated_organ);
        view  = itemView;
    }
}
