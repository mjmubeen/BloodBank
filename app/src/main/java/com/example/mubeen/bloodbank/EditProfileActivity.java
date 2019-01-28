package com.example.mubeen.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {
    EditText nameText;
    EditText mobileText;
    Spinner loc;
    Spinner B_type;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_layout);
        nameText = findViewById(R.id.add_User_Name);
        mobileText = findViewById(R.id.add_Phone_Number);
        B_type = (Spinner) findViewById(R.id.Add_Spinner_Blood_type);
        loc = (Spinner) findViewById(R.id.Add_Spinner_City);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Save(View view) {
        String name = nameText.getText().toString();
        String mobile = mobileText.getText().toString();
        String location = loc.getSelectedItem().toString();
        String blood= B_type.getSelectedItem().toString();

        if (!validate(name,mobile)) {
            onSignupFailed();
            return;
        }
        saveInFirebase(name, mobile, location, blood);
    }


    public void onSignupSuccess() {
        Toast.makeText(this, "Saved Data", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(this, "Not Saved", Toast.LENGTH_LONG).show();
    }

    public boolean validate(String name, String mobile) {
        boolean valid = true;

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

    void saveInFirebase(String name, String mobile, String location, String blood)
    {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userId = firebaseUser.getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        UserInfo user = new UserInfo(userId, name, mobile, location, blood);
        mDatabase.child(userId).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    onSignupSuccess();
                }
                else {
                    onSignupFailed();
                }
            }
        });
    }

}