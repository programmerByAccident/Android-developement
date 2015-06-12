package com.example.george.myfirstaplication;

import android.content.DialogInterface;
import android.database.Cursor;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by George on 5/20/2015.
 */
public class FoodTab1 extends Fragment{

    CaloricClass calorie;
    HelperOwner helperOwner;

    TextView totalCalories, proteinGoal, carbohydratesTarget, fatsLimit, weightPrinter, heightPrinter, agePrinter;
    EditText userName, foodName;
    ImageButton searchUser, searchFood;
    String username;
    String kg, m, an;
    Double w,h,a;
    Double allCaloriesDouble;
    String allCaloriesString;
    String proteineTicule, carboTicule, fatsTicule;
    Double proteineT, carboT, fatsT;

    //
    String proteinaDinBaza, carboDinBaza, grasimiDinBaza;
    Double proteinaBazaDouble, carboBazaDouble, grasimiBazaDouble;
    Double newPValue, newCValue, newFValue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.food_tab_1, container, false);
        calorie = new CaloricClass();
        helperOwner = new HelperOwner(getActivity());

        totalCalories = (TextView)v.findViewById(R.id.totalCalories);
        proteinGoal = (TextView)v.findViewById(R.id.proteinGoal);
        carbohydratesTarget = (TextView)v.findViewById(R.id.carbohydratesTarget);
        fatsLimit = (TextView)v.findViewById(R.id.fatsLimit);

        weightPrinter = (TextView)v.findViewById(R.id.weightPrinter);
        heightPrinter = (TextView)v.findViewById(R.id.heightPrinter);
        agePrinter = (TextView)v.findViewById(R.id.agePrinter);

        userName = (EditText)v.findViewById(R.id.username);
        foodName = (EditText)v.findViewById(R.id.foodname);

        searchUser = (ImageButton)v.findViewById(R.id.searchUser);
        searchFood = (ImageButton)v.findViewById(R.id.searchFood);

        searchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = userName.getText().toString();

                kg = helperOwner.getWeightOfUser(username);
                m = helperOwner.getHeightOfUser(username);
                an = helperOwner.getAgeOfUser(username);

                weightPrinter.setText(kg + " kg");
                heightPrinter.setText(m+ " cm");
                agePrinter.setText(an + " years");

                w = Double.parseDouble(kg);
                h = Double.parseDouble(m);
                a = Double.parseDouble(an);

                allCaloriesDouble = calorie.countCalories(w,h,a);
                allCaloriesString = allCaloriesDouble.toString();

                totalCalories.setText(allCaloriesString);

                proteineT = calorie.numarProteineDieta(allCaloriesDouble);
                carboT = calorie.numarCarboDieta(allCaloriesDouble);
                fatsT = calorie.numarGrasimiDieta(allCaloriesDouble);

                proteineTicule = String.format("%.2f", proteineT);
                carboTicule = String.format("%.2f", carboT);
                fatsTicule = String.format("%.2f", fatsT);

                proteinGoal.setText(proteineTicule);
                carbohydratesTarget.setText(carboTicule);
                fatsLimit.setText(fatsTicule);
            }
        });

        searchFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numeMancare = foodName.getText().toString();

                proteinaDinBaza = helperOwner.getProteinaDinDatabase(numeMancare);
                carboDinBaza = helperOwner.getCarbohidratiDinDatabase(numeMancare);
                grasimiDinBaza = helperOwner.getGrasimiDinDataBase(numeMancare);

                proteinaBazaDouble = Double.valueOf(proteinaDinBaza);
                carboBazaDouble = Double.valueOf(carboDinBaza);
                grasimiBazaDouble = Double.valueOf(grasimiDinBaza);

                String proteinGoalString, carboTargetString, fatsLimitString;
                Double proteinGoalStringDouble, carboTargetStringDouble, fatsLimitStringDouble;

                proteinGoalString = proteinGoal.getText().toString();
                carboTargetString = carbohydratesTarget.getText().toString();
                fatsLimitString = fatsLimit.getText().toString();

                proteinGoalStringDouble = Double.parseDouble(proteinGoalString.replace(",", "."));
                carboTargetStringDouble = Double.valueOf(carboTargetString.replace(",", "."));
                fatsLimitStringDouble = Double.valueOf(fatsLimitString.replace(",", "."));
                newPValue = proteinGoalStringDouble - proteinaBazaDouble;
                newCValue = carboTargetStringDouble - carboBazaDouble;
                newFValue = fatsLimitStringDouble - grasimiBazaDouble;

                if(newPValue > 0){
                    proteinGoal.setText(newPValue.toString());
                }
                else
                    proteinGoal.setText("DONE");

                if(newCValue > 0){
                    carbohydratesTarget.setText(newCValue.toString());
                }
                else
                    carbohydratesTarget.setText("DONE");

                if(newFValue > 0){
                    fatsLimit.setText(newFValue.toString());
                }
                else
                    fatsLimit.setText("DONE");
            }
        });


        return v;
    }

}

