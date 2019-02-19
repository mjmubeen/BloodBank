package com.example.mubeen.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginFragment extends Fragment implements OnClickListener {
	private View view;
	private EditText emailId, password;
	private Button loginButton;
	private TextView forgotPassword, signUp;
	private CheckBox show_hide_password;
	private LinearLayout loginLayout;
	private static Animation shakeAnimation;
	private static FragmentManager fragmentManager;
	private FirebaseAuth firebaseAuth;
	ProgressDialog pg;

	public LoginFragment() {

	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.login_layout, container, false);
		firebaseAuth = FirebaseAuth.getInstance();
		initViews();
		setListeners();
		return view;
	}

	// Initiate Views
	private void initViews() {
		fragmentManager = getActivity().getSupportFragmentManager();

		emailId = view.findViewById(R.id.login_emailid);
		password = view.findViewById(R.id.login_password);
		loginButton = view.findViewById(R.id.loginBtn);
		forgotPassword = view.findViewById(R.id.forgot_password);
		signUp = view.findViewById(R.id.createAccount);
		show_hide_password = view.findViewById(R.id.show_hide_password);
		loginLayout = view.findViewById(R.id.login_layout);

		// Load ShakeAnimation
		shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
	}

	// Set Listeners
	private void setListeners() {
		loginButton.setOnClickListener(this);
		forgotPassword.setOnClickListener(this);
		signUp.setOnClickListener(this);

		// Set check listener over checkbox for showing and hiding password
		show_hide_password.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton button, boolean isChecked) {

						// If it is checkec then show password else hide
						// password
						if (isChecked) {
							show_hide_password.setText(R.string.hide_pwd);// change
																			// checkbox
																			// text
							password.setInputType(InputType.TYPE_CLASS_TEXT);
							password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());// show password
						}
						else {
							show_hide_password.setText(R.string.show_pwd);// change
																			// checkbox
																			// text

							password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
							password.setTransformationMethod(PasswordTransformationMethod.getInstance());// hide password

						}

					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginBtn:
			checkValidation();
			break;

		case R.id.forgot_password:

			// Replace forgot password fragment with animation
			fragmentManager
					.beginTransaction()
					.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
					.replace(R.id.frameContainer, new ForgotPassword_Fragment(), Utils.ForgotPassword_Fragment).commit();
			break;
		case R.id.createAccount:

			// Replace signup frgament with animation
			fragmentManager.beginTransaction().setCustomAnimations(R.anim.right_enter, R.anim.left_out)
					.replace(R.id.frameContainer, new SignUpFragment(), Utils.SignUp_Fragment).commit();
			break;
		}

	}

	// Check Validation before login
	private void checkValidation() {
		// Get email id and password
		String getEmailId = emailId.getText().toString();
		String getPassword = password.getText().toString();

		// Check patter for email id
		Pattern p = Pattern.compile(Utils.regEx);

		Matcher m = p.matcher(getEmailId);

		// Check for both field is empty or not
		if (getEmailId.equals("") || getEmailId.length() == 0 || getPassword.equals("") || getPassword.length() == 0) {
			loginLayout.startAnimation(shakeAnimation);
			Toast.makeText(getActivity(), "Enter both credentials.", Toast.LENGTH_SHORT).show();

		}
		// Check if email id is valid or not
		else if (!m.find())
			Toast.makeText(getActivity(), "Your Email Id is Invalid.", Toast.LENGTH_SHORT).show();

		else if(getPassword.length() < 5)
			Toast.makeText(getActivity(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
		// Else do login and do your stuff
		else
        {
            pg = new ProgressDialog(getActivity());
            pg.setMessage("Please Wait....");
            pg.show();
            checkInFirebase(getEmailId, getPassword);
	    }

	}

	private void checkInFirebase(String getEmailId, String getPassword) {
		try {
			firebaseAuth.signInWithEmailAndPassword(getEmailId, getPassword)
					.addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
						@Override
						public void onComplete(@NonNull Task<AuthResult> task) {
							if (!task.isSuccessful()) {
								Toast.makeText(getActivity(), "Authentication Failed", Toast.LENGTH_LONG).show();
								Log.v("error", Objects.requireNonNull(task.getResult()).toString());
								pg.dismiss();
							} else {
								Objects.requireNonNull(getActivity()).finish();
								Intent intent = new Intent(getActivity(), NavigationDrawerActivity.class);
								startActivity(intent);
								pg.dismiss();
							}
						}
					});
		}
		catch(Exception e)
		{
			Toast.makeText(getActivity(),  "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}