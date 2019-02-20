package com.example.mubeen.bloodbank;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends RecyclerView.Adapter<Adapter.ProgrammingViewHolder>
{
    private bank data[];
    public Adapter(bank data[])
    {
        this.data=data;
    }

    @NonNull
    @Override
    public ProgrammingViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.bank_layout,parent,false);
        return new ProgrammingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgrammingViewHolder holder, int position)
    {
        ImageView imageViewPerson = holder.imageViewPerson;
        TextView textViewName = holder.textViewName;
        TextView textViewLocation = holder.textViewLocation;
        imageViewPerson.setImageResource(R.drawable.bloodbank);
        textViewName.setText(data[position].getName());
        textViewLocation.setText(data[position].getLocation());
    }

    @Override
    public int getItemCount()
    {
        return data.length;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewPerson;
        TextView textViewName;
        TextView textViewLocation;

        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageViewPerson = itemView.findViewById(R.id.Person);
            this.textViewName = itemView.findViewById(R.id.Person_Name);
            this.textViewLocation = itemView.findViewById(R.id.Person_Location);
        }
    }
}
