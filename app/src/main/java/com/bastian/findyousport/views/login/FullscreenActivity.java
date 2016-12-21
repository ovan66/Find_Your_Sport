package com.bastian.findyousport.views.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.bastian.findyousport.R;
import com.bastian.findyousport.views.main.MainActivity;
import com.bastian.findyousport.views.owner.OwnerCreateActivity;
import com.firebase.ui.auth.AuthUI;

import static com.firebase.ui.auth.ui.AcquireEmailHelper.RC_SIGN_IN;

public class FullscreenActivity extends AppCompatActivity implements LoginCallback, ProfileCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        new LoginValidation(this).init();
    }

    @Override
    public void loged() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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
            new ProfileValidation(this).init();
        }
    }

    @Override
    public void displayDialog() {
        new AlertDialog.Builder(this)
                .setTitle("¿Deseas publicar clases?")
                .setMessage("FindYourSport es para buscar y crear clases. Para crear clases primero completa tu perfil. Puedas hacerlo más tarde en el menú lateral")
                .setPositiveButton("Quiero crear clases", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(FullscreenActivity.this, OwnerCreateActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("Quiero buscar clases", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loged();
                    }
                })
                .show();
    }

}

