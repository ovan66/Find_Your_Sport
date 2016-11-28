package com.bastian.findyousport.views.login;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Bastian on 26-11-2016.
 */

public class LoginValidation {

    private LoginCallback callback;

    public LoginValidation(LoginCallback callback){
        this.callback = callback;
    }

    public void init(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            callback.loged();
        } else {
            callback.singUp();
        }
    }
}
