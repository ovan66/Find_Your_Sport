package com.bastian.findyousport.views.create;

import android.app.DatePickerDialog;
import android.content.Context;
import android.provider.CalendarContract;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bastian.findyousport.R;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class SteperActivity extends AppCompatActivity implements VerticalStepperForm {

    private EditText institutionName, sportName, price, schedules, location, phoneNum, email, facebook;
    private VerticalStepperFormLayout verticalStepperForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steper);

        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.createForm);

        String[] mySteps = {"Nombre de la Institución", "Nombre del Deporte", "Precio", "Horarios", "Ubicacion", "Datos de contacto" };
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();



    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                institutionName = new EditText(this);
                institutionName.setHint("Nombre de la institucion");
                view = institutionName;
                break;

            case 1:
                sportName = new EditText(this);
                sportName.setHint("Nombre del deporte");
                view = sportName;
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

            case 7:
                break;
        }
        return view;
    }

    private View input(String hint){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.partial_steper_field, null, false);
        TextInputLayout textInputLayout = (TextInputLayout) view.findViewById(R.id.hintLayout);
        textInputLayout.setHint(hint);
        return view;
    }


    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case 0:
                verticalStepperForm.setActiveStepAsCompleted();
                /*if(institutionName.length() >= 3) {
                    verticalStepperForm.setActiveStepAsCompleted();
                } else {
                    String errorMessage = "El nombre debe ser más largo";
                    verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
                }*/
                break;
            case 1:
                verticalStepperForm.setActiveStepAsCompleted();
                break;
        }
    }

    @Override
    public void sendData() {
        Toast.makeText(this, "SEND DATA", Toast.LENGTH_SHORT).show();
    }
}
