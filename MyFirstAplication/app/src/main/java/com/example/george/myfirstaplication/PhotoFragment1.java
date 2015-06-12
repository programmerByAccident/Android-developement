package com.example.george.myfirstaplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by George on 5/27/2015.
 */
public class PhotoFragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v=  inflater.inflate(R.layout.photo_fragment_1, container);

        return v;
    }
}
