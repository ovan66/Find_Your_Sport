package com.bastian.findyousport.views.create.partials;

import android.content.Context;
import android.view.LayoutInflater;

import com.bastian.findyousport.R;
import com.shawnlin.numberpicker.NumberPicker;

/**
 * Created by santo_000 on 07-12-2016.
 */

public class VacantsPiker {

    private NumberPicker numberPicker;
    private PartialCallback callback;

    public VacantsPiker(PartialCallback callback) {
        this.callback = callback;
    }

    public NumberPicker getView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        numberPicker = (NumberPicker) inflater.inflate(R.layout.partial_vacants, null, false);
        return numberPicker;
    }

    public void setListener() {
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if (newVal == 0) {
                    callback.stepperError("Debe tener cupos");
                } else {
                    callback.stepperSucces();
                }
            }
        });
    }

}
