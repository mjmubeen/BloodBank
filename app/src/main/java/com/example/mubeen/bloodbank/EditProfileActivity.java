package com.example.mubeen.bloodbank;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Objects;

public class EditProfileActivity extends AppCompatActivity {
    EditText nameText, mobileText, birthText;
    Spinner loc, B_type, Gender;
    boolean databtn = false;
    private FirebaseAuth mAuth;
    ProgressDialog pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_layout);
        nameText = findViewById(R.id.add_User_Name);
        mobileText = findViewById(R.id.add_Phone_Number);
        birthText = findViewById(R.id.dateText);
        B_type = findViewById(R.id.Add_Spinner_Blood_type);
        loc = findViewById(R.id.Add_Spinner_City);
        Gender = findViewById(R.id.Add_Spinner_Gender);
        mAuth = FirebaseAuth.getInstance();
    }

    public void selectDate(View view) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        databtn = true;
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birthText.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        Objects.requireNonNull(datePickerDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    public void Save(View view) {
        pg = new ProgressDialog(this);
        pg.setMessage("Saving....");
        pg.show();

        String name = nameText.getText().toString();
        String mobile = mobileText.getText().toString();
        String birth = birthText.getText().toString();
        String location = loc.getSelectedItem().toString();
        String blood = B_type.getSelectedItem().toString();
        String gender = Gender.getSelectedItem().toString();
        if (!validate(name,mobile)) {
            onSignupFailed();
        }
        else {
            saveInFirebase(name, mobile, location, blood, gender, birth);
        }
    }


    public void onSignupSuccess() {
        pg.dismiss();
        Toast.makeText(this, "Saved Data", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
        finish();
    }

    public void onSignupFailed() {
        pg.dismiss();
        Toast.makeText(this, "Not Saved", Toast.LENGTH_LONG).show();
    }

    public boolean validate(String name, String mobile) {
        boolean validName = true, validMobile = true;
        if(!databtn){
            birthText.setError("Please select a date");
        }
        else{
            birthText.setError(null);
        }
        if (name.isEmpty() || name.length() < 2) {
            nameText.setError("at least 2 characters");
            validName = false;
        }
        else {
            nameText.setError(null);
        }

        if (mobile.isEmpty() || mobileText.length() < 10 || mobile.length() > 11) {
            mobileText.setError("invalid mobile number");
            validMobile = false;
        }
        else {
            mobileText.setError(null);
        }
        return (databtn && validName && validMobile);
    }

    void saveInFirebase(String name, String mobile, String location, String blood, String gender, String birth)
    {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        String userId = firebaseUser.getUid();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        UserInfo user = new UserInfo(userId, name, mobile, location, blood, gender,birth);
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