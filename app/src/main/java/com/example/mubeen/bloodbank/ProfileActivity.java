package com.example.mubeen.bloodbank;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    TextView email, name, mobile, location, blood, gender, birth;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    String userId;
    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        email = findViewById(R.id.profileEmailText);
        name = findViewById(R.id.tv_name);
        mobile = findViewById(R.id.profilePhoneText);
        location = findViewById(R.id.tv_address);
        blood = findViewById(R.id.tv_blood);
        gender = findViewById(R.id.profileGenderText);
        birth = findViewById(R.id.profileDateText);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        userId = firebaseUser.getUid();
        getDataFromFirebase();
    }

    private void getDataFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users").child(userId);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);
                change(userInfo);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    private void change(UserInfo user) {
        name.setText(user.getName());
        mobile.setText(user.getMobile());
        location.setText(user.getLocation());
        blood.setText(user.getBlood());
        gender.setText(user.getGender());
        birth.setText(user.getBirth());
    }

    public void EditProfile(View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }
}