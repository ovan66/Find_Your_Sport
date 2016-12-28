package com.bastian.findyousport.views.sports.sportList;


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

public class SportListFragment extends Fragment {

    public static final String EVENT = "com.bastian.findyousport.views.sports.sportList.EVENT";

    public SportListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sport_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = (RecyclerView) view;

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));




        //DatabaseReference reference = new FirebaseRef().categoryProfile(category,null);

       /* FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Profile, ProfileHolder>
                (Event.class, R.layout.list_item_profile, ProfileHolder.class, reference) {
            @Override
            protected void populateViewHolder(ProfileHolder viewHolder, final Profile model, int position) {


                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra(EVENT, model);
                        startActivity(intent);
            }

            @Override
            protected void populateViewHolder(ProfileHolder viewHolder, final Event model, int position) {
                Log.d("ADAPTER", model.getEmail());
                viewHolder.setName(model.getSportName());


                    }
                });
            }
        };

        recycler.setAdapter(adapter);*/
    }

    public static class ProfileHolder extends RecyclerView.ViewHolder {

        public ProfileHolder(View itemView) {
            super(itemView);
        }

        public void setName(String name){
            TextView textView = (TextView) itemView.findViewById(R.id.nameSportCardTv);
            textView.setText(name);
        }
    }
}
