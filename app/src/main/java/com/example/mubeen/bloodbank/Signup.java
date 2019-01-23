package com.example.mubeen.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;


public class Signup extends Fragment {
    EditText nameText;
    EditText passwordText;
    EditText rePasswordText;
    Button signupButton;
    Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signup, container, false);
        nameText = view.findViewById(R.id.signUpUserName);
        passwordText = view.findViewById(R.id.signUpPassword);
        rePasswordText = view.findViewById(R.id.signUpRePassword);
        signupButton = view.findViewById(R.id.signUp);
        loginButton = view.findViewById(R.id.signUp);// yahan par wo link dalni ha jo login ki taraf la jaye gi
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signup();
            }
        });
        return view;
    }

    public void signup() {

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();

        SharedPreferences sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name",name);
        editor.putString("Password",password);
        editor.apply();
        onSignupSuccess();
    }
    public void login() {

        startActivity(new Intent(this.getActivity(),Login.class));
    }


    public void onSignupSuccess() {
        signupButton.setEnabled(true);
        Toast.makeText(this.getActivity(), "Signed up", Toast.LENGTH_LONG);
        //Intent intent = new Intent(getActivity(), Home.class);
        startActivity(new Intent(this.getActivity(),Home.class));
    }

    public void onSignupFailed() {
        Toast.makeText(getActivity(), "Signed up failed", Toast.LENGTH_LONG);
        signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();
        String rePassword = rePasswordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        }
        else {
            nameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        }
        else {
            passwordText.setError(null);
        }

        if (password.equals(rePassword)) {
            valid=true;
        }
        else {
            rePasswordText.setError(null);
        }

        return valid;
    }
}