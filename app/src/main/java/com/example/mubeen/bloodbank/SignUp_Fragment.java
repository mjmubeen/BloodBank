package com.example.mubeen.bloodbank;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp_Fragment extends Fragment implements OnClickListener {
	private View view;
	private EditText fullName, emailId, mobileNumber, location, password, confirmPassword;
	private TextView login;
	private Button signUpButton;
	private CheckBox terms_conditions;

	public SignUp_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.signup_layout, container, false);
		initViews();
		setListeners();
		return view;
	}

	// Initialize all views
	private void initViews() {
		fullName = view.findViewById(R.id.fullName);
		emailId = view.findViewById(R.id.userEmailId);
		mobileNumber = view.findViewById(R.id.mobileNumber);
		location = view.findViewById(R.id.location);
		password = view.findViewById(R.id.password);
		confirmPassword = view.findViewById(R.id.confirmPassword);
		signUpButton = view.findViewById(R.id.signUpBtn);
		login = view.findViewById(R.id.already_user);
		terms_conditions = view.findViewById(R.id.terms_conditions);
	}

	// Set Listeners
	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signUpBtn:

			// Call checkValidation method
			checkValidation();
			break;

		case R.id.already_user:

			// Replace login fragment
			new MainActivity().replaceLoginFragment();
			break;
		}

	}

	// Check Validation Method
	private void checkValidation() {

		// Get all edittext texts
		String getFullName = fullName.getText().toString();
		String getEmailId = emailId.getText().toString();
		String getMobileNumber = mobileNumber.getText().toString();
		String getLocation = location.getText().toString();
		String getPassword = password.getText().toString();
		String getConfirmPassword = confirmPassword.getText().toString();

		// Pattern match for email id
		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);

		// Check if all strings are null or not
		if (getFullName.equals("") || getFullName.length() == 0
				|| getEmailId.equals("") || getEmailId.length() == 0
				|| getMobileNumber.equals("") || getMobileNumber.length() == 0
				|| getLocation.equals("") || getLocation.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("")
				|| getConfirmPassword.length() == 0)

			Toast.makeText(getActivity(),  "All fields are required.", Toast.LENGTH_SHORT).show();

		// Check if email id valid or not
		else if (!m.find())
			Toast.makeText(getActivity(),  "Your Email Id is Invalid.", Toast.LENGTH_SHORT).show();

		// Check if both password should be equal
		else if (!getConfirmPassword.equals(getPassword))
			Toast.makeText(getActivity(),  "Both password doesn't match.", Toast.LENGTH_SHORT).show();

		// Make sure user should check Terms and Conditions checkbox
		else if (!terms_conditions.isChecked())
			Toast.makeText(getActivity(),  "Please select Terms and Conditions.", Toast.LENGTH_SHORT).show();

		// Else do signup or do your stuff
		else
			Toast.makeText(getActivity(), "Do SignUp.", Toast.LENGTH_SHORT).show();

	}
}
