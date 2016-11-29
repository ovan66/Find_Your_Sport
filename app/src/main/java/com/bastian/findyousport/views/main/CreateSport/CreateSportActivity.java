package com.bastian.findyousport.views.main.CreateSport;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.bastian.findyousport.R;

public class CreateSportActivity extends AppCompatActivity {

    private String nameLocal, sportName, price, schedules, location, phoneNum, email, facebook;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sport);
        Button publishBtn = (Button) findViewById(R.id.publishBtn);

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameLocal = String.valueOf(findViewById(R.id.nameLocalEt).toString());
                String sportName = String.valueOf(findViewById(R.id.nameSportEt).toString());
                String price = String.valueOf(findViewById(R.id.priceEt).toString());
                String schedules = String.valueOf(findViewById(R.id.schedulesEt).toString());
                String location = String.valueOf(findViewById(R.id.locationEt).toString());
                String phoneNum = String.valueOf(findViewById(R.id.phoneNumEt).toString());
                String email = String.valueOf(findViewById(R.id.email).toString());
                String facebook = String.valueOf(findViewById(R.id.facebookEt).toString());
                
                //TODO enviar la data a firebase
            }
        });
    }
}
