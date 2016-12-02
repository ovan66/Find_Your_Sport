package com.bastian.findyousport.views.details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.views.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String nameLocal = getIntent().getStringExtra(Constants.NAME_LOCAL);
        TextView nameLocalTv = (TextView) findViewById(R.id.reciverNameLocalTv);
        nameLocalTv.setText(nameLocal);

        final String sportName = getIntent().getStringExtra(Constants.SPORT_NAME);
        TextView sportNameTv = (TextView) findViewById(R.id.reciverSportNameTv);
        sportNameTv.setText(sportName);

        final String price = getIntent().getStringExtra(Constants.NAME_LOCAL);
        TextView priceTv= (TextView) findViewById(R.id.reciverPriceTv);
        priceTv.setText(price);

        final String schedules = getIntent().getStringExtra(Constants.NAME_LOCAL);
        TextView schedulesTv = (TextView) findViewById(R.id.reciverSchedulesTv);
        schedulesTv.setText(schedules);

        final String location= getIntent().getStringExtra(Constants.NAME_LOCAL);
        TextView locationTv = (TextView) findViewById(R.id.reciverLocationTv);
        locationTv.setText(location);

        final String phoneNum= getIntent().getStringExtra(Constants.NAME_LOCAL);
        TextView phoneNumTv = (TextView) findViewById(R.id.reciverPhoneNumTv);
        phoneNumTv.setText(phoneNum);

        final String facebook = getIntent().getStringExtra(Constants.NAME_LOCAL);
        TextView facebookTv = (TextView) findViewById(R.id.reciverFacebookTv);
        facebookTv.setText(facebook);

        final String email = getIntent().getStringExtra(Constants.NAME_LOCAL);
        TextView emailTv = (TextView) findViewById(R.id.reciverEmailTv);
        emailTv.setText(email);

        final String key = getIntent().getStringExtra(Constants.KEY);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        DatabaseReference reference = new FirebaseRef().favorites().child(key);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    Toast.makeText(DetailsActivity.this, "Lo tiene", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailsActivity.this, "No lo tiene", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                final DatabaseReference reference = new FirebaseRef().favorites();
                //TODO warning category is not seted!!!!!
                Event event = new Event(uid, nameLocal, sportName, price, schedules, location, phoneNum, email, facebook, key, "");
                reference.child(key).setValue(event);

                Toast.makeText(DetailsActivity.this, "Agregado a favoritos", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
