package com.example.george.myfirstaplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class AdapterListaPoze extends ArrayAdapter<Bitmap> {

   Context context;

    public AdapterListaPoze(Context context, ArrayList<Bitmap> pictures){
        super(context,0,pictures);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            v = inflater.inflate(R.layout.row_item_exercise, null);
            ImageView image = (ImageView)v.findViewById(R.id.imgIcon);



        }
        return null;
    }
}
