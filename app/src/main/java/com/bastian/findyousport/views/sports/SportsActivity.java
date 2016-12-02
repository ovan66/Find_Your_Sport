package com.bastian.findyousport.views.sports;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bastian.findyousport.R;
import com.bastian.findyousport.views.main.categoryList.CategoriesFragment;

public class SportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        String category = getIntent().getStringExtra(CategoriesFragment.CATEGORY).toUpperCase();
        getSupportActionBar().setTitle(category);

    }
}
