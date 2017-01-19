package com.bastian.findyousport.views.details.events;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.views.profiles.ProfilesActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;


public class EventsFragment extends Fragment implements SubscriptionCallback {

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
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView bottomSheet = (CardView) view.findViewById(R.id.bottomSheet);
        final BottomSheetBehavior sheetBehavior = BottomSheetBehavior.from(bottomSheet);
        final TextView nameTv = (TextView) view.findViewById(R.id.classNameTv);
        final TextView daysTv = (TextView) view.findViewById(R.id.daysTv);
        final TextView scheduleTv = (TextView) view.findViewById(R.id.scheduleTv);
        final Button signBtn = (Button) view.findViewById(R.id.signBtn);


        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.eventsRv);

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        final String uid = getActivity().getIntent().getStringExtra(ProfilesActivity.UID);
        DatabaseReference reference = new FirebaseRef().userEvents(uid);

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Event, EventHolder>(Event.class, R.layout.list_item_event, EventHolder.class, reference) {
            @Override
            protected void populateViewHolder(EventHolder viewHolder, final Event model, int position) {
                viewHolder.setIsRecyclable(false);
                final String name = model.getName();
                final String days = model.getDays().toString().replaceAll("\\[","").replaceAll("]","");
                final String schedule = model.getStart() + " - " +  model.getEnd();
                viewHolder.setName(name);
                viewHolder.setDays(days);
                viewHolder.setSchedule(schedule);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        nameTv.setText(name);
                        daysTv.setText(days);
                        scheduleTv.setText(schedule);
                        signBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d("CLICK", "click");
                                new Subscribe(EventsFragment.this, model).validation(uid);
                            }
                        });
                    }
                });

            }
        };

        recycler.setAdapter(adapter);

    }

    @Override
    public void success() {
        Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void badLuck() {
        Toast.makeText(getContext(), "Qué mala suerte, mientras mirabas se acabaron los cupos", Toast.LENGTH_SHORT).show();
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

        public void setDays(String days){
            this.days.setText(days);
        }

        public void setSchedule (String schedule){
            this.schedule.setText(schedule);

        }
    }

}
