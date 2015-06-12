package com.example.george.myfirstaplication;

import java.math.BigDecimal;

/**
 * Created by George on 5/13/2015.
 */
public class CaloricClass {

    public CaloricClass(){}

    public double countCalories(double weight, double height, double age){
        double caloricNeeds = 0;
        return caloricNeeds=(10*weight)+(6.25*height)-(5*age)-5;
    }
    public double proteinPercentageNumber(double calories){
        double percentageOfTotal = calories/4;
        double proteinQuantity = percentageOfTotal*4;
        return proteinQuantity;
    }
    public double carohydratesPercentageNumber(double calories){
        double percentageOfTotal = calories/2;
        double carbohydratesQuantity = percentageOfTotal*4;
        return carbohydratesQuantity;
    }
    public double fatPercentageNumber(double calories){
        double percentageOfTotal = calories/4;
        double fatQuantity = percentageOfTotal*9;
        return fatQuantity;
    }

    public double numarCarboDieta(double calories){

        double caloriesCarbo = calories/2;
        double numarCarbo = caloriesCarbo/4;
        return numarCarbo;
    }

    public double numarProteineDieta(double calories){

        double caloriesProteina = calories/4;
        double numarProteina = caloriesProteina/4;
        return numarProteina;
    }
    public double numarGrasimiDieta(double calories){

        double caloriesProteina = calories/4;
        double numarGrasimi = caloriesProteina/9;
        return numarGrasimi;
    }


}
