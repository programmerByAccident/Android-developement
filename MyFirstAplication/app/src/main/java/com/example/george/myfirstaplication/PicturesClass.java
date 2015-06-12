package com.example.george.myfirstaplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.text.format.Time;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by George on 6/9/2015.
 */
public class PicturesClass extends Activity{
    static final int REQUEST_IMAGE_CAPTURE =1;
    ImageButton takePicture, saveButton, datePick;
    ImageButton before;
    int year;
    int month;
    int day;
    ImageView left;
    HelperOwner owner;
    byte[] picture;
    Bitmap pictureTaken;
    ByteArrayOutputStream stream;
    String datePhoto;
    Time currDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictures_layout);
        stream = new ByteArrayOutputStream();
        owner = new HelperOwner(this);
        left = (ImageView)findViewById(R.id.left);
        takePicture =(ImageButton)findViewById(R.id.takePicture);
        before = (ImageButton)findViewById(R.id.photoLeft);
        saveButton = (ImageButton)findViewById(R.id.saveButton);
        datePick = (ImageButton)findViewById(R.id.datePick);
        currDate = new Time(Time.getCurrentTimezone());
        currDate.setToNow();


        DatePickerDialog.OnDateSetListener datePickerListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay)
                    {
                        year = selectedYear;
                        month = selectedMonth+1;
                        day = selectedDay;
                        datePhoto = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
                    }
                };
        final DatePickerDialog d = new DatePickerDialog(this, datePickerListener,
                currDate.year,  currDate.month, currDate.monthDay);

        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.show();
            }
        });


        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               pictureTaken = owner.getPoza(datePhoto);
                left.setImageBitmap(pictureTaken);
                System.out.println(datePhoto);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
           final Bitmap image = (Bitmap)extras.get("data");

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String time = geDateTime();

                   long id =  owner.inserPhotoIntoDatabase(time, image);
                    if(id>0)
                        System.out.println("Success");
                    else System.out.println("Failed");
                }
            });
        }
    }

    public void takePhoto(){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
    }
    private String geDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);

    }
}
