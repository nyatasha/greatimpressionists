package com.mycomp.myfirstapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class FullImageActivityMagritt extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        final ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7e0d17")));
        actionBar.setIcon(R.drawable.ic_launcher);

        // get intent data
        Intent i = getIntent();
        int positionM = i.getExtras().getInt("Magritt");
        ImageAdapterMagritt ImageAdapterMagritt = new ImageAdapterMagritt(this);
        ImageView imageViewM = (ImageView) findViewById(R.id.full_image_view);
        imageViewM.setImageResource(ImageAdapterMagritt.mThumbIds[positionM]);
    }
}

