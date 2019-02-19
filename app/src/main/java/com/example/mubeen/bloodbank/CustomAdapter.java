package com.example.mubeen.bloodbank;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<UserInfo> users;

    CustomAdapter(Context context, ArrayList<UserInfo> users)
    {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.donor_info_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewBlood = holder.textViewBlood;
        TextView textViewGender = holder.textViewGender;
        TextView textViewLocation = holder.textViewLocation;

        textViewName.setText(users.get(position).getName());
        textViewBlood.setText(users.get(position).getBlood());
        textViewGender.setText(users.get(position).getGender());
        textViewLocation.setText(users.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView textViewName;
        TextView textViewBlood;
        TextView textViewGender;
        TextView textViewLocation;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.Person_Name);
            this.textViewBlood = itemView.findViewById(R.id.Person_Blood);
            this.textViewGender =  itemView.findViewById(R.id.Person_Gender);
            this.textViewLocation = itemView.findViewById(R.id.Person_Location);
        }
    }
}