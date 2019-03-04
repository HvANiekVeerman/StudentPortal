package com.example.studentportal;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Portal> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // Data is passed into the constructor
    RecyclerViewAdapter(Context context, List<Portal> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    private void startWebView(String url) {
        Intent intent2 = new Intent(context, PortalviewActivity.class);
        String message = url;
        intent2.putExtra("URL", url);
        context.startActivity(intent2);
    }

    // Inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Portal portal = mData.get(position);
        holder.myTextView.setText(portal.getTitle());

        holder.myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWebView(portal.getUrl());
            }
        });
    }

    // Total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //Stores and recycles view as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tvPortalName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // Allows click events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // Parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
