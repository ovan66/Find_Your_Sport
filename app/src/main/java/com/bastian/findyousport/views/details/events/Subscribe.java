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

import static android.util.Log.d;

/**
 * Created by cutiko on 19-01-17.
 */

public class Subscribe {

    private SubscriptionCallback callback;
    private Event event;
    private int result = 0;
    private static final int SUCCESS = 1;
    private static final int FAIL = 2;
    private static final int BAD_LUCK = 3;
    private static final int CHEATER = 4;


    public Subscribe(SubscriptionCallback callback, Event event) {
        this.callback = callback;
        this.event = event;
    }

    public void validation(final String uid) {
        DatabaseReference reference = new FirebaseRef().eventSuscription(uid, event.getKey());
        reference.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                UserData user = new UserData();
                String userUid = user.uid();
                Subscription subscription = new Subscription(userUid, user.email());
                Event liveEvent = mutableData.getValue(Event.class);
                if (liveEvent != null) {
                    List<Subscription> subscriptions = liveEvent.getSubscribers();
                    if (subscriptions != null && subscriptions.size() > 0) {
                        //TODO iterate over the susbscription list to make sure user is not subscribing again
                        if (liveEvent.getVacants() > subscriptions.size()) {
                            boolean validation = true;
                            for (Subscription sub : subscriptions) {
                                if (userUid == sub.getUid()) {
                                    validation = false;
                                }
                            }
                            if (validation) {
                                List<Subscription> subscriptionList = subscriptions;
                                subscriptionList.add(subscription);
                                result = SUCCESS;
                                mutableData.child("subscribers").setValue(subscriptionList);
                            } else {
                                result = CHEATER;
                            }
                        } else {
                            result = BAD_LUCK;
                        }
                    } else {
                        List<Subscription> subscriptionList = new ArrayList<>();
                        subscriptionList.add(subscription);
                        mutableData.child("subscribers").setValue(subscriptionList);
                        result = SUCCESS;
                    }
                } else {
                    result = FAIL;
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                switch (result) {
                    case 0:
                        //NOHTHING HAPPENED
                        //HANDLE THIS CASE?
                        break;

                    case SUCCESS:
                        subscribeCreation(dataSnapshot);
                        callback.success();
                        break;

                    case FAIL:
                        callback.badLuck();
                        break;

                    case BAD_LUCK:
                        callback.badLuck();
                        break;

                    case CHEATER:
                        callback.badLuck();
                        break;

                    default:
                        break;
                }
                d("onComplete", dataSnapshot.getKey());
            }
        });
    }

    public void subscribeCreation(DataSnapshot dataSnapshot) {
        Event event = dataSnapshot.getValue(Event.class);
        event.setSubscribers(null);
        DatabaseReference reference = new FirebaseRef().userSubsriptions(event.getKey());
        reference.child(event.getKey()).setValue(event);
    }

}
