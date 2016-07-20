package com.mycomp.myfirstapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import java.util.ArrayList;


public class DisplayMagritt extends Activity {
    public final static String EXTRA_ID = "com.mycomp.myfirstapp.MESSAGE";
    String myID = "";
    Integer ID_int;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        final ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7e0d17")));
        actionBar.setIcon(R.drawable.ic_launcher);

        Intent intent = getIntent();
        myID = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);
        ID_int = Integer.valueOf(myID);

        ImageView img = (ImageView) findViewById(R.id.image1);
        ImageAdapterMagritt imad = new ImageAdapterMagritt(this);
        img.setImageResource(imad.getItem(ID_int));
        img.setClickable(true);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivityMagritt.class);
                int position = ID_int;
                // passing array index
                i.putExtra("Magritt", position);
                startActivity(i);
            }
        });

        String tmp = "";
        TextView title = (TextView) findViewById(R.id.text1);
        TextView ttt = (TextView) findViewById(R.id.text11);

        String elemtext = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            XmlPullParser parser = getResources().getXml(R.xml.magritt);

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
                    if (tmp.equals(myID)) {
                        if (elemtext.equals("name")) {
                            title.setText(parser.getText());
                            list.add(parser.getText());
                        } else if (elemtext.equals("description")) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_message, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }
}
