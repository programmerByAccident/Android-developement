package com.example.george.myfirstaplication;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

import static com.example.george.myfirstaplication.R.*;

public class MainActivity extends ActionBarActivity implements Communicator {
    private Toolbar toolbar;
    ImageButton pictures;
    Button plm;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override // first window menu
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        //this.toolbar = (Toolbar)findViewById(id.app_bar);
      //  setActionBar(toolbar);
        ImageButton weightButton = (ImageButton) findViewById(R.id.weightButton);
        ImageButton trainingButton = (ImageButton) findViewById(R.id.trainingButton);
        ImageButton foodButton = (ImageButton) findViewById(id.foodButton);

        pictures = (ImageButton)findViewById(id.pictures);




        trainingButton.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, TrainingActivity.class);
                startActivity(in);
            }
        });
        foodButton.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(in);
            }
        });
        weightButton.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, WeightActivity.class);
                startActivity(in);
            }
        });
        pictures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PicturesClass.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void respond(String data) {

    }
}



