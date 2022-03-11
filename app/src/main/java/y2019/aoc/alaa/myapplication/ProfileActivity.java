package y2019.aoc.alaa.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    //request for camera for a activity result
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;

    //attributes
    private Button buttonCamera, buttonGallery;
    private ImageView imageViewProfile;
//    private String pic;
    //for picture of camera, A bitmap is a type of memory organization or image file format used to store digital images.
    //The term bitmap comes from the computer programming terminology, meaning just a map of bits,
    //a spatially mapped array of bits.
    private Bitmap picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //gets reference for the design components
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(this);

        buttonGallery = findViewById(R.id.buttonGallery);
        buttonGallery.setOnClickListener(this);

        imageViewProfile =  findViewById(R.id.imageViewProfile);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.buttonCamera){
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,CAMERA_REQUEST); // this startActivity takes the intent and the camera_request.
        }else{
            Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // chooses a photo from gallery.
            startActivityForResult(i,GALLERY_REQUEST);  // this startActivity takes the intent and the gallery_request.
        }
    }

//    public void bitMapToString(Bitmap bitmap){
//        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
//        byte [] arr=baos.toByteArray();
//        pic= Base64.encodeToString(arr, Base64.DEFAULT);
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if the request was from camera and the result was OK meaning the camera worked
        if(requestCode==CAMERA_REQUEST){
            if(resultCode == RESULT_OK){
                //the image captured is saved in the data object
                picture = (Bitmap) data.getExtras().get("data");
                //set image captured to be the new image
//                bitMapToString(picture);
                imageViewProfile.setImageBitmap(picture);
            }
        }else{
            if(resultCode==RESULT_OK){
                //URI - unified resource locator is something like URL but can hold different type of paths
                // examples: file:///something.txt, http://www.example.com/, ftp://example.com
                // A Uri object is usually used to tell a ContentProvider what we want to access by reference
                Uri targetUri=data.getData();
                try {
                    //decode an input stream URI into a bitmap.
                    //openInputStream, Opens a stream on to the content associated with a content URI. If there is no data associated with the URI, FileNotFoundException is thrown
                    // inputStream is A readable source of bytes. Most clients will use input streams that read data from the file system.
                    //ContentResolver is something we use to be able to reach ContentProvider as a client/user in order to connect with provider.
                    picture= BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    imageViewProfile.setImageBitmap(picture);
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    }
}