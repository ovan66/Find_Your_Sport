package com.bastian.findyousport.views.main.categoryList;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bastian.findyousport.R;
import com.bastian.findyousport.adapters.CategoriesAdapter;
import com.bastian.findyousport.adapters.CategoriesClickListener;
import com.bastian.findyousport.views.profiles.ProfilesActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment implements CategoriesClickListener {

    public static final String CATEGORY = "com.bastian.findyousport.views.main.categoryList.CATEGORY";


    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recycler = (RecyclerView) view;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(gridLayoutManager);

        CategoriesAdapter adapter = new CategoriesAdapter(this, getActivity().getResources().getStringArray(R.array.categories_array));
        recycler.setAdapter(adapter);

    }

    @Override
    public void click(String category) {
        Intent intent = new Intent(getActivity(), ProfilesActivity.class);
        intent.putExtra(CATEGORY, category);
        startActivity(intent);
    }
}
