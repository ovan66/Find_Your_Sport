package com.bastian.findyousport.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by santo_000 on 29-11-2016.
 */

public class FirebaseRef {

    private DatabaseReference root (){
        return FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference profile(){
        return root().child("profiles");
    }

    public DatabaseReference userProfile(){
        return root().child("profiles").child(new UserData().uid());
    }
    public DatabaseReference userProfileUid(String uid){
        return profile().child(uid);
    }

    private DatabaseReference event() {
        return root().child("event");
    }

    public DatabaseReference userEvent(){
        return event().child(new UserData().uid());
    }

    public DatabaseReference userEvents(String uid) {
        return event().child(uid);
    }

    public DatabaseReference favorites(){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return root().child("favorites").child(uid);
    }

    public DatabaseReference category (String category){
        return root().child(category);
    }

    public DatabaseReference categoryProfile(String category, String uid){
        return category(category).child(uid);
    }

    public DatabaseReference userSubsription(String key){
        return root().child("subscriptions").child(new UserData().uid());
    }

    public DatabaseReference eventSuscription(String uid, String key){
        return userEvents(uid).child(key);
    }

}
