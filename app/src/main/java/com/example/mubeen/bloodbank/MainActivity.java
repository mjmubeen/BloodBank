package com.example.mubeen.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
	private static FragmentManager fragmentManager;
	private FirebaseAuth firebaseAuth;
	@Override
	protected void onStart() {
		super.onStart();
		if(firebaseAuth.getCurrentUser()!= null){
            this.finish();
            Intent intent = new Intent(this, EditProfileActivity.class);
            startActivity(intent);
        }
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		fragmentManager = getSupportFragmentManager();
        firebaseAuth = FirebaseAuth.getInstance();
		// If savedinstnacestate is null then replace login fragment
		if (savedInstanceState == null) {
			fragmentManager.beginTransaction().replace(R.id.frameContainer, new LoginFragment(), Utils.Login_Fragment).commit();
		}

	}

	// Replace Login Fragment with animation
	protected void replaceLoginFragment() {
		fragmentManager.beginTransaction().setCustomAnimations(R.anim.left_enter, R.anim.right_out)
				.replace(R.id.frameContainer, new LoginFragment(), Utils.Login_Fragment).commit();
	}

	@Override
	public void onBackPressed() {

		// Find the tag of signup and forgot password fragment
		Fragment SignUp_Fragment = fragmentManager.findFragmentByTag(Utils.SignUp_Fragment);
		Fragment ForgotPassword_Fragment = fragmentManager.findFragmentByTag(Utils.ForgotPassword_Fragment);

		// Check if both are null or not
		// If both are not null then replace login fragment else do backpressed
		// task

		if (SignUp_Fragment != null)
			replaceLoginFragment();
		else if (ForgotPassword_Fragment != null)
			replaceLoginFragment();
		else
			super.onBackPressed();
	}
}
