package com.example.george.myfirstaplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class HelperOwner {
    DataBaseHelper helper;
    Context context;
    SQLiteDatabase db;

    public HelperOwner(Context context) {
        helper = new DataBaseHelper(context);
    }

    public long insertFoodIntoDatabase(String foodName, String proteinNumber, String carboNumber, String fatNumber) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.FOOD_NAME, foodName);
        contentValues.put(DataBaseHelper.PROTEIN_NUMBER, proteinNumber);
        contentValues.put(DataBaseHelper.CARBOHYDRATES_NUMBER, carboNumber);
        contentValues.put(DataBaseHelper.FAT_NUMBER, fatNumber);
        long id = sqLiteDatabase.insert(DataBaseHelper.FOOD_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    public String getProteinaDinDatabase(String nume){
        String proteins = " ";
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.FOOD_NAME, DataBaseHelper.PROTEIN_NUMBER, DataBaseHelper.CARBOHYDRATES_NUMBER, DataBaseHelper.FAT_NUMBER};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FOOD_TABLE,
                columns, DataBaseHelper.FOOD_NAME + " = '" + nume + "' "
                , null, null, null, null);

        while(cursor.moveToNext()){
            int columnIndex = cursor.getColumnIndex(DataBaseHelper.PROTEIN_NUMBER);
            proteins = cursor.getString(columnIndex);
        }
        return proteins;
    }
    public String getCarbohidratiDinDatabase(String nume){
        String carbo = " ";
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.FOOD_NAME, DataBaseHelper.PROTEIN_NUMBER, DataBaseHelper.CARBOHYDRATES_NUMBER, DataBaseHelper.FAT_NUMBER};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FOOD_TABLE, columns, DataBaseHelper.FOOD_NAME +  " = '" + nume + "' ", null,null,null,null);

        while(cursor.moveToNext()){
            int columnIndex = cursor.getColumnIndex(DataBaseHelper.CARBOHYDRATES_NUMBER);
            carbo = cursor.getString(columnIndex);
        }
        return carbo;
    }
    public String getGrasimiDinDataBase(String nume){
        String grasimi = " ";
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.FOOD_NAME, DataBaseHelper.PROTEIN_NUMBER, DataBaseHelper.CARBOHYDRATES_NUMBER, DataBaseHelper.FAT_NUMBER};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FOOD_TABLE, columns, DataBaseHelper.FOOD_NAME +  " = '" + nume + "' ", null,null,null,null);

        while(cursor.moveToNext()){
            int columnIndex = cursor.getColumnIndex(DataBaseHelper.FAT_NUMBER);
            grasimi = cursor.getString(columnIndex);
        }
        return grasimi;
    }
    public long insertExerciseIntoDatabase(String exerciseName, String exerciseWeight, String exerciseSets, String exerciseReps){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.EXERCISE_NAME, exerciseName);
        contentValues.put(DataBaseHelper.EXERCISE_WEIGHT, exerciseWeight);
        contentValues.put(DataBaseHelper.EXERCISE_SET, exerciseSets);
        contentValues.put(DataBaseHelper.EXERCISE_REPS, exerciseReps);
        long id = sqLiteDatabase.insert(DataBaseHelper.EXERCISE_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    public long inserPhotoIntoDatabase(String name, Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100,bos);
        byte[] bitmapData = bos.toByteArray();
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.PHOTO_NAME, name);
        cv.put(DataBaseHelper.PHOTO, bitmapData);
        long id = sqLiteDatabase.insert(DataBaseHelper.PHOTO_TABLE, null, cv);
        sqLiteDatabase.close();
        return id;
    }

    public Bitmap getPoza(String nume){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String colums[] = {DataBaseHelper.PHOTO_NAME, DataBaseHelper.PHOTO};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.PHOTO_TABLE, colums, DataBaseHelper.PHOTO_NAME + " = '" + nume + "' ", null, null, null, null);
        byte[] imageRetrived = null;

        int indexofPhoto = cursor.getColumnIndex(DataBaseHelper.PHOTO);
        while(cursor.moveToNext()){
            imageRetrived = cursor.getBlob(indexofPhoto);

        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageRetrived, 0, imageRetrived.length);
        return bitmap;
    }
    public ArrayList<Bitmap> getAllPhotosInAList(){
        ArrayList<Bitmap> listaPoze =new ArrayList<Bitmap>();
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.UID, DataBaseHelper.PHOTO};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.PHOTO_TABLE, columns,null,null,null,null,null);
        byte[] myPhoto = null;
        int indexOfPhoto = cursor.getColumnIndex(DataBaseHelper.PHOTO);
        while(cursor!=null){
            myPhoto = cursor.getBlob(indexOfPhoto);
            Bitmap poze = BitmapFactory.decodeByteArray(myPhoto, 0, myPhoto.length);
            listaPoze.add(poze);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return listaPoze;
    }
    public long insertChronometerIntoDatabase(String time){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.TIME, time);
        long id = sqLiteDatabase.insert(DataBaseHelper.TIME_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }

    public Cursor getTime(){
        Cursor cursor;
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.UID,DataBaseHelper.TIME};
        cursor = sqLiteDatabase.query(DataBaseHelper.TIME_TABLE, columns, null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToLast();
        }
        return cursor;
    }
