package com.bastian.findyousport.views.details.profile;

import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.models.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by cutiko on 19-01-17.
 */

public class GetProfile implements ValueEventListener {

    private ProfileCallback callback;

    public GetProfile(ProfileCallback callback) {
        this.callback = callback;
    }

    public void get(String uid){
        DatabaseReference reference = new FirebaseRef().userProfileUid(uid);
        reference.addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Profile profile = dataSnapshot.getValue(Profile.class);
        if (profile != null) {
            callback.setProfile(profile);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
