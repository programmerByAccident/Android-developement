package com.example.george.myfirstaplication;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by George on 5/10/2015.
 */
public class FragmentTab1 extends android.support.v4.app.Fragment{
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    ArrayList<String> titlesList;
    HashMap<String, ArrayList<String>> itemsList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1, container, false);
        expandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);

        listaElemente();



        expandableListAdapter = new ExpandableListAdapter(getActivity(), titlesList, itemsList);

        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(groupPosition==0)
                {
                    switch(childPosition){
                        case 0:
                            Intent intent1 = new Intent(getActivity(), Tractiuni.class);
                            startActivity(intent1);
                            break;
                        case 1:
                            Intent intent2= new Intent(getActivity(), Indreptari.class);
                            startActivity(intent2);
                            break;
                        case 2:
                            Intent intent3 = new Intent(getActivity(), Ramat.class);
                            startActivity(intent3);
                            break;
                    }
                }
                else
                    if(groupPosition==1){
                        switch (childPosition){
                            case 0:
                                Intent intent1 = new Intent(getActivity(), ImpinsOrizontal.class);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent2 = new Intent(getActivity(), ImpinsInclinat.class);
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(getActivity(), ImpinsDeclinat.class);
                                startActivity(intent3);
                                break;

                        }
                    }
                    else
                    if(groupPosition==2){
                        switch (childPosition){
                            case 0:
                                Intent intent1 = new Intent(getActivity(), Genoflexiuni.class);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent2 = new Intent(getActivity(), Presa.class);
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(getActivity(), Lunge.class);
                                startActivity(intent3);
                                break;

                        }
                    }
                    else
                    if(groupPosition==3){
                        switch (childPosition){
                            case 0:
                                Intent intent1 = new Intent(getActivity(), FlexiiZ.class);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent2 = new Intent(getActivity(), FlexiiClasic.class);
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(getActivity(), FlexiiHammer.class);
                                startActivity(intent3);
                                break;

                        }
                    }
                    else
                    if(groupPosition==4){
                        switch (childPosition){
                            case 0:
                                Intent intent1 = new Intent(getActivity(), Abdomente.class);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent2 = new Intent(getActivity(), AbdomeneInversate.class);
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(getActivity(), AbdomeneAparat.class);
                                startActivity(intent3);
                                break;

                        }
                    }
                    else
                    if(groupPosition==5){
                        switch (childPosition){
                            case 0:
                                Intent intent1 = new Intent(getActivity(), PresaUmeri.class);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent2 = new Intent(getActivity(), FluturariGantere.class);
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(getActivity(), FluturariAparat.class);
                                startActivity(intent3);
                                break;

                        }
                    }
            return true;
            }
        });


        return v;
    }

    private void listaElemente(){
        titlesList = new ArrayList<String>();
        itemsList = new HashMap<String, ArrayList<String>>();

        titlesList.add("Spate");
        titlesList.add("Piept");
        titlesList.add("Picioare");
        titlesList.add("Biceps");
        titlesList.add("Abdomen");
        titlesList.add("Umeri");

        ArrayList<String> exercitiiSpate = new ArrayList<String>();
        exercitiiSpate.add("Tractiuni priza larga");
        exercitiiSpate.add("Indreptri");
        exercitiiSpate.add("Ramat cu bara");

        ArrayList<String> exercitiiPiept = new ArrayList<String>();
        exercitiiPiept.add("Impins plan orizontal");
        exercitiiPiept.add("Impins plan inclinat");
        exercitiiPiept.add("Impins plan declinat");

        ArrayList<String> exercitiiPicioare = new ArrayList<String>();
        exercitiiPicioare.add("Genoflexiuni");
        exercitiiPicioare.add("Presa");
        exercitiiPicioare.add("Lunges");

        ArrayList<String> exercitiiBicepsTriceps = new ArrayList<String>();
        exercitiiBicepsTriceps.add("Flexii bara Z");
        exercitiiBicepsTriceps.add("Flexii gantere");
        exercitiiBicepsTriceps.add("Flexii hammer");


        ArrayList<String> exercitiiAbdomen = new ArrayList<String>();
        exercitiiAbdomen.add("Abdomene");
        exercitiiAbdomen.add("Abdomene inversate");
        exercitiiAbdomen.add("Abdomene aparat");

        ArrayList<String> exercitiiUmeri = new ArrayList<String>();
        exercitiiUmeri.add("Presa umeri");
        exercitiiUmeri.add("Fluturari gantere");
        exercitiiUmeri.add("Fluturari aparat");

        itemsList.put(titlesList.get(0), exercitiiSpate);
        itemsList.put(titlesList.get(1), exercitiiPiept);
        itemsList.put(titlesList.get(2), exercitiiPicioare);
        itemsList.put(titlesList.get(3), exercitiiBicepsTriceps);
        itemsList.put(titlesList.get(4), exercitiiAbdomen);
        itemsList.put(titlesList.get(5), exercitiiUmeri);

    }

}
