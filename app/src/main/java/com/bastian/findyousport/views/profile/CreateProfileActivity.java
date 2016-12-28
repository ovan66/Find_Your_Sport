package com.bastian.findyousport.views.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bastian.findyousport.R;
import com.bastian.findyousport.adapters.CategoriesDdAdapter;
import com.bastian.findyousport.data.UserData;
import com.bastian.findyousport.models.Profile;
import com.bastian.findyousport.views.create.SteperActivity;

public class CreateProfileActivity extends AppCompatActivity implements CreateProfileCallback {

    private EditText institutionEt, locationEt, phoneNumEt, emailEt, facebookEt, descriptionEt;
    private Spinner categoriesSpinner;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        Button publishBtn = (Button) findViewById(R.id.createProfileBtn);
        categoriesSpinner = (Spinner) findViewById(R.id.categoriesDd);
        String[] categoriesList = getResources().getStringArray(R.array.categories_array_spinner);
        CategoriesDdAdapter categoriesDdAdapter = new CategoriesDdAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, categoriesList);
        categoriesSpinner.setAdapter(categoriesDdAdapter);

        institutionEt = (EditText) findViewById(R.id.institutionEt);
        locationEt = (EditText) findViewById(R.id.locationEt);
        phoneNumEt = (EditText) findViewById(R.id.phoneNumEt);
        emailEt = (EditText) findViewById(R.id.emailEt);
        facebookEt = (EditText) findViewById(R.id.facebookEt);
        descriptionEt = (EditText) findViewById(R.id.descriptionEt);


        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }

        });
    }

    private void validation(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();
        int validation = 0;
        resetErrors();

        String category = categoriesSpinner.getSelectedItem().toString();
        if (category.equals(getResources().getString(R.string.select_sport))) {
            validation++;
            Toast.makeText(CreateProfileActivity.this, "Se requiere categoria", Toast.LENGTH_SHORT).show();
        }

        String institution = institutionEt.getText().toString();
        if (institution.trim().length() == 0) {
            validation++;
            error(institutionEt);
        }

        String location = locationEt.getText().toString();
        if (location.trim().length() == 0) {
            validation++;
            error(locationEt);
        }


        String phoneNum = phoneNumEt.getText().toString();
        if (phoneNum.equals("")) {
            validation++;
            error(phoneNumEt);
        }

        String email = emailEt.getText().toString();
        if (email.trim().length() == 0) {
            validation++;
            error(emailEt);
        }

        String facebook = facebookEt.getText().toString();
        String description = descriptionEt.getText().toString();

        if (validation == 0) {
            ProfilePhotosFragment fragment = (ProfilePhotosFragment) getSupportFragmentManager().findFragmentById(R.id.profilePhotosFragment);
            Profile profile = new Profile(new UserData().uid(), institution, location, email, facebook, category, description, phoneNum, fragment.getPhotos());
            new CreateProfile(this, profile).init();
        }
    }

    private void error(EditText editText){
        editText.setError("Campo requerido");
    }

    private void resetErrors(){
        institutionEt.setError(null);
        locationEt.setError(null);
        phoneNumEt.setError(null);
        emailEt.setError(null);
    }

    @Override
    public void done() {
        progressDialog.dismiss();
        progressDialog = null;
        startActivity(new Intent(this, SteperActivity.class));
    }
}
