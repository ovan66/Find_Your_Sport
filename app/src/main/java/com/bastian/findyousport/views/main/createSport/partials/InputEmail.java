package com.bastian.findyousport.views.main.createSport.partials;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by santo_000 on 05-12-2016.
 */

public class InputEmail extends InputText {
    public InputEmail(Context context) {
        super(context);
    }

    @Override
    public void setValidator() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 0
                        &&  !s.toString().contains(" ")
                        &&  !s.toString().contains(".")
                        &&  !s.toString().contains("@")) {
                    callback.stepperSucces();
                } else {
                        callback.stepperError("Nombre muy corto");
                    }
                }
            }
        );
    }
}
