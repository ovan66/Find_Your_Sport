package com.bastian.findyousport.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.category;

/**
 * Created by santo_000 on 29-11-2016.
 */

public class FirebaseRef {

    public DatabaseReference root (){
        return FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference profiles(){
        return root().child("profiles");
    }

    public DatabaseReference events(String category){
        return root().child(category).child("profiles").child("event");
    }

    public DatabaseReference favorites(){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return root().child("favorites").child(uid);
    }

}
