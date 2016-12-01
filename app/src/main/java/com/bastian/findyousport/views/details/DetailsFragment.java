package com.bastian.findyousport.views.details;


import android.content.Intent;
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
import com.bastian.findyousport.views.Constants;
import com.bastian.findyousport.views.main.sportList.SportListFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycler = (RecyclerView) view;
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        DatabaseReference userData = new FirebaseRef().events();

        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Event, DetailsFragment.DetailHolder>(Event.class, R.layout.detail_info, DetailsFragment.DetailHolder.class, userData) {

            @Override
            protected void populateViewHolder(DetailHolder viewHolder, Event model, int position) {

                viewHolder.setLocalName(model.getNameLocal());
                viewHolder.setSportName(model.getSportName());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setSchedules(model.getSchedules());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setPhoneNum(model.getPhoneNum());
                viewHolder.setFacebook(model.getFacebook());
                viewHolder.setEmail(model.getEmail());

            }
        };

    }
    public static class DetailHolder extends RecyclerView.ViewHolder {

        public DetailHolder(View itemView) {
            super(itemView);
        }

        public void setLocalName(String name){
            TextView localName = (TextView) itemView.findViewById(R.id.reciverNameLocalTv);
            localName.setText(name);
        }
        public void setSportName(String name){
            TextView sportName = (TextView) itemView.findViewById(R.id.reciverSportNameTv);
            sportName.setText(name);
        }
        public void setPrice(String name){
            TextView price = (TextView) itemView.findViewById(R.id.reciverPriceTv);
            price.setText(name);
        }
        public void setSchedules(String name){
            TextView schedules = (TextView) itemView.findViewById(R.id.reciverSchedulesTv);
            schedules.setText(name);
        }
        public void setLocation(String name){
            TextView location = (TextView) itemView.findViewById(R.id.reciverLocationTv);
            location.setText(name);
        }
        public void setPhoneNum(String name){
            TextView phoneNum = (TextView) itemView.findViewById(R.id.reciverPhoneNumTv);
            phoneNum.setText(name);
        }
        public void setFacebook(String name){
            TextView facebook = (TextView) itemView.findViewById(R.id.reciverFacebookTv);
            facebook.setText(name);
        }
        public void setEmail(String name){
            TextView email = (TextView) itemView.findViewById(R.id.reciverEmailTv);
            email.setText(name);
        }
    }
}
