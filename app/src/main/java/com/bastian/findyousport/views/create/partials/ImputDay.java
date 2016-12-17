package com.bastian.findyousport.views.create.partials;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bastian.findyousport.R;
import com.shawnlin.numberpicker.NumberPicker;

/**
 * Created by santo_000 on 16-12-2016.
 */

public class ImputDay {

    private CheckBox checkBox;
    private Button startTime, EndTime;
    private PartialCallback callback;

    public ImputDay (PartialCallback callback) {
        this.callback = callback;
    }

    public CheckBox getView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        checkBox = (CheckBox) inflater.inflate(R.layout.schedules_layout, null, false);
        return checkBox;
    }

    public void setListener() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox != null ){
                    callback.stepperError("Debes elegir al menos un dia");
                }else{
                    callback.stepperSucces();
                }
            }
        });
    }

}