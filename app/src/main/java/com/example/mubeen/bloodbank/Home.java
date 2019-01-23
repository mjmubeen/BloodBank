package com.example.mubeen.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Home extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView add = findViewById(R.id.homeprofile);
        ImageView search = findViewById(R.id.homesearch);
        ImageView Request= findViewById(R.id.homeRequest);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Add.class));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*startActivity(new Intent(Home.this,searchview.class));*/
            }
        });

        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Blood_Request.class));
            }
        });
    }
}
