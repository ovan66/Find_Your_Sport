package com.bastian.findyousport.views.create.partials;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by santo_000 on 05-12-2016.
 */

public class InputText extends EditText {

    protected PartialCallback callback;

    public InputText(Context context) {
        super(context);
        this.callback = (PartialCallback) context;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);

    }

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
                int size = s.length();
                if (size >= 3 && size < 40) {
                    callback.stepperSucces();
                } else {
                    if (size < 3) {
                        callback.stepperError("Nombre muy corto");
                    } else {
                        callback.stepperError("Nombre muy largo");
                    }
                }
            }
        });
    }
}
