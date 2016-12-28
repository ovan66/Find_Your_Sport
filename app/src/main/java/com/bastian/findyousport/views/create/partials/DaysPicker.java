package com.bastian.findyousport.views.create.partials;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.bastian.findyousport.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cutiko on 28-12-16.
 */

public class DaysPicker {

    private LinearLayout linearLayout;
    private PartialCallback callback;

    public DaysPicker(PartialCallback callback) {
        this.callback = callback;
    }

    public HorizontalScrollView getView(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        HorizontalScrollView view = (HorizontalScrollView) inflater.inflate(R.layout.days_layout, null);
        linearLayout = (LinearLayout) view.getChildAt(0);
        return view;
    }

    public void setValidation(){
        final List<CheckBox> checkBoxes = new ArrayList<>();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) linearLayout.getChildAt(i);
            checkBoxes.add(checkBox);
        }
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        callback.stepperSucces();
                    } else {
                        int validation = 0;
                        for (CheckBox cb : checkBoxes) {
                            if (cb.isChecked()) {
                                validation++;
                            }
                        }
                        if (validation == 0) {
                            callback.stepperError("Campo requerido");
                        }
                    }
                }
            });
        }
    }

    public List<String> getAnswer(){
        List<String> answers = new ArrayList<>();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) linearLayout.getChildAt(i);
            if (checkBox.isChecked()){
                answers.add(checkBox.getText().toString());
            }
        }
        return answers;
    }

}
