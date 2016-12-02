package com.bastian.findyousport.application;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by cutiko on 02-12-16.
 */

public class SportApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

}