//1
    public long insertGenoflexiuni(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.GENOFLEXIUNI_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //2
    public long insertPresaPicioare(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.PRESA_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //3
    public long insertLunges(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.LUNGES_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //4
    public long insertImpinsOrizontal(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.IMPINS_ORIZONTAL, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //5
    public long insertImpinsInclinat(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.IMPINS_INCLINAT, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //6
    public long insertImpinsDeclinat(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.IMPINS_DECLINAT, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //7
    public long insertTractiuni(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.TRACTIUNI_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //8
    public long insertRamat(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.RAMAT_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //9
    public long insertIndreptari(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.INDREPTARI_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //10
    public long insertFlexiiZ(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.FLEXII_Z, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //12
    public long insertFlexiiGantere(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.FLEXII_GANTERE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //13
    public long insertFlexiiHammer(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.FLEXII_HAMMER, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //14
    public long insertPresaUmeri(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.PRESA_UMERI, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //15
    public long insertFluturariGantere(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.FLUTURARI_GANTERE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    //16
    public long insertFluturariAparat(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.FLUTURARI_APARAT, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    public long insertAbdomene(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.ABDOMENE_CLASICE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    public long insertAbdomeneAparat(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.ABDOMENE_CABLURI, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }
    public long insertAbdomeneInversate(String dateTime, String weight, String sets, String repetitions){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.DATE_TIME, dateTime);
        contentValues.put(DataBaseHelper.GREUTATE_EXERCITIU, weight);
        contentValues.put(DataBaseHelper.NUMBER_SETURI, sets);
        contentValues.put(DataBaseHelper.NUMBER_REPETITIONS, repetitions);
        long id = sqLiteDatabase.insert(DataBaseHelper.ABDOMENE_INVERSATE, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }

    public Cursor getAllGenoflexiuni(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.GENOFLEXIUNI_TABLE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllFoods(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID, DataBaseHelper.FOOD_NAME, DataBaseHelper.PROTEIN_NUMBER, DataBaseHelper.CARBOHYDRATES_NUMBER, DataBaseHelper.FAT_NUMBER};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FOOD_TABLE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllPresaPicioare(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.PRESA_TABLE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllLunges(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.LUNGES_TABLE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllTractiuni(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.TRACTIUNI_TABLE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllRamat(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.RAMAT_TABLE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllIndreptari(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.INDREPTARI_TABLE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllImpinsOrizontal(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.IMPINS_ORIZONTAL, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllImpinsDeclinat(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.IMPINS_DECLINAT, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllImpinsInclinat(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.IMPINS_INCLINAT, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllFlexiiZ(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FLEXII_Z, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllFlexiiGantere(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FLEXII_GANTERE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllFlexiiHammer(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FLEXII_HAMMER, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllPresaUmeri(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.PRESA_UMERI, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllFluturariGantere(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FLUTURARI_GANTERE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllFluturariAparat(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FLUTURARI_APARAT, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllAbdomene(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.ABDOMENE_CLASICE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllAbdomeneInversate(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME, DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI, DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.ABDOMENE_INVERSATE, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }
    public Cursor getAllAbdomeneAparat(){

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.UID,DataBaseHelper.DATE_TIME,
                DataBaseHelper.GREUTATE_EXERCITIU, DataBaseHelper.NUMBER_SETURI,
                DataBaseHelper.NUMBER_REPETITIONS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.ABDOMENE_CABLURI, columns, null, null, null, null, null);
        if(cursor != null)
        {
            cursor.moveToLast();
        }
        return cursor;
    }


    public String readProtein(String name) {
        String calories = new String();
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.FOOD_NAME, DataBaseHelper.PROTEIN_NUMBER, DataBaseHelper.CARBOHYDRATES_NUMBER, DataBaseHelper.FAT_NUMBER};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FOOD_TABLE, columns, DataBaseHelper.FOOD_NAME + " = '" + name + "' ", null, null, null, null);
        while (cursor.moveToNext()) {
            int caloriesIndex = cursor.getColumnIndex(DataBaseHelper.PROTEIN_NUMBER);
            calories = cursor.getString(caloriesIndex);
        }
        return calories;
    }

    public String readCarbo(String name) {
        String calories = new String();
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.FOOD_NAME, DataBaseHelper.PROTEIN_NUMBER, DataBaseHelper.CARBOHYDRATES_NUMBER, DataBaseHelper.FAT_NUMBER};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FOOD_TABLE, columns, DataBaseHelper.FOOD_NAME + " = '" + name + "' ", null, null, null, null);
        while (cursor.moveToNext()) {
            int caloriesIndex = cursor.getColumnIndex(DataBaseHelper.CARBOHYDRATES_NUMBER);
            calories = cursor.getString(caloriesIndex);
        }
        return calories;
    }

    public String readFat(String name) {
        String calories = new String();
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.FOOD_NAME, DataBaseHelper.PROTEIN_NUMBER, DataBaseHelper.CARBOHYDRATES_NUMBER, DataBaseHelper.FAT_NUMBER};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FOOD_TABLE, columns, DataBaseHelper.FOOD_NAME + " = '" + name + "' ", null, null, null, null);
        while (cursor.moveToNext()) {
            int caloriesIndex = cursor.getColumnIndex(DataBaseHelper.FAT_NUMBER);
            calories = cursor.getString(caloriesIndex);
        }
        return calories;
    }

    public String getWeightOfUser(String name){
        String weight = " ";
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.USER_NAME, DataBaseHelper.USER_WEIGHT, DataBaseHelper.USER_AGE, DataBaseHelper.USER_HEIGHT};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.USER_ACCOUNT, columns, DataBaseHelper.USER_NAME + " = '" + name + "' ", null,null,null,null);
        while(cursor.moveToNext()) {
            int weightIndex = cursor.getColumnIndex(DataBaseHelper.USER_WEIGHT);
            weight = cursor.getString(weightIndex);
        }

        return weight;
    }
    public String getHeightOfUser(String name){
        String height = " ";
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.USER_NAME, DataBaseHelper.USER_WEIGHT, DataBaseHelper.USER_AGE, DataBaseHelper.USER_HEIGHT};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.USER_ACCOUNT, columns, DataBaseHelper.USER_NAME + " = '" + name + "' ", null,null,null,null);
        while(cursor.moveToNext()) {
            int weightIndex = cursor.getColumnIndex(DataBaseHelper.USER_HEIGHT);
            height = cursor.getString(weightIndex);
        }

        return height;
    }
    public String getAgeOfUser(String name){
        String age = " ";
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.USER_NAME, DataBaseHelper.USER_WEIGHT, DataBaseHelper.USER_AGE, DataBaseHelper.USER_HEIGHT};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.USER_ACCOUNT, columns, DataBaseHelper.USER_NAME + " = '" + name + "' ", null,null,null,null);
        while(cursor.moveToNext()) {
            int weightIndex = cursor.getColumnIndex(DataBaseHelper.USER_AGE);
            age = cursor.getString(weightIndex);
        }

        return age;
    }


    public String readAllData() {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.FOOD_TABLE, DataBaseHelper.PROTEIN_NUMBER, DataBaseHelper.CARBOHYDRATES_NUMBER, DataBaseHelper.FAT_NUMBER};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FOOD_TABLE, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(DataBaseHelper.FOOD_NAME);
            int index2 = cursor.getColumnIndex(DataBaseHelper.PROTEIN_NUMBER);
            int index3 = cursor.getColumnIndex(DataBaseHelper.CARBOHYDRATES_NUMBER);
            int index4 = cursor.getColumnIndex(DataBaseHelper.FAT_NUMBER);

            String name = cursor.getString(index1);
            String protein = cursor.getString(index2);
            String carbo = cursor.getString(index3);
            String fat = cursor.getString(index4);
            buffer.append(name + " " + " " + protein + " " + carbo + " " + fat);
        }
        return buffer.toString();
    }
    public String returnExerciseWeight(String name){
        SQLiteDatabase sqLiteDatabase=helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.EXERCISE_NAME, DataBaseHelper.EXERCISE_WEIGHT};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.EXERCISE_TABLE, columns, DataBaseHelper.EXERCISE_NAME + " = '" + name + "'", null, null, null, null);
        int indexOfWeight = cursor.getColumnIndex(DataBaseHelper.EXERCISE_WEIGHT);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            String weight = cursor.getString(indexOfWeight);
            buffer.append(weight);
        }
        return buffer.toString();
    }
   public void updateExerciseWeight(String weight){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.EXERCISE_WEIGHT, weight);
        sqLiteDatabase.update(DataBaseHelper.EXERCISE_TABLE, cv, DataBaseHelper.EXERCISE_WEIGHT,null);
    }
    public String returnNumberOfSets(String name){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.EXERCISE_NAME, DataBaseHelper.EXERCISE_SET};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.EXERCISE_TABLE, columns, DataBaseHelper.EXERCISE_NAME + " = '" + name +"'", null,null,null,null);
        int indexOfSet = cursor.getColumnIndex(DataBaseHelper.EXERCISE_SET);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            String sets = cursor.getString(indexOfSet);
            buffer.append(sets);
        }
        return buffer.toString();
    }

    public long insertUsers(String name, String weight, String height, String age){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.USER_NAME, name);
        contentValues.put(DataBaseHelper.USER_WEIGHT, weight);
        contentValues.put(DataBaseHelper.USER_HEIGHT, height);
        contentValues.put(DataBaseHelper.USER_AGE, age);
        long id = sqLiteDatabase.insert(DataBaseHelper.USER_ACCOUNT, null, contentValues);
        sqLiteDatabase.close();
        return id;
    }



    public String returnNumberOfRepetitions(String name){
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String columns[] = {DataBaseHelper.EXERCISE_NAME, DataBaseHelper.EXERCISE_REPS};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.EXERCISE_TABLE, columns, DataBaseHelper.EXERCISE_NAME + " = '" + name + "'",null,null,null,null);
        int indexOfReps = cursor.getColumnIndex(DataBaseHelper.EXERCISE_REPS);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()){
            String repetitions = cursor.getString(indexOfReps);
            buffer.append(repetitions);
        }
        return buffer.toString();
    }



    public String returnFoodName(String name) {
        String food = new String() ;
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String[] columns = {DataBaseHelper.FOOD_NAME};
        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.FOOD_TABLE, columns, DataBaseHelper.FOOD_NAME + " = '" + name + "'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            cursor.getInt(0);
            int indexOfFood = cursor.getColumnIndex(DataBaseHelper.FOOD_NAME);
            food = cursor.getString(indexOfFood);
        }
        return food;
    }

    static class DataBaseHelper extends SQLiteOpenHelper {

       public static final String UID = "_id";

        //CONSTANTELE FOLOSITE PENTRU TABELUL CALORIC AL ALIMENTELOR!!
        private static final String DATABASE_NAME = "sportdatabase";
        //nume tabele exercitii
       public static final String GENOFLEXIUNI_TABLE = "Genoflexiuni";
        private static final String PRESA_TABLE = "Presa";
        private static final String LUNGES_TABLE = "Lunges";
        private static final String IMPINS_ORIZONTAL = "ImpinsO";
        private static final String IMPINS_DECLINAT = "ImpinsD";
        private static final String IMPINS_INCLINAT = "ImpinsI";
        private static final String TRACTIUNI_TABLE = "Tractiuni";
        private static final String INDREPTARI_TABLE = "Indreptari";
        private static final String RAMAT_TABLE = "Ramat";
        private static final String FLEXII_Z = "FlexiiZ";
        private static final String FLEXII_GANTERE = "FlexiiG";
        private static final String FLEXII_HAMMER = "FlexiiH";
        private static final String ABDOMENE_CLASICE = "Abdomene";
        private static final String ABDOMENE_INVERSATE = "AbdomeneI";
        private static final String ABDOMENE_CABLURI = "AbdomeneC";
        private static final String PRESA_UMERI = "PresaU";
        private static final String FLUTURARI_GANTERE = "FluturariG";
        private static final String FLUTURARI_APARAT = "FluturariA";
        //repetari + seturi
       public static final String DATE_TIME = "DateAndTime";
        public static final String GREUTATE_EXERCITIU = "Greutate";
        public static final String NUMBER_REPETITIONS = "Repetitions";
        public static final String NUMBER_SETURI = "Seturi";
        //
        public static final String TIME_TABLE = "TimeTable";
        public static final String TIME = "ExactTime";

        // tabele individuale
        private static final String CREATE_TABLE_TIME = "CREATE TABLE " + TIME_TABLE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TIME + " VARCHAR(255));";
        private static final String CREATE_GENOFLEXIUNI = "CREATE TABLE "
                + GENOFLEXIUNI_TABLE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_PRESA = "CREATE TABLE "
                + PRESA_TABLE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_LUNGES = "CREATE TABLE "
                + LUNGES_TABLE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_IMPINS_ORIZONTAL = "CREATE TABLE "
                + IMPINS_ORIZONTAL + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_IMPINS_INCLINAT = "CREATE TABLE "
                + IMPINS_INCLINAT + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_IMPINS_DECLINAT = "CREATE TABLE "
                + IMPINS_DECLINAT + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_TRACTIUNI = "CREATE TABLE "
                + TRACTIUNI_TABLE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_INDREPTARI = "CREATE TABLE "
                + INDREPTARI_TABLE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_RAMAT = "CREATE TABLE "
                + RAMAT_TABLE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_FLEXII_Z = "CREATE TABLE "
                + FLEXII_Z + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_FLEXII_GANTERE = "CREATE TABLE "
                + FLEXII_GANTERE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_FLEXII_HAMMER = "CREATE TABLE "
                + FLEXII_HAMMER + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_ABDOMENE_CLASICE = "CREATE TABLE "
                + ABDOMENE_CLASICE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_ABDOMENE_INVERSATE = "CREATE TABLE "
                + ABDOMENE_INVERSATE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_ABDOMENE_CABLURI = "CREATE TABLE "
                + ABDOMENE_CABLURI + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_PRESA_UMERI = "CREATE TABLE "
                + PRESA_UMERI + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_FLUTURARI_GANTERE = "CREATE TABLE "
                + FLUTURARI_GANTERE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";
        private static final String CREATE_FLUTURARI_CABLURI = "CREATE TABLE "
                + FLUTURARI_APARAT + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE_TIME + " VARCHAR(255), " + GREUTATE_EXERCITIU + " VARCHAR(255), "
                + NUMBER_SETURI + " VARCHAR(255), " + NUMBER_REPETITIONS + " VARCHAR(255));";


        //other stuff

        public static final String FOOD_TABLE = "Food";
        public static final String FOOD_NAME = "Name";
        public static final String PROTEIN_NUMBER = "Proteins";
        public static final String CARBOHYDRATES_NUMBER = "Carbohydrates";
        public static final String FAT_NUMBER = "FATS";
        private static final String EXERCISE_TABLE="Exercise";
        private static final String EXERCISE_NAME = "Exercise";
        private static final String EXERCISE_WEIGHT="Weight";
        private static final String EXERCISE_SET = "Seturi";
        private static final String EXERCISE_REPS = "Reps";
        private static final String PHOTO_TABLE = "Pictures";
        private static final String PHOTO_NAME = "PictureNames";
        private static final String PHOTO = "Picture";
        private static final String USER_ACCOUNT = "Utilizatori";
        private static final String USER_NAME = "Nume";
        private static final String USER_WEIGHT = "Greutate";
        private static final String USER_HEIGHT = "Inaltime";
        private static final String USER_AGE = "Varsta";


        private static final int DB_VERSION = 1;

        private static final String CREATE_TABLE_FOOD = "CREATE TABLE " + FOOD_TABLE + " ("
                + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FOOD_NAME + " VARCHAR(255), " + PROTEIN_NUMBER + " VARCHAR(255), " +CARBOHYDRATES_NUMBER+ " VARCHAR(255), "
                +FAT_NUMBER+ " VARCHAR(255));";
        private static final String CREATE_USERS_TABLE = "CREATE TABLE " + USER_ACCOUNT + " ("
                + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_NAME + " VARCHAR(255), "
                + USER_WEIGHT + " VARCHAR(255), "
                + USER_HEIGHT + " VARCHAR(255), "
                + USER_AGE + " VARCHAR(255));";
        private static final String CREATE_TABLE_EXERCISES ="CREATE TABLE " + EXERCISE_TABLE + " ("
                + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EXERCISE_NAME + " VARCHAR(255), " + EXERCISE_WEIGHT + " VARCHAR(255), "
                +EXERCISE_SET+ " VARCHAR(255), " +EXERCISE_REPS+ " VARCHAR(255));";
       private static final String CREATE_PHOTO_TABLE = "CREATE TABLE " + PHOTO_TABLE + " ("
               + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
               + PHOTO_NAME + " VARCHAR(255), " + PHOTO + " BLOB);";

        private static final String DROP_TABLE_FOOD = "DROP TABLE IF EXISTS " + FOOD_TABLE;
        private static final String DROP_TABLE_EXERCISE = "DROP TABLE IF EXISTS " + EXERCISE_TABLE;
        private static final String DROP_TABLE_PHOTO = "DROP TABLE IF EXISTS " + PHOTO_TABLE;
        private static final String DROP_GENO = "DROP TABLE IF EXISTS " + GENOFLEXIUNI_TABLE;
        private static final String DROP_PRESA = "DROP TABLE IF EXITS " + PRESA_TABLE;
        private static final String DROP_LUNGES = "DROP TABLE IF EXISTS " + LUNGES_TABLE;
        private static final String DROP_TRACTIUNI = "DROP TABLE IF EXISTS " + TRACTIUNI_TABLE;
        private static final String DROP_INDREPTARI = "DROP TABLE IF EXISTS " +INDREPTARI_TABLE;
        private static final String DROP_RAMAT = "DROP TABLE IF EXITS " + RAMAT_TABLE;
        private static final String DROP_IMPINS_ORIZONTAL = "DROP TABLE IF EXISTS " + IMPINS_ORIZONTAL;
        private static final String DROP_IMPINS_INCLINAT = "DROP TABLE IF EXISTS " + IMPINS_INCLINAT;
        private static final String DROP_IMPINS_DECLINAT = "DROP TABLE IF EXISTS " + IMPINS_DECLINAT;
        private static final String DROP_ABDOMENE = "DROP TABLE IF EXISTS " + ABDOMENE_CLASICE;
        private static final String DROP_ABDOMENE_INVERSATE = "DROP TABLE IF EXISTS " + ABDOMENE_INVERSATE;
        private static final String DROP_ABDOMENE_CABLURI = "DROP TABLE IF EXISTS " + ABDOMENE_CABLURI;
        private static final String DROP_PRESA_UMER = "DROP TABLE IF EXISTS " + PRESA_UMERI;
        private static final String DROP_FLUTURARI_GANTERE = "DROP TABLE IF EXISTS " + FLUTURARI_GANTERE;
        private static final String DROP_FLUTURARI_CABLURI = "DROP TABLE IF EXISTS " + FLUTURARI_APARAT;
        private static final String DROP_FLEXII_Z = "DROP TABLE IF EXISTS " + FLEXII_Z;
        private static final String DROP_FLEXII_CLASICE = "DROP TABLE IF EXISTS " + FLEXII_GANTERE;
        private static final String DROP_FLEXII_HAMMER = "DROP TABLE IF EXISTS " + FLEXII_HAMMER;
        private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS " + USER_ACCOUNT;
        private static final String DROP_TIME_TABLE = "DROP TABLE IF EXISTS " + TIME_TABLE;
        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USERS_TABLE);
            db.execSQL(CREATE_TABLE_FOOD);
            db.execSQL(CREATE_TABLE_EXERCISES);
            db.execSQL(CREATE_TABLE_TIME);
            db.execSQL(CREATE_PHOTO_TABLE);
            db.execSQL(CREATE_ABDOMENE_CABLURI);
            db.execSQL(CREATE_ABDOMENE_CLASICE);
            db.execSQL(CREATE_ABDOMENE_INVERSATE);
            db.execSQL(CREATE_GENOFLEXIUNI);
            db.execSQL(CREATE_INDREPTARI);
            db.execSQL(CREATE_RAMAT);
            db.execSQL(CREATE_IMPINS_ORIZONTAL);
            db.execSQL(CREATE_IMPINS_DECLINAT);
            db.execSQL(CREATE_IMPINS_INCLINAT);
            db.execSQL(CREATE_FLEXII_GANTERE);
            db.execSQL(CREATE_FLEXII_HAMMER);
            db.execSQL(CREATE_FLEXII_Z);
            db.execSQL(CREATE_LUNGES);
            db.execSQL(CREATE_PRESA);
            db.execSQL(CREATE_TRACTIUNI);
            db.execSQL(CREATE_PRESA_UMERI);
            db.execSQL(CREATE_FLUTURARI_CABLURI);
            db.execSQL(CREATE_FLUTURARI_GANTERE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_TABLE_FOOD);
            db.execSQL(DROP_TABLE_EXERCISE);
           db.execSQL(DROP_TABLE_PHOTO);
            db.execSQL(DROP_GENO);
            db.execSQL(DROP_PRESA);
            db.execSQL(DROP_LUNGES);
            db.execSQL(DROP_TRACTIUNI);
            db.execSQL(DROP_INDREPTARI);
            db.execSQL(DROP_RAMAT);
            db.execSQL(DROP_IMPINS_DECLINAT);
            db.execSQL(DROP_IMPINS_INCLINAT);
            db.execSQL(DROP_IMPINS_ORIZONTAL);
            db.execSQL(DROP_FLEXII_CLASICE);
            db.execSQL(DROP_FLEXII_HAMMER);
            db.execSQL(DROP_FLEXII_Z);
            db.execSQL(DROP_ABDOMENE);
            db.execSQL(DROP_ABDOMENE_CABLURI);
            db.execSQL(DROP_ABDOMENE_INVERSATE);
            db.execSQL(DROP_PRESA_UMER);
            db.execSQL(DROP_FLUTURARI_CABLURI);
            db.execSQL(DROP_FLUTURARI_GANTERE);
            db.execSQL(DROP_USERS_TABLE);
            db.execSQL(DROP_TIME_TABLE);
            onCreate(db);

        }
    }
}