package com.app.oda_admin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class user_view_holder extends RecyclerView.ViewHolder {
    TextView name;
    View view;
    public user_view_holder(@NonNull View itemView) {
        super(itemView);
        name
                = (TextView)itemView
                .findViewById(R.id.list_recipient_name);
        view  = itemView;
    }
}
