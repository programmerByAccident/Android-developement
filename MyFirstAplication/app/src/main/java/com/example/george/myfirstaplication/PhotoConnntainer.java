package com.example.george.myfirstaplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by George on 6/9/2015.
 */
public class PhotoConnntainer extends Activity {

    ImageView imageView;
    byte[] imageSent;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_containnner);
        imageView = (ImageView)findViewById(R.id.photoTata);

        Bundle intentExtras = new Bundle();
        if(intentExtras!=null)
        {
            imageSent = intentExtras.getByteArray("Poza");
        }
        bitmap = BitmapFactory.decodeByteArray(imageSent, 0, imageSent.length);
        imageView.setImageBitmap(bitmap);
    }
}
