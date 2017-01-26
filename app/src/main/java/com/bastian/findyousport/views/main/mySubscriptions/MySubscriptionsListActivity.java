package com.bastian.findyousport.views.main.mySubscriptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.views.details.DetailsActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import static com.bastian.findyousport.views.profiles.ProfilesActivity.UID;

public class MySubscriptionsListActivity extends AppCompatActivity {

    public static final String EVENT = "com.bastian.findyousport.views.sports.sportList.EVENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_favorite_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String key = new UserData().uid();
        DatabaseReference reference = new FirebaseRef().userSubsriptions(key);
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Event, SubscriptionHolder>
                (Event.class, R.layout.list_item_subscriptions, SubscriptionHolder.class, reference) {


            @Override
            protected void populateViewHolder(SubscriptionHolder viewHolder, final Event model, int position) {
                final String days = model.getDays().toString().replaceAll("\\[","").replaceAll("]","");
                final String schedule = model.getStart() + " - " +  model.getEnd();
                final String uid = model.getUid();
                viewHolder.setInstitution(model.getInstitution());
                viewHolder.setName(model.getName());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setDays(days);
                viewHolder.setSchedule(schedule);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MySubscriptionsListActivity.this, DetailsActivity.class);
                        intent.putExtra(UID, uid);
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    public static class SubscriptionHolder extends RecyclerView.ViewHolder {
        private TextView institution, className, location, days, schedule;

        public SubscriptionHolder(View itemView) {
            super(itemView);
            institution = (TextView) itemView.findViewById(R.id.institutionNameTv);
            className = (TextView) itemView.findViewById(R.id.classNameTv);
            location = (TextView) itemView.findViewById(R.id.locationTv);
            days = (TextView) itemView.findViewById(R.id.daysTv);
            schedule = (TextView) itemView.findViewById(R.id.scheduleTv);


        }

        public void setInstitution(String institution){
            this.location.setText(institution);
        }

        public void setName(String className) {
            this.className.setText(className);
        }

        public void setLocation(String location){
            this.location.setText(location);
        }

        public void setDays(String days){
            this.location.setText(days);
        }

        public void setSchedule(String schedule){
            this.location.setText(schedule);
        }

    }
}