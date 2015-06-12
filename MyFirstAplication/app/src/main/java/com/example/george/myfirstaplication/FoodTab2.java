package com.example.george.myfirstaplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by George on 5/20/2015.
 */
public class FoodTab2 extends Fragment {

    ListView lista;
    EditText numeAliment;
    EditText numarProteine;
    EditText numarCarbo;
    EditText numarGrasimi;
    HelperOwner helperOwner;
    ImageButton insert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.food_tab_2, container, false);

        lista = (ListView)v.findViewById(R.id.listaAlimente);
        numeAliment = (EditText)v.findViewById(R.id.numeMancare);
        numarProteine = (EditText)v.findViewById(R.id.numarProteins);
        numarCarbo = (EditText)v.findViewById(R.id.numarCarbs);
        numarGrasimi = (EditText)v.findViewById(R.id.numarFats);
        helperOwner = new HelperOwner(getActivity());
        insert = (ImageButton)v.findViewById(R.id.insertFoood);
        populateListView();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              long id = helperOwner.insertFoodIntoDatabase(numeAliment.getText().toString(), numarProteine.getText().toString()
                , numarCarbo.getText().toString(), numarGrasimi.getText().toString());
                if(id>0)
                    System.out.println("Success");
                else System.out.println("Failed");

                populateListView();

            }
        });

        return v;
    }

    private void populateListView(){
        Cursor cursor = helperOwner.getAllFoods();
        String[] campuri = {HelperOwner.DataBaseHelper.UID,HelperOwner.DataBaseHelper.FOOD_NAME, HelperOwner.DataBaseHelper.PROTEIN_NUMBER, HelperOwner.DataBaseHelper.CARBOHYDRATES_NUMBER, HelperOwner.DataBaseHelper.FAT_NUMBER};
        int[] id = {R.id.idItemAliment, R.id.numeItemAliment,R.id.proteineItemAliment,R.id.carbohidratiItemAliment,R.id.grasimiItemAliment};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getActivity(),R.layout.food_items,cursor,campuri,id,0);
        lista.setAdapter(myCursorAdapter);


}}
