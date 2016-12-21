package com.bastian.findyousport.views.profile;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bastian.findyousport.R;
import com.bastian.findyousport.views.main.MainActivity;
import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.Objects.MagicalCameraObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePhotosFragment extends Fragment {

    private int RESIZE_PHOTO_PIXELS_PERCENTAGE = 200;
    private MagicalCamera magicalCamera;
    private ImageButton imageButton;


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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //CALL THIS METHOD EVER
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        //this is for rotate picture in this method
        //magicalCamera.resultPhoto(requestCode, resultCode, data, MagicalCamera.ORIENTATION_ROTATE_180);

        //with this form you obtain the bitmap (in this example set this bitmap in image view)
        imageButton.setImageBitmap(magicalCamera.getPhoto());

        //if you need save your bitmap in device use this method and return the path if you need this
        //You need to send, the bitmap picture, the photo name, the directory name, the picture type, and autoincrement photo name if           //you need this send true, else you have the posibility or realize your standard name for your pictures.
        String path = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(),"myPhotoName","myDirectoryName", MagicalCamera.JPEG, true);

        if(path != null){
            Toast.makeText(getContext(), "The photo is save in device, please check this path: " + path, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getContext(), "Sorry your photo dont write in devide, please contact with fabian7593@gmail and say this error", Toast.LENGTH_SHORT).show();
        }


    }
}
