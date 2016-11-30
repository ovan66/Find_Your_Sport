package com.bastian.findyousport.views.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by santo_000 on 29-11-2016.
 */

public class FirebaseRef {

    public DatabaseReference root (){
        return FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference events(){
        return root().child("events");
    }

    public DatabaseReference favorites(){
        return root().child("favorites");
    }

}
