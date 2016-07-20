package com.mycomp.myfirstapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;


public class DisplayDali extends Activity {
    public final static String EXTRA_ID = "com.mycomp.myfirstapp.MESSAGE";
    //public final static int EXTRA_ID = 1;
    String myID = "";
    Integer ID_int;
    String biografia = "";
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
        ImageAdapter imad = new ImageAdapter(this);
        img.setImageResource(imad.getItem(ID_int));
        img.setClickable(true);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                int position = ID_int;
                // passing array index
                i.putExtra("Dali", position);
                startActivity(i);
            }
        });

        String tmp = "";
        TextView title = (TextView) findViewById(R.id.text1);
        TextView ttt = (TextView) findViewById(R.id.text11);

        String elemtext = null;
        ArrayList<String> list = new ArrayList<String>();
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
                    if (tmp.equals(myID)) {
                        if (elemtext.equals("name")) {
                            title.setText(parser.getText());
                            list.add(parser.getText());
                            Log.i("myapp", parser.getText());
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
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //      android.R.layout.simple_list_item
        //lvInfo.setAdapter(adapter);

    }
    /*public void Parsing(){

        ArrayList<String> list = new Ar_1, list);
rayList<String>();
        try
        {
            XmlPullParser parser = getResources().getXml(R.xml.dali);

            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG
                        && parser.getName().equals("contact")) {
                    list.add(parser.getAttributeValue(0) + " "
                            + parser.getAttributeValue(1) + "\n"
                            + parser.getAttributeValue(2));
                }
                parser.next();
            }
        }

        catch(Throwable t)
        {
            Toast.makeText(this, "Ошибка при загрузке XML-документа: " + t.toString(), 4000).show();
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
    }*/
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_message, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            Toast.makeText(this, "action_fav selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_search) {
            Toast.makeText(this, "action_search selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}
