package com.example.mubeen.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Add extends AppCompatActivity {

    EditText nameText;
    EditText mobileText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nameText = findViewById(R.id.editText);
        mobileText = findViewById(R.id.editText2);
    }

    public void Save(View view) {

        if (!validate()) {
            onSignupFailed();
            return;
        }

        String name = nameText.getText().toString();
        String mobile = mobileText.getText().toString();

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name",name);
        editor.putString("mobile",mobile);
        editor.apply();
        onSignupSuccess();
    }


    public void onSignupSuccess() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSignupFailed() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_LONG);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String mobile = mobileText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        }
        else {
            nameText.setError(null);
        }

        if (mobile.isEmpty() || mobileText.length() < 10 || mobile.length() > 13) {
            mobileText.setError("invalid mobile number");
            valid = false;
        }
        else {
            mobileText.setError(null);
        }
        return valid;
    }
}