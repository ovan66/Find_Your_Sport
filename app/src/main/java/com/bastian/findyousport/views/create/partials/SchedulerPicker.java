package com.bastian.findyousport.views.create.partials;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.bastian.findyousport.R;

/**
 * Created by cutiko on 28-12-16.
 */

public class SchedulerPicker extends Button implements TimePickerDialog.OnTimeSetListener {

    private PartialCallback callback;

    public SchedulerPicker(Context context) {
        super(context);
        callback = (PartialCallback) context;
        setAppearance();
        setClick();
    }

    private void setClick() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(getContext(), SchedulerPicker.this, 0, 0, true);
                dialog.show();
            }
        });
    }

    private void setAppearance() {
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        setTextColor(ContextCompat.getColor(getContext(), android.R.color.white));
        getBackground().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        setText(String.valueOf(i)+":"+String.valueOf(i1));
        callback.stepperSucces();
    }
}
