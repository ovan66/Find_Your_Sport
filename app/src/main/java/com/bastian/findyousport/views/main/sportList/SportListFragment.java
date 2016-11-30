package com.bastian.findyousport.views.main.sportList;


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
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.models.Event;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportListFragment extends Fragment {


    public SportListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sport_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycler = (RecyclerView) view;

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseReference reference = new FirebaseRef().events().child(new UserData().user().getUid());
        //new FirebaseIndexListAdapter<Event>(this, Event.class, R.layout.list_item_post, reference)
    }

    public static class EventHolder extends RecyclerView.ViewHolder {


        public EventHolder(View itemView) {
            super(itemView);
        }

        public void setName(String name){
            TextView textView = (TextView) itemView.findViewById(R.id.reciverSportTv);
            textView.setText(name);

        }

        public void setClick (String eventId){

        }
    }
}
