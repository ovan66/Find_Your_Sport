package com.bastian.findyousport;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.auth.AuthUI;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class FullscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_fullscreen);
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(
                                AuthUI.EMAIL_PROVIDER,
                                AuthUI.GOOGLE_PROVIDER,
                                AuthUI.FACEBOOK_PROVIDER)
                        .setTheme(R.style.FullscreenTheme)
                        .build(),
                RC_SIGN_IN);
    }
}

