package com.bastian.findyousport.views.main.CreateSport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateSportActivity extends AppCompatActivity {

    private UserData userData = new UserData();
    private User oterUser = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sport);

        Button publishBtn = (Button) findViewById(R.id.publishBtn);

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameLocal = ((EditText)findViewById(R.id.nameLocalEt)).getText().toString();
                String sportName  = ((EditText)findViewById(R.id.nameSportEt)).getText().toString();
                String price = ((EditText)findViewById(R.id.priceEt)).getText().toString();
                String schedules = ((EditText)findViewById(R.id.schedulesEt)).getText().toString();
                String location = ((EditText)findViewById(R.id.locationEt)).getText().toString();
                String phoneNum = ((EditText)findViewById(R.id.phoneNumEt)).getText().toString();
                String email = ((EditText)findViewById(R.id.emailEt)).getText().toString();
                String facebook= ((EditText)findViewById(R.id.nameLocalEt)).getText().toString();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("events");
                new FirebaseRef().events();

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Event event = new Event(uid, nameLocal, sportName, price, schedules, location, phoneNum, email, facebook);
                reference.push().setValue(event);





                //TODO the events owned by the user are set for the next iteration
                //DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference().child("events");
                //Query keyRf = dataRef.orderByChild("uid").equalTo(uid);
            }
        });
    }
}
