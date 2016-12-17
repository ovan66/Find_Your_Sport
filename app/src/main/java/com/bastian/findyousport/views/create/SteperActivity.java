package com.bastian.findyousport.views.create;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bastian.findyousport.R;
import com.bastian.findyousport.data.FirebaseRef;
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.models.Event;
import com.bastian.findyousport.views.create.partials.InputEmail;
import com.bastian.findyousport.views.create.partials.InputNumber;
import com.bastian.findyousport.views.create.partials.InputText;
import com.bastian.findyousport.views.create.partials.PartialCallback;
import com.bastian.findyousport.views.create.partials.SpinnerCategories;
import com.bastian.findyousport.views.create.partials.VacantsPiker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.shawnlin.numberpicker.NumberPicker;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

import static android.R.attr.phoneNumber;

public class SteperActivity extends AppCompatActivity implements VerticalStepperForm, PartialCallback {

    private EditText  schedules, location, facebook;
    private SpinnerCategories spinnerCategories;
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

        String[] mySteps = {"Nombre de la Institución", "Nombre del Deporte", "Categoria", "Precio", "Horarios",
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
                spinnerCategories = new SpinnerCategories(this);
                spinnerCategories.setValidator();
                view = spinnerCategories;
                break;
            case 3:
                price = new InputNumber(this);
                price.setHint("Precios");
                view = price;
                break;

            case 4:
                schedules = new EditText(this);
                schedules.setHint("Horarios");
                view = schedules;
                break;

            case 5:
                location = new EditText(this);
                location.setHint("Ubicacion");
                view = location;
                break;

            case 6:
                VacantsPiker vacantWrapper = new VacantsPiker(this);
                vacantsPicker = vacantWrapper.getView(this);
                vacantWrapper.setListener();
                view = vacantsPicker;
                break;

            case 7:
                phoneNum = new InputNumber(this);
                phoneNum.setHint("Numero de telefono");
                phoneNum.setValidator();
                view = phoneNum;
                break;

            case 8:
                email = new InputEmail(this);
                email.setHint("Email");
                email.setValidator();
                view = email;
                break;

            case 9:
                facebook = new EditText(this);
                facebook.setHint("Facebook");
                view = facebook;
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
                verticalStepperForm.setActiveStepAsCompleted();
                break;

            case 4:
                verticalStepperForm.setActiveStepAsCompleted();
                break;

            case 5:
                verticalStepperForm.setActiveStepAsCompleted();
                break;

            case 6:
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 7:
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 8:
                verticalStepperForm.setActiveStepAsUncompleted("Campo requerido");
                break;

            case 9:
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
        String institution = institutionName.getText().toString();
        String sport = sportName.getText().toString();
        int priceSport = Integer.parseInt(price.getText().toString());
        String category = spinnerCategories.getSelectedItem().toString();
        String schedulesClass = schedules.getText().toString();
        String locationinstitution = location.getText().toString();
        int vacants = vacantsPicker.getValue();
        int phoneNumber = Integer.parseInt(phoneNum.getText().toString());
        String facebookInstitution = facebook.getText().toString();
        String emailInstitution = email.getText().toString();


        DatabaseReference databaseReference = new FirebaseRef().events(category);
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
