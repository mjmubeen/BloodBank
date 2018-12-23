package com.example.mubeen.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Login extends Fragment {

    EditText nameText;
    EditText passwordText;
    Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        nameText = view.findViewById(R.id.editText5);
        passwordText = view.findViewById(R.id.editText6);
        loginButton = view.findViewById(R.id.button3);
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
        return view;
    }

    public void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);


        String enterName = nameText.getText().toString();
        String enterPassword = passwordText.getText().toString();

        SharedPreferences sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name","");
        String password = sharedPreferences.getString("Password","");
        if(name.equals(enterName) && password.equals(enterPassword))
        {
            onLoginSuccess();
        }
        else
        {
            onLoginFailed();
        }
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        Toast.makeText(getActivity(), "Login", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        startActivity(intent);
    }

    public void onLoginFailed() {
        Toast.makeText(getActivity(), "Login failed", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();

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
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
