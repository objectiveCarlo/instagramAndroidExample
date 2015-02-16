package com.example.carlobation.instagramgalleryapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.example.carlobation.instagramgalleryapp.fragments.ImageGalleryFragment;

/**
 * Created by carlobation on 2/16/15.
 */
public class ImageGalleryActivity extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment fr;
        String tag;

        tag = ImageGalleryFragment.class.getSimpleName();
        fr = getSupportFragmentManager().findFragmentByTag(tag);
        if (fr == null) {
            fr = new ImageGalleryFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, fr, tag).commit();

    }
}
