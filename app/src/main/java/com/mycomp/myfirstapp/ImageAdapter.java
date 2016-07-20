package com.mycomp.myfirstapp;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.perexodnyj_moment,
            R.drawable.portret_pikasso,
            R.drawable.melanxoliya,
            R.drawable.chasha_zhizni,
            R.drawable.antropomorfnyj_shkafchik,
            R.drawable.pol_elyuar,
            R.drawable.ischezayushhie_obrazy,
            R.drawable.teni_tayushhej_nochi,
            R.drawable.portret_gala_s_nosorogicheskimi_priznakami,
            R.drawable.telefon_omar,
            R.drawable.put_zagadki,
            R.drawable.galateya_sfer,
            R.drawable.melkie_ostanki,
            R.drawable.rasshheplenie_atoma,
            R.drawable.urok_anatomii,
            R.drawable.prosveshhennye_udovolstviya,
            R.drawable.madonna_port_ligata,
            R.drawable.beskonechnaya_zagadka,
            R.drawable.med_slashhe_krovi,
            R.drawable.lastochkin_xvost,
            R.drawable.korolevskoe_serdce,
            R.drawable.padshij_angel,
            R.drawable.mrachnaya_igra,
            R.drawable.lebedi_otrazhennye_v_slonax,
            R.drawable.zagadka_gitlera,
            R.drawable.plot_na_kamnyax,
            R.drawable.germes,
            R.drawable.osennij_kannibalizm,
            R.drawable.nezrimyj_chelovek,
            R.drawable.zhenshhina_s_golovoj_iz_roz,
            R.drawable.korzinka_s_xlebom,
            R.drawable.edinorog,
            R.drawable.dante,
            R.drawable.natyurmort,
            R.drawable.tri_vozrasta,
            R.drawable.tajnaya_vecherya,
            R.drawable.velaskes_pishushhij_portret,
            R.drawable.avtoportret,
            R.drawable.fontan,
            R.drawable.podglyadyvayushhij,
            R.drawable.myagkaya_kompoziciya,
            R.drawable.raspyatie
};
    MyActivity myact = new MyActivity();
    //ArrayList<String> listOfNames = new ArrayList<String>();
    List<Integer> mThumbIdsSearch = new ArrayList<Integer>(Arrays.asList(mThumbIds));
    List<Integer> listOfIds = new ArrayList<Integer>();
    LayoutInflater mInflater;
    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
        mInflater = (LayoutInflater)
                c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        //Log.i("myapp", String.valueOf(position));
        //Log.i("myapp", String.valueOf(mThumbIdsSearch.size()));
        if(position < mThumbIdsSearch.size()) {
            imageView.setImageResource(mThumbIdsSearch.get(position)); //mThumbIds[position]);//mThumbIdsSearch.get(position));
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(210, 210));
        return imageView;
    }
    public void fillListOfIds(){
        for (int i = 0; i < mThumbIds.length; i++) {
            listOfIds.add(i);
        }
    }
    public List<Integer> getListOfIds(){
        return listOfIds;
    }
    public void filter(String charText, List<String> listOfNames) {

        charText = charText.toLowerCase(Locale.getDefault());

        List<Integer> searchList = new ArrayList<Integer>(Arrays.asList(mThumbIds));

        if (charText.length() == 0) {
            //mThumbIdsSearch = Arrays.asList(mThumbIds);
            mThumbIdsSearch = new ArrayList<>(searchList);
            fillListOfIds();
        } else {
            searchList.clear();
            //Log.i("myapp", charText);
            for (int i = 0; i < mThumbIds.length && i < listOfNames.size(); i++) {
                //Log.i("myapp", listOfNames.get(i));
                if (charText.length() != 0 && listOfNames.get(i).toLowerCase(Locale.getDefault()).contains(charText)) {
                    Log.i("myapp", listOfNames.get(i));
                    Log.i("myapp", charText);
                    //mThumbIdsSearch.add(mThumbIds[i]);
                    searchList.add(mThumbIds[i]);
                    //listOfIds.add(i);
                }
            }
            mThumbIdsSearch = new ArrayList<>(searchList);
        }
        notifyDataSetChanged();
        }
}