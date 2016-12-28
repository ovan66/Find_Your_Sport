package com.bastian.findyousport.views.create;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.views.create.partials.InputNumber;
import com.bastian.findyousport.views.create.partials.InputText;
import com.bastian.findyousport.views.create.partials.PartialCallback;
import com.shawnlin.numberpicker.NumberPicker;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class SteperActivity extends AppCompatActivity implements VerticalStepperForm, PartialCallback {

    private InputText name;
    private NumberPicker vacantsPicker, start, end;
    private InputNumber price;
    private VerticalStepperFormLayout verticalStepperForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steper);

        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.createForm);

        String[] mySteps = {"Nombre de la clase", "Hora de inicio", "Hora de termino", "Cupos", "Precio"};
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
                name = new InputText(this);
                name.setHint("Nombre de la Clase");
                name.setValidator();
                view = name;
                break;

            case 1:
                start = new NumberPicker(this);
                view = start;
                break;

            case 2:
                end = new NumberPicker(this);
                view = end;
                break;

            case 3:
                vacantsPicker = new NumberPicker(this);
                view = vacantsPicker;
                break;

            case 4:
                price = new InputNumber(this);
                price.setHint("Precios");
                view = price;
                break;

            case 5:
                price = new InputNumber(this);
                price.setHint("Precio");
                view = price;
                break;
        }
        return view;
    }



    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case 0:
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 1:
                verticalStepperForm.setActiveStepAsCompleted();
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
                verticalStepperForm.setActiveStepAsCompleted();
                break;



        }
    }

    @Override
    public void sendData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setCancelable(false);

        String uid = new UserData().uid();
        String institution = name.getText().toString();
        int startScheudle = start.getValue();
        int endScheudle = end.getValue();
        int vacants = vacantsPicker.getValue();
        int priceSport = Integer.parseInt(price.getText().toString());


        /*DatabaseReference databaseReference = new FirebaseRef().events(category);
        //TODO initiallizae the model correctly
        String key = databaseReference.push().getKey();
        Event event = new Event(
                phoneNumber,
                priceSport,
                vacants,
                category,
                key,
                facebookInstitution,
                emailInstitution,
                locationinstitution,
                schedulesClass,
                sport,
                institution,
                uid);
        databaseReference.child(key).setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(SteperActivity.this, "Su clase ha sido publicada", Toast.LENGTH_SHORT).show();

            }
        });*/
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
