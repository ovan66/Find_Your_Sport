package com.bastian.findyousport.views.login;

import com.bastian.findyousport.data.FirebaseRef;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by cutiko on 21-12-16.
 */

public class ProfileValidation implements ValueEventListener {

    private ProfileCallback callback;

    public ProfileValidation(ProfileCallback callback) {
        this.callback = callback;
    }

    public void init(){
        DatabaseReference reference = new FirebaseRef().userProfile();
        reference.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.getValue() != null) {
            callback.loged();
        } else {
            callback.displayDialog();
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        callback.loged();
    }
}
