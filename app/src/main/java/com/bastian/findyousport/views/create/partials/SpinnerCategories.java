package com.bastian.findyousport.views.create.partials;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.bastian.findyousport.R;

/**
 * Created by santo_000 on 14-12-2016.
 */

public class SpinnerCategories extends Spinner{

    protected PartialCallback callback;

    public SpinnerCategories(Context context) {
        super(context);
        this.callback = (PartialCallback) context;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        String[] categoryArray = getResources().getStringArray(R.array.categories_array_spinner);
        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, categoryArray);
        setAdapter(adapter);
    }

    public void setValidator(){
        setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    callback.stepperError("Su deporte debe pertenecer a una categoria");
                } else {
                    callback.stepperSucces();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
