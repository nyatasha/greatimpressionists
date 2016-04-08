package com.mycomp.myfirstapp;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapterMagritt extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.nostalgia,
            R.drawable.golkonda,
            R.drawable.verolomstvo_obrazov,
            R.drawable.vlublennye,
            R.drawable.the_son_of_the_man,
            R.drawable.glavnaya_istoria,
            R.drawable.zadumchivost_odinokogo_guljajushhego,
            R.drawable.ogon,
            R.drawable.tajnyj_igrok,
            R.drawable.fizioterapevt,
            R.drawable.kanikuly_gegelja,
            R.drawable.jalo,
            R.drawable.iznasilovanie,
            R.drawable.pamjati_maka_senneta,
            R.drawable.izbriratelnoe_srodstvo,
            R.drawable.chelovecheskoe_polozhenie,
            R.drawable.neozhidannyj_otvet,
            R.drawable.poterjannyj_zhokej,
            R.drawable.kluch_ot_snovidenij,
            R.drawable.slovo_i_izobrazhenie,
            R.drawable.kollazh_bez_nazvanija,
            R.drawable.okno,
            R.drawable.pejzazh,
            R.drawable.zamok_v_pireneyax,
            R.drawable.falshivoe_zerkalo,
            R.drawable.osvobozhdenie,
            R.drawable.gotovij_buket
    };

    // Constructor
    public ImageAdapterMagritt(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Integer getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(210, 210));
        return imageView;
    }
}