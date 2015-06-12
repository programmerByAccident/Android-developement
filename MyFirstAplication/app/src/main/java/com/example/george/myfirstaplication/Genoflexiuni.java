package com.example.george.myfirstaplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by George on 5/31/2015.
 */
public class Genoflexiuni extends Activity {
    NumberPicker numberPicker;
    DatePicker datePicker;
    Calendar calendar;
    FrameLayout frameLayout;
    ImageButton insertButton;
    EditText weightText;
    EditText setsText;
    EditText repetitionsText;
    SQLiteDatabase sqLiteDatabase;
    HelperOwner helper;
    HelperOwner.DataBaseHelper dbHelper;
    ListView listView;



    private String geDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genoflexiuni);
        weightText=(EditText)findViewById(R.id.weightText);
        setsText=(EditText)findViewById(R.id.setsText);
        repetitionsText=(EditText)findViewById(R.id.repetitionsText);
        insertButton = (ImageButton)findViewById(R.id.insertButton);
        listView=(ListView)findViewById(R.id.listElemente);
        helper = new HelperOwner(this);
        populateListView();


        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weight = weightText.getText().toString();
                String sets = setsText.getText().toString();
                String repetitions = repetitionsText.getText().toString();
                String dateTime = geDateTime();

                long id = helper.insertGenoflexiuni(dateTime, weight, sets, repetitions);
                if (id > 0)
                    System.out.println("Succes MADAFAKAAAAA");
                else
                    System.out.println("Failed miserably");

                populateListView();
            }
        });


    }

    private void populateListView(){
        Cursor cursor = helper.getAllGenoflexiuni();
        String[] campuri = {HelperOwner.DataBaseHelper.UID,
                HelperOwner.DataBaseHelper.DATE_TIME,
                HelperOwner.DataBaseHelper.GREUTATE_EXERCITIU,
                HelperOwner.DataBaseHelper.NUMBER_SETURI,
                HelperOwner.DataBaseHelper.NUMBER_REPETITIONS};
        int[] id = {R.id.idViewItem, R.id.dateViewItem,R.id.weightViewItem,R.id.setsViewItem,R.id.repetitionsViewItem};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(Genoflexiuni.this,R.layout.inregistrare_exercitiu,cursor,campuri,id,0);
        listView.setAdapter(myCursorAdapter);

    }

}
