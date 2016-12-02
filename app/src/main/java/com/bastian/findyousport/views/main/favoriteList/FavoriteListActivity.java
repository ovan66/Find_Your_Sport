package com.bastian.findyousport.views.main.favoriteList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.views.main.sportList.SportListFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

public class FavoriteListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_favorite_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference userData = new FirebaseRef().favorites();
        FirebaseRecyclerAdapter adapter = new FirebaseRecyclerAdapter<Event, FavoriteHolder>(Event.class, R.layout.list_item_post, FavoriteHolder.class, userData) {


            @Override
            protected void populateViewHolder(FavoriteHolder viewHolder, final Event model, int position) {
                viewHolder.setName(model.getNameLocal());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference reference = new FirebaseRef().favorites();
                        reference.child(model.getKey()).removeValue();

                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);


    }

    public static class FavoriteHolder extends RecyclerView.ViewHolder {
        public FavoriteHolder(View itemView) {
            super(itemView);
        }

        public void setName(String name) {
            TextView textView = (TextView) itemView.findViewById(R.id.reciverSportTv);
            textView.setText(name);
        }
    }
}