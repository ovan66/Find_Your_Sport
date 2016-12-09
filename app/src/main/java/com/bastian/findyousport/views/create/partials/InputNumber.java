package com.bastian.findyousport.views.create.partials;

import android.content.Context;
import android.text.InputType;

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
