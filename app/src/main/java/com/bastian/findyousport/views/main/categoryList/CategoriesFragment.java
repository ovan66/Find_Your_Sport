package com.bastian.findyousport.views.main.categoryList;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bastian.findyousport.R;
import com.bastian.findyousport.adapters.CategoriesAdapter;
import com.bastian.findyousport.adapters.CategoriesClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment implements CategoriesClickListener {


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

        Toast.makeText(getContext(), category, Toast.LENGTH_SHORT).show();

    }
}
