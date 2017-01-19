package com.bastian.findyousport.views.details;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bastian.findyousport.R;
import com.bastian.findyousport.adapters.SectionsPagerAdapter;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.models.Profile;
import com.bastian.findyousport.views.profiles.ProfilesActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import retrofit2.http.POST;


public class ProfileFragment extends Fragment implements DetailsCallback{

    private TextView name,location,description, phoneNum, email, facebook;
    private ImageView photos;

    public static final String UID = "com.bastian.findyousport.views.profiles.ProfilesActivity.UID";


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
        return inflater.inflate(R.layout.details_profile_view, container, false);


    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());


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
        DatabaseReference reference = new FirebaseRef().userProfileUid(uid);
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name.setText((dataSnapshot.child("name").getValue()).toString());
                location.setText((dataSnapshot.child("location").getValue()).toString());
                description.setText((dataSnapshot.child("description").getValue()).toString());
                phoneNum.setText((dataSnapshot.child("phoneNum").getValue()).toString());
                email.setText((dataSnapshot.child("email").getValue()).toString());
                facebook.setText((dataSnapshot.child("facebook").getValue()).toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }


        });
    }
}
