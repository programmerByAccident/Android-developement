package com.example.george.myfirstaplication;

import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by George on 5/10/2015.
 */
public class FragmentTab2 extends Fragment {

    ListView timeList;
    Chronometer cardio;
    String chronometerText;
    HelperOwner helperOwner;
    MyCountDownTimes countDownTimes;
    Button c1,c2,c3;

    long start, interval;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_2,container,false);
        cardio = (Chronometer) v.findViewById(R.id.chronometer);

        c1 = (Button)v.findViewById(R.id.startCardio);
        c2 = (Button)v.findViewById(R.id.stopCardio);
        c3 = (Button)v.findViewById(R.id.pop);
        helperOwner = new HelperOwner(getActivity());
        timeList = (ListView) v.findViewById(R.id.timeView);


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardio.setBase(SystemClock.elapsedRealtime());
                cardio.start();
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardio.stop();
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chronometerText = cardio.getText().toString();
                helperOwner.insertChronometerIntoDatabase(chronometerText);

                populateTimeList();
            }
        });


        return v;
    }

    private void populateTimeList(){

        Cursor cursor = helperOwner.getTime();
        String columns[] = {HelperOwner.DataBaseHelper.UID, HelperOwner.DataBaseHelper.TIME};
        int id[] = {R.id.idTime, R.id.exactTime};
        SimpleCursorAdapter simpleCursorAdapter;
        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),R.layout.time_layout_item, cursor, columns, id,0);
        timeList.setAdapter(simpleCursorAdapter);

    }
}

