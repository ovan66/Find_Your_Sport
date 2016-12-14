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
import com.bastian.findyousport.views.sports.sportList.SportListFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import static android.os.Build.VERSION_CODES.M;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Event event = (Event) getIntent().getSerializableExtra(SportListFragment.EVENT);
        Toast.makeText(this, event.getSportName(), Toast.LENGTH_SHORT).show();

        final String nameLocal = event.getNameLocal();
        TextView nameLocalTv = (TextView) findViewById(R.id.reciverNameLocalTv);
        nameLocalTv.setText(nameLocal);

        final String sportName = event.getSportName();
        TextView sportNameTv = (TextView) findViewById(R.id.reciverSportNameTv);
        sportNameTv.setText(sportName);

        final String price = event.getPrice();
        TextView priceTv= (TextView) findViewById(R.id.reciverPriceTv);
        priceTv.setText(price);

        final String schedules = event.getSchedules();
        TextView schedulesTv = (TextView) findViewById(R.id.reciverSchedulesTv);
        schedulesTv.setText(schedules);

        final String location= event.getLocation();
        TextView locationTv = (TextView) findViewById(R.id.reciverLocationTv);
        locationTv.setText(location);

        final String phoneNum= event.getPhoneNum();
        TextView phoneNumTv = (TextView) findViewById(R.id.reciverPhoneNumTv);
        phoneNumTv.setText(phoneNum);

        final String facebook = event.getFacebook();
        TextView facebookTv = (TextView) findViewById(R.id.reciverFacebookTv);
        facebookTv.setText(facebook);

        final String email = event.getEmail();
        TextView emailTv = (TextView) findViewById(R.id.reciverEmailTv);
        emailTv.setText(email);

        final String key = event.getKey();
        final FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        final FloatingActionButton fabDeleted = (FloatingActionButton) findViewById(R.id.fabDelete);


        DatabaseReference reference = new FirebaseRef().favorites().child(key);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    //Toast.makeText(DetailsActivity.this, "Lo tiene", Toast.LENGTH_SHORT).show();
                    fabDeleted.setVisibility(View.VISIBLE);
                    fabAdd.setVisibility(View.GONE);
                    fabDeleted.setOnClickListener(new View.OnClickListener() {
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
                } else {
                    //Toast.makeText(DetailsActivity.this, "No lo tiene", Toast.LENGTH_SHORT).show();
                    fabDeleted.setVisibility(View.GONE);
                    fabAdd.setVisibility(View.VISIBLE);
                    fabDeleted.setOnClickListener(new View.OnClickListener() {
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
