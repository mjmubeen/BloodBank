package com.example.mubeen.bloodbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


public class Intro extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        function();
    }

    private void function() {
        sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstTime",true);
        editor.apply();
        firstTime = sharedPreferences.getBoolean("firstTime",true);

        if (firstTime){
            Handler handler= new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    firstTime = false;
                    editor.putBoolean("firstTime",firstTime);
                    editor.apply();
                }
            }, 5000);
        }
        Intent i  = new Intent(Intro.this,Signup.class);
        startActivity(i);
        finish();


    }

    //ImageView adver = findViewById(R.id.imageView4);


}