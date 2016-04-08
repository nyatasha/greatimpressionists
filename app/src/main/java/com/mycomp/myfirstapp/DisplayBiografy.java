package com.mycomp.myfirstapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;


public class DisplayBiografy extends Activity {
    String biogr="";
    public final static String qwe="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biografy_dali);

        final ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7e0d17")));
        actionBar.setIcon(R.drawable.ic_launcher);

        Intent intent = getIntent();

        biogr = intent.getStringExtra(MyActivity.Biografia);

        ImageView img = (ImageView) findViewById(R.id.image1);
        img.setImageResource(R.drawable.portret_dali);

        String tmp = "";
        TextView ttt = (TextView) findViewById(R.id.text11);

        String elemtext = null;

        ImageView info = (ImageView) findViewById(R.id.moreinfo);
        info.setImageResource(R.drawable.more_info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.salvador-dali.org/en_index/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        try {
            XmlPullParser parser = getResources().getXml(R.xml.dali);

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG) {

                    String elemName = parser.getName();
                    if (elemName.equals("picture")) {
                        tmp = parser.getAttributeValue(null,
                                "id");
                    }
                    if (elemName.equals("name")) {
                        elemtext = "name";
                    }
                    if (elemName.equals("description")) {
                        elemtext = "description";
                    }
                } else if (parser.getEventType() == XmlPullParser.TEXT) {
                    if (tmp.equals(biogr)) {
                        if (elemtext.equals("description")) {
                            ttt.setText(parser.getText());
                        }
                    }
                }
                parser.next();

            }
        } catch (Throwable e) {
            Toast.makeText(this, "Ошибка при загрузке XML-документа: " + e.toString(), 4000).show();
        }
    }

}
