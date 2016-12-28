package com.bastian.findyousport.views.profiles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.models.Profile;
import com.bastian.findyousport.views.main.categoryList.CategoriesFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

public class ProfilesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        String category = getIntent().getStringExtra(CategoriesFragment.CATEGORY);
        getSupportActionBar().setTitle(category);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.profilesRv);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference reference = new FirebaseRef().category(category);
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Profile, ProfileHolder>(Profile.class, R.layout.list_item_profile, ProfileHolder.class, reference) {
            @Override
            protected void populateViewHolder(ProfileHolder viewHolder, Profile model, int position) {
                viewHolder.setPhoto(model.getPhoto());
                viewHolder.setName(model.getName());
                viewHolder.setLocation(model.getLocation());
            }
        };

        recycler.setAdapter(adapter);


    }

    public static class ProfileHolder extends RecyclerView.ViewHolder {

        private final ImageView photo;
        private final TextView name, location;

        public ProfileHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.institutionIv);
            name = (TextView) itemView.findViewById(R.id.nameLocalSportTv);
            location = (TextView) itemView.findViewById(R.id.locationTv);
        }

        public void setPhoto(String url){
            Picasso.with(itemView.getContext()).load(url).placeholder(R.mipmap.login_background).fit().centerCrop().noFade().into(photo);
        }

        public void setName(String name) {
            this.name.setText(name);
        }

        public void setLocation(String location){
            this.location.setText(location);
        }
    }
}

