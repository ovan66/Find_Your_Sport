package com.bastian.findyousport.views.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bastian.findyousport.R;
import com.bastian.findyousport.views.main.MainActivity;
import com.bastian.findyousport.views.owner.OwnerCreateActivity;
import com.firebase.ui.auth.AuthUI;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class FullscreenActivity extends AppCompatActivity implements LoginCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Intent intent = new Intent(this, OwnerCreateActivity.class);
        startActivity(intent);

        //new LoginValidation(this).init();
    }

    @Override
    public void loged() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public void singUp() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(
                                AuthUI.GOOGLE_PROVIDER,
                                AuthUI.FACEBOOK_PROVIDER)
                        .setTheme(R.style.LoginTheme)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            loged();
        }
    }
}

