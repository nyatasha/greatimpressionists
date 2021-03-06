package com.mycomp.myfirstapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class FullImageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        final ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7e0d17")));
        actionBar.setIcon(R.drawable.ic_launcher);

        // get intent data
        Intent i = getIntent();
        int positionD = i.getExtras().getInt("Dali");
        ImageAdapter ImageAdapter = new ImageAdapter(this);
        ImageView imageViewD = (ImageView) findViewById(R.id.full_image_view);
        imageViewD.setImageResource(ImageAdapter.mThumbIds[positionD]);
    }
}
