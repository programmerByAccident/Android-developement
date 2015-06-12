package com.example.george.myfirstaplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by George on 5/27/2015.
 */
public class PhotoFragment2 extends Fragment {
    ListView listView;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.photo_fragment_2,container,false);
        HelperOwner helper = new HelperOwner(getActivity());
        listView = (ListView)v.findViewById(R.id.listView);
        ArrayList<Bitmap> myList = helper.getAllPhotosInAList();
        ArrayAdapter<Bitmap> adapter = new ArrayAdapter<Bitmap>(getActivity(), R.layout.row_item_exercise, myList);
        listView.setAdapter(adapter);
        return v;
    }
}
