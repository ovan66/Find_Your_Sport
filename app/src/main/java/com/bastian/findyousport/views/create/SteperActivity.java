package com.bastian.findyousport.views.create;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.views.create.partials.DaysPicker;
import com.bastian.findyousport.views.create.partials.InputNumber;
import com.bastian.findyousport.views.create.partials.InputText;
import com.bastian.findyousport.views.create.partials.PartialCallback;
import com.bastian.findyousport.views.create.partials.SchedulerPicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.shawnlin.numberpicker.NumberPicker;

import java.util.List;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class SteperActivity extends AppCompatActivity implements VerticalStepperForm, PartialCallback {


    private DaysPicker daysPicker;
    private InputText name;
    private NumberPicker vacantsPicker;
    private SchedulerPicker start, end;
    private InputNumber price;
    private VerticalStepperFormLayout verticalStepperForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steper);

        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.createForm);

        String[] mySteps = {"Nombre de la clase","Selecciona los dias", "Hora de inicio", "Hora de termino", "Cupos", "Precio"};
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
                daysPicker = new DaysPicker(this);
                view = daysPicker.getView(this);
                daysPicker.setValidation();
                break;

            case 2:
                start = new SchedulerPicker(this);
                start.setText("Selecciona la hora de inicio");
                view = start;
                break;

            case 3:
                end = new SchedulerPicker(this);
                end.setText("Selecciona la hora de termino");
                view = end;
                break;

            case 4:
                vacantsPicker = new NumberPicker(this);
                view = vacantsPicker;
                break;

            case 5:
                price = new InputNumber(this);
                price.setHint("Precios");
                view = price;
                break;

            case 6:
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
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 2:
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 3:
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 4:
                verticalStepperForm.setActiveStepAsCompleted();
                break;

            case 5:
                verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 6:
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
        List<String> days = daysPicker.getAnswer();
        String startScheudle = start.getText().toString();
        String endScheudle = end.getText().toString();
        int vacants = vacantsPicker.getValue();
        int priceSport = Integer.parseInt(price.getText().toString());


        DatabaseReference databaseReference = new FirebaseRef().events();

        String key = databaseReference.push().getKey();
        Event event = new Event(uid, institution, startScheudle, endScheudle,key,priceSport,vacants, days);
        databaseReference.child(key).setValue(event).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(SteperActivity.this, "Su clase ha sido publicada", Toast.LENGTH_SHORT).show();
            }
        });
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
