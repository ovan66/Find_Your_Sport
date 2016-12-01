package com.bastian.findyousport.views.details;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.bastian.findyousport.R;
import com.bastian.findyousport.views.Constants;

import static com.bastian.findyousport.R.id.fab;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String nameLocal = getIntent().getStringExtra(Constants.NAME_LOCAL);
        String nameSport = getIntent().getStringExtra(Constants.SPORT_NAME);
        String price = getIntent().getStringExtra(Constants.PRICE);
        String schedules = getIntent().getStringExtra(Constants.SCHEDULES);
        String location = getIntent().getStringExtra(Constants.LOCATION);
        String phoneNum = getIntent().getStringExtra(Constants.PHONE_NUM);
        String facebook = getIntent().getStringExtra(Constants.FACEBOOK);
        String email= getIntent().getStringExtra(Constants.EMAIL);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
