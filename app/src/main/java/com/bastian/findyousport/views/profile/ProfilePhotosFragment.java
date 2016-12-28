package com.bastian.findyousport.views.profile;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bastian.findyousport.R;
import com.bastian.findyousport.adapters.ProfilePhotoAdapter;
import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.Objects.MagicalCameraObject;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePhotosFragment extends Fragment {

    private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 100;
    private MagicalCamera magicalCamera;
    private ImageButton imageButton;
    private ProfilePhotoAdapter adapter;


    public ProfilePhotosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_photos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PermissionGranted permissionGranted = new PermissionGranted(getActivity());
        magicalCamera = new MagicalCamera(getActivity() ,RESIZE_PHOTO_PIXELS_PERCENTAGE, permissionGranted);
        imageButton = (ImageButton) view.findViewById(R.id.updatePhoto);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(magicalCamera.selectedFragmentPicture()){
                    startActivityForResult(Intent.createChooser(magicalCamera.getIntentFragment(),  "FindYourSport Foto"),
                            MagicalCameraObject.SELECT_PHOTO);
                }

            }
        });

        adapter = new ProfilePhotoAdapter(new ArrayList<String>());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.photosRv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            magicalCamera.resultPhoto(requestCode, resultCode, data);
            Bitmap bitmap = magicalCamera.getPhoto();
            String photoName = "yoursport_" + System.currentTimeMillis();
            String path = magicalCamera.savePhotoInMemoryDevice(bitmap, photoName, "findyoursport", MagicalCamera.JPEG, false);
            adapter.add(path);
        }
        System.gc();
    }

    protected List<String> getPhotos() {
        return adapter.getPaths();
    }
}
