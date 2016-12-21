package com.bastian.findyousport.views.profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bastian.findyousport.R;
import com.bastian.findyousport.adapters.CategoriesDdAdapter;
import com.bastian.findyousport.data.FirebaseRef;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class CreateProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        //TODO exist 3 views, we need to apply each view depending of the tipe of users or is actions

        Button publishBtn = (Button) findViewById(R.id.createProfileBtn);
        final Spinner categoriesSpinner = (Spinner)findViewById(R.id.categoriesDd);
        String[] categoriesList = getResources().getStringArray(R.array.categories_array_spinner);
        CategoriesDdAdapter categoriesDdAdapter = new CategoriesDdAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, categoriesList);
        categoriesSpinner.setAdapter(categoriesDdAdapter);

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO Missing add the option to upload images (magic camera)
                String nameLocal = ((EditText)findViewById(R.id.institutionEt)).getText().toString();
                String location = ((EditText)findViewById(R.id.locationEt)).getText().toString();
                String phoneNum = ((EditText)findViewById(R.id.phoneNumEt)).getText().toString();
                String email = ((EditText)findViewById(R.id.emailEt)).getText().toString();
                String facebook = ((EditText)findViewById(R.id.facebookEt)).getText().toString();
                String descripcion = ((EditText)findViewById(R.id.descriptionEt)).getText().toString();

                String category = categoriesSpinner.getSelectedItem().toString().toLowerCase();
                DatabaseReference reference = new FirebaseRef().profiles();

                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String key = reference.push().getKey();


                //Profile ownerUser = new Profile(uid, nameLocal, location, email, facebook, key, category, descripcion, phoneNum);
                //reference.child(key).setValue(ownerUser);


                //TODO we need to show the classes that the current ownerUser have created so far
            }
        });
    }
}