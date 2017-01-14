package com.bastian.findyousport.views.details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.views.profiles.ProfilesActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.List;


public class EventsFragment extends Fragment {

    public EventsFragment() {
        // Required empty public constructor
    }

    public static EventsFragment newInstance() {
        return new EventsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycler = (RecyclerView) view;

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        String uid = getActivity().getIntent().getStringExtra(ProfilesActivity.UID);
        DatabaseReference reference = new FirebaseRef().userEvents(uid);

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Event, EventHolder>(Event.class, R.layout.list_item_event, EventHolder.class, reference) {
            @Override
            protected void populateViewHolder(EventHolder viewHolder, Event model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setDays(model.getDays());
                viewHolder.setSchedule(model.getStart(), model.getEnd());
            }
        };

        recycler.setAdapter(adapter);
    }

    public static class EventHolder extends RecyclerView.ViewHolder {

        private final TextView name, days, schedule;


        public EventHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.classNameTv);
            days = (TextView) itemView.findViewById(R.id.daysTv);
            schedule = (TextView) itemView.findViewById(R.id.scheduleTv);
        }

        public void setName(String name) {
            this.name.setText(name);
        }

        public void setDays(List<String> days){
            String text = "";
            for (String day : days) {
                text = text + day + ", ";
            }
            this.days.setText(text);

        }

        public void setSchedule (String start, String end){
            this.schedule.setText(start + " - " + end);

        }
    }

}
