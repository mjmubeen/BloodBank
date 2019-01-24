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

public class SignUpFragment extends Fragment implements OnClickListener {
	private View view;
	private EditText emailId, password, confirmPassword;
	private TextView login;
	private Button signUpButton;
	private CheckBox terms_conditions;
//	private FirebaseAuth firebaseAuth;

	public SignUpFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.signup_layout, container, false);
//		firebaseAuth = FirebaseAuth.getInstance();
		initViews();
		setListeners();
		return view;
	}

	// Initialize all views
	private void initViews() {
		emailId = view.findViewById(R.id.userEmailId);
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
		String getEmailId = emailId.getText().toString();
		String getPassword = password.getText().toString();
		String getConfirmPassword = confirmPassword.getText().toString();

		// Pattern match for email id
		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);

		// Check if all strings are null or not
		if (getEmailId.equals("") || getEmailId.length() == 0 || getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("") || getConfirmPassword.length() == 0)

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
			saveFirebase(getEmailId, getPassword);

	}

	private void saveFirebase(String getEmailId, String getPassword)
	{
		/*firebaseAuth.createUserWithEmailAndPassword(getEmailId, getPassword)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						//checking if success
						if(task.isSuccessful()){
							//display some message here
							Toast.makeText(getActivity(),"Successfully registered",Toast.LENGTH_LONG).show();
						}else{
							//display some message here
							Toast.makeText(getActivity(),"Registration Error",Toast.LENGTH_LONG).show();
						}
					}
				});*/
	}
}