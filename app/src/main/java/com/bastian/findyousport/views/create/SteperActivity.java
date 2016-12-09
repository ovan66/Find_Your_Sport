package com.bastian.findyousport.views.create;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bastian.findyousport.R;
import com.bastian.findyousport.views.create.partials.InputEmail;
import com.bastian.findyousport.views.create.partials.InputNumber;
import com.bastian.findyousport.views.create.partials.InputText;
import com.bastian.findyousport.views.create.partials.InputTextCallback;
import com.bastian.findyousport.views.create.partials.VacantsPiker;
import com.shawnlin.numberpicker.NumberPicker;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class SteperActivity extends AppCompatActivity implements VerticalStepperForm, InputTextCallback {

    private EditText  schedules, location, facebook;
    private InputText institutionName, sportName;
    private NumberPicker vacantsPicker;
    private InputNumber price, phoneNum;
    private InputEmail email;
    private VerticalStepperFormLayout verticalStepperForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steper);

        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.createForm);

        String[] mySteps = {"Nombre de la Institución", "Nombre del Deporte", "Precio", "Horarios",
                "Ubicacion", "Cupos", "Numero de contacto", "email", "facebook"};
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
                institutionName = new InputText(this);
                institutionName.setHint("Nombre de la institucion");
                institutionName.setValidator();
                view = institutionName;
                break;

            case 1:
                sportName = new InputText(this);
                sportName.setHint("Nombre del deporte");
                sportName.setValidator();
                view = sportName;
                break;

            case 2:
                price = new InputNumber(this);
                price.setHint("Precios");
                view = price;
                break;

            case 3:
                schedules = new EditText(this);
                schedules.setHint("Horarios");
                view = schedules;
                break;

            case 4:
                location = new EditText(this);
                location.setHint("Ubicacion");
                view = location;
                break;

            case 5:
                VacantsPiker vacantWrapper = new VacantsPiker(this);
                vacantsPicker = vacantWrapper.getView(this);
                vacantWrapper.setListener();
                view = vacantsPicker;
                break;

            case 6:
                phoneNum = new InputNumber(this);
                phoneNum.setHint("Numero de telefono");
                phoneNum.setValidator();
                view = phoneNum;
                break;

            case 7:
                email = new InputEmail(this);
                email.setHint("Email");
                email.setValidator();
                view = email;
                break;

            case 8:
                facebook = new EditText(this);
                facebook.setHint("Facebook");
                view = facebook;
                break;
        }
        return view;
    }

    private View input(String hint) {
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
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 1:
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 2:
                verticalStepperForm.setActiveStepAsCompleted();
                break;

            case 3:
                verticalStepperForm.setActiveStepAsCompleted();
                break;

            case 4:
                verticalStepperForm.setActiveStepAsCompleted();
                break;

            case 5:
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 6:
                verticalStepperForm.setActiveStepAsCompleted();
                break;

            case 7:
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 8:
                verticalStepperForm.setActiveStepAsCompleted();
                break;

        }
    }

    @Override
    public void sendData() {
        Toast.makeText(this, "SEND DATA", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void stepperError(String error) {
        verticalStepperForm.setActiveStepAsUncompleted(error);
    }

    @Override
    public void stepperSucces() {
        verticalStepperForm.setActiveStepAsCompleted();
    }
}
