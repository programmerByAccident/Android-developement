package com.example.george.myfirstaplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by George on 5/30/2015.
 */
public class WeightActivity extends Activity {
    Button registeredUser;
    EditText name;
    EditText wText;
    EditText heightText;
    EditText ageText;
    String nume, greutate, inaltime, varsta;
    HelperOwner helperOwner;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ampulea);
        bundle = new Bundle();
    //
        registeredUser = (Button)findViewById(R.id.registerUserButton);
        name = (EditText)findViewById(R.id.getNameText);
        wText = (EditText)findViewById(R.id.getWeightText);
        heightText = (EditText)findViewById(R.id.getHeightText);
        ageText = (EditText)findViewById(R.id.getAgeText);
       //
        helperOwner = new HelperOwner(this);
    //
        registeredUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nume = name.getText().toString();
                greutate = wText.getText().toString();
                inaltime = heightText.getText().toString();
                varsta = ageText.getText().toString();
//
                long id = helperOwner.insertUsers(nume, greutate, inaltime, varsta);
                if(id<0)
                    System.out.println("Success!!!");
                else
                    System.out.println("Failed!!");

                Intent intent = new Intent(WeightActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });
    }
}
