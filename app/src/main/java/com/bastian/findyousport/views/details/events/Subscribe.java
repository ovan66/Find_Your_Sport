package com.bastian.findyousport.views.details.events;

import android.util.Log;

import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.models.Subscription;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cutiko on 19-01-17.
 */

public class Subscribe {

    private SubscriptionCallback callback;
    private Event event;

    public Subscribe(SubscriptionCallback callback, Event event) {
        this.callback = callback;
        this.event = event;
    }

    public void validation(String uid) {
        DatabaseReference reference = new FirebaseRef().eventSuscription(uid, event.getKey());
        reference.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                UserData user = new UserData();
                Subscription subscription = new Subscription(user.uid(), user.email());
                Event liveEvent = mutableData.getValue(Event.class);
                if (liveEvent != null) {
                    List<Subscription> subscriptions = liveEvent.getSubscribers();
                    if (subscriptions != null && subscriptions.size() > 0) {
                        Log.d("SUBSCRIPTIONS", "not null " + subscriptions.size());
                        //TODO iterate over the susbscription list to make sure user is not subscribing again
                        if (liveEvent.getVacants() > subscriptions.size()) {
                            Log.d("SUBSCRIPTIONS", "vacants availables");
                            List<Subscription> subscriptionList = subscriptions;
                            subscriptionList.add(subscription);
                            mutableData.child("subscribers").setValue(subscriptionList);
                            Log.d("SUBSCRIPTIONS", "susbscriptios size = " + subscriptions.size());
                        } else {
                            Log.d("SUBSCRIPTIONS", "bad luck");
                        }
                    } else {
                        Log.d("SUBSCRIPTIONS", "list null ");
                        List<Subscription> subscriptionList = new ArrayList<>();
                        subscriptionList.add(subscription);
                        mutableData.child("subscribers").setValue(subscriptionList);
                    }
                } else {

                }
                return Transaction.success(mutableData);
                /*List<Subscription> subscriptions = mutableData.getValue(List.class);
                Log.d("SUBSCRIPTION", subscriptions.toString());
                UserData user = new UserData();
                Subscription subscription = new Subscription(user.uid(), user.email());
                if (subscriptions != null) {
                    if (event.getVacants() < subscriptions.size()) {
                        subscriptions.add(subscription);
                        mutableData.setValue(subscriptions);
                        callback.success();
                        return Transaction.success(mutableData);
                    } else {
                        callback.badLuck();
                        return Transaction.success(mutableData);
                    }
                } else {
                    List<Subscription> subscriptionList = new ArrayList<>();
                    subscriptionList.add(subscription);
                    mutableData.setValue(subscriptionList);
                    callback.success();
                    return Transaction.success(mutableData);
                }*/

            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                Log.d("onComplete", dataSnapshot.getKey());
            }
        });
    }
}
