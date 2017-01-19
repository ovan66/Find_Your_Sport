package com.bastian.findyousport.views.details.profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bastian.findyousport.R;
import com.bastian.findyousport.models.Profile;
import com.bastian.findyousport.views.profiles.ProfilesActivity;


public class ProfileFragment extends Fragment implements ProfileCallback {

    private TextView name,location,description, phoneNum, email, facebook;
    private ImageView photos;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);


    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.profileFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Create Favorites in Firebase

            }
        });

        name = (TextView) view.findViewById(R.id.institutionTv);
        location = (TextView) view.findViewById(R.id.locationTv);
        description = (TextView) view.findViewById(R.id.descriptionTv);
        phoneNum = (TextView) view.findViewById(R.id.phoneNumTv);
        email = (TextView) view.findViewById(R.id.emailTv);
        facebook = (TextView) view.findViewById(R.id.facebookTv);
        photos = (ImageView) view.findViewById(R.id.galleryIv);

        String uid = getActivity().getIntent().getStringExtra(ProfilesActivity.UID);
        new GetProfile(this).get(uid);
    }

    @Override
    public void setProfile(Profile profile) {
        name.setText(profile.getName());
        location.setText(profile.getLocation());
        description.setText(profile.getDescription());
        phoneNum.setText(profile.getPhoneNum());
        email.setText(profile.getEmail());
        facebook.setText(profile.getFacebook());
    }
}