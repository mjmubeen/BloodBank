package com.example.mubeen.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {
    EditText nameText;
    EditText mobileText;
    Spinner loc;
    Spinner B_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nameText = findViewById(R.id.add_User_Name);
        mobileText = findViewById(R.id.add_Phone_Number);
        B_type = (Spinner) findViewById(R.id.Add_Spinner_Blood_type);
        loc = (Spinner) findViewById(R.id.Add_Spinner_City);
       // loc = spinner_name.getSelectedItem().toString();
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
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
    }

    public void onSignupFailed() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_LONG);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String mobile = mobileText.getText().toString();
        String location = loc.getSelectedItem().toString();
        String blood= B_type.getSelectedItem().toString();
        if (name.isEmpty() || name.length() < 2) {
            nameText.setError("at least 2 characters");
            valid = false;
        }
        else {
            nameText.setError(null);
        }

        if (mobile.isEmpty() || mobileText.length() < 10 || mobile.length() > 11) {
            mobileText.setError("invalid mobile number");
            valid = false;
        }
        else {
            mobileText.setError(null);
        }
        return valid;
    }

}