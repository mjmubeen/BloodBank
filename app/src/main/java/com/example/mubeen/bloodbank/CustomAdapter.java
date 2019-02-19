package com.example.mubeen.bloodbank;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<UserInfo> dataSet;

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewBlood;
        TextView textViewGender;
        TextView textViewLocation;

        MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.Person_Name);
            this.textViewBlood = itemView.findViewById(R.id.Person_Blood);
            this.textViewGender =  itemView.findViewById(R.id.Person_Gender);
            this.textViewLocation = itemView.findViewById(R.id.Person_Location);
        }
    }

    CustomAdapter(ArrayList<UserInfo> data) {
        this.dataSet = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donar_info_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewBlood = holder.textViewBlood;
        TextView textViewGender = holder.textViewGender;
        TextView textViewLocation = holder.textViewLocation;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewBlood.setText(dataSet.get(listPosition).getBlood());
        textViewGender.setText(dataSet.get(listPosition).getGender());
        textViewLocation.setText(dataSet.get(listPosition).getLocation());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}