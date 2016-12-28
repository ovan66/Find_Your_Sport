package com.bastian.findyousport.views.profile;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.models.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cutiko on 21-12-16.
 */

public class CreateProfile {

    private CreateProfileCallback callback;
    private List<String> urls = new ArrayList<>();
    private Profile profile;

    public CreateProfile(CreateProfileCallback callback, Profile profile) {
        this.callback = callback;
        this.profile = profile;
    }

    public void init(){
        uploadPhoto(0);
    }

    private void uploadPhoto(final int position){
        final String uid = new UserData().uid();
        final String photoName = String.valueOf(System.currentTimeMillis()) + ".jpeg";
        String refUrl = "gs://findyousport-c197e.appspot.com/profiles/"+ uid + "/" + photoName;

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl(refUrl);

        String path = profile.getPhotos().get(position);
        File file = new File(path);

        storageReference.putFile(Uri.fromFile(file)).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                int next = position+1;
                String url = "https://firebasestorage.googleapis.com/v0/b/findyousport-c197e.appspot.com/o/profiles%2F"+ uid + "%2f" + photoName + "?alt=media";
                urls.add(url);
                if (next < profile.getPhotos().size()) {
                    uploadPhoto(next);
                } else {
                    profile.setPhotos(urls);
                    createProfile();
                }
            }
        });
    }

    private void createProfile() {
        DatabaseReference databaseReference = new FirebaseRef().profiles();
        databaseReference.child(profile.getUid()).setValue(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                createSimpleProfile();
            }
        });
    }

    public void createSimpleProfile() {
        Profile simplified = new Profile();
        simplified.setUid(profile.getUid());
        simplified.setName(profile.getName());
        simplified.setLocation(profile.getLocation());
        simplified.setPhoto(profile.getPhotos().get(0));
        DatabaseReference databaseReference = new FirebaseRef().categoryProfile(profile.getCategory(), profile.getUid());
        databaseReference.setValue(simplified).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                callback.done();
            }
        });
    }


}
