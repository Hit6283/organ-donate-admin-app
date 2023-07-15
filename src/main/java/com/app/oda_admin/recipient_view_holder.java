package com.app.oda_admin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recipient_view_holder extends RecyclerView.ViewHolder {
    TextView recipient_name;
    TextView requestedOrgan;
    View view;
    public recipient_view_holder(@NonNull View itemView) {
        super(itemView);
        recipient_name
                = (TextView)itemView
                .findViewById(R.id.list_recipient_name);
        requestedOrgan
                = (TextView)itemView
                .findViewById(R.id.list_organ);
        view  = itemView;
    }
}
