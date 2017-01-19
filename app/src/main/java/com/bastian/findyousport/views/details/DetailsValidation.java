package com.bastian.findyousport.views.details;

import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.views.details.profile.ProfileCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Bastian on 07-01-2017.
 */

public class DetailsValidation implements ValueEventListener {

    private ProfileCallback callback;

    public DetailsValidation(ProfileCallback callback) {
        this.callback = callback;
    }

    public void init(){
        DatabaseReference reference = new FirebaseRef().userProfile();
        reference.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.getValue() != null) {

        } else {

        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
