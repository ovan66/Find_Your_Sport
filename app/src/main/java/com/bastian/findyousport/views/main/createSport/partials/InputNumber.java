package com.bastian.findyousport.views.main.createSport.partials;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;

/**
 * Created by santo_000 on 05-12-2016.
 */

public class InputNumber extends InputText {

    public InputNumber(Context context) {
        super(context);
        setAsNumber();
    }

    private void setAsNumber() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
    }

}
