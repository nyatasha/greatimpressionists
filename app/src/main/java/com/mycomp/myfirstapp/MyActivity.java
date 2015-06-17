package com.mycomp.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuInflater;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class MyActivity extends FragmentActivity { //FragmentActivity implements ActionBar.TabListener {
    public final static String EXTRA_MESSAGE = "com.mycomp.myfirstapp.MESSAGE";
    public final static String Biografia = "com.mycomp.myfirstapp.MESSAGE";
    PagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final ActionBar actionBar = getActionBar();

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.ic_launcher);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7e0d17")));

        ////TABS//////
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);


        // Assiging the Sliding Tab Layout View
        SlidingTabLayout tabs = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        //tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.color1);
            }
            @Override
            public int getDividerColor(int position) {
                return getResources().getColor(R.color.color1);
            }
        });
        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(mViewPager);

    }

    public static class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    // The first section of the app is the most interesting -- it offers
                    // a launchpad into the other demonstrations in this example application.
                    return new LaunchpadSectionFragment();
                case 1:
                    return new MagrittSectionFragment();
                default:
                    // The other sections of the app are dummy placeholders.
                    Fragment fragment = new DummySectionFragment();
                    Bundle args = new Bundle();
                    args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
                    fragment.setArguments(args);
                    return fragment;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "Сальвадор Дали";
                case 1:
                    return "Рене Магритт";
                default:
                    return "Избранные";
            }
        }
    }

    public static class LaunchpadSectionFragment extends Fragment {
        private FragmentActivity fa;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.grid_layout, container, false);
            ImageView img_biogr = (ImageView) v.findViewById(R.id.biografia);
            img_biogr.setImageResource(R.drawable.dali_biograf);
            img_biogr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((MyActivity) getActivity()).ShowBioDali(v);
                }
            });

            GridView gridView = (GridView) v.findViewById(R.id.grid_view);

            // Instance of ImageAdapter Class
            gridView.setAdapter(new ImageAdapter(getActivity()));
            gridView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    ((MyActivity) getActivity()).ShowDali(v, position);
                }
            });
            return v;
            //View rootView = inflater.inflate(R.layout.grid_layout, container, false);

            /* Demonstration of a collection-browsing activity.
            rootView.findViewById(R.id.demo_collection_button)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), CollectionDemoActivity.class);
                            startActivity(intent);
                        }
                    });

            // Demonstration of navigating to external activities.
            rootView.findViewById(R.id.demo_external_activity)
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Create an intent that asks the user to pick a photo, but using
                            // FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, ensures that relaunching
                            // the application from the device home screen does not return
                            // to the external activity.
                            Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
                            externalActivityIntent.setType("image/*");
                            externalActivityIntent.addFlags(
                                    Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            startActivity(externalActivityIntent);
                        }
                    });*/

            //return rootView;
     }
    }

    public static class MagrittSectionFragment extends Fragment {
        private FragmentActivity fa;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.grid_layout, container, false);

            ImageView img_biogr = (ImageView) v.findViewById(R.id.biografia);
            img_biogr.setImageResource(R.drawable.magritt_biograf);
            img_biogr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((MyActivity) getActivity()).ShowBioMagritt(v);
                }
            });

            GridView gridView = (GridView) v.findViewById(R.id.grid_view);

            // Instance of ImageAdapter Class
            gridView.setAdapter(new ImageAdapterMagritt(getActivity()));
            gridView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    ((MyActivity) getActivity()).ShowMagritt(v, position);
                }
            });
            return v;
        }
    }

    public static class DummySectionFragment extends Fragment {

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_dummy, container, false);
            TextView text = (TextView) rootView.findViewById(R.id.text1);
            text.setText("Здесь будут избранные картины.");
            return rootView;
        }
    }

    public void ShowDali(View v, Integer id){
        // Говорим между какими Activity будет происходить связь
        Intent intent = new Intent(this, DisplayDali.class);
        //ImageView iv = (ImageView)v;
        //String tmp = iv.getTag().toString();
        //String tmp = v.getResources().getResourceName(v.getId());
        String tmp = id.toString();
        intent.putExtra(EXTRA_MESSAGE, tmp);
        // показываем новое Activity
        startActivity(intent);
    }
    public void ShowMagritt(View v, Integer id){
        Intent intent = new Intent(this, DisplayMagritt.class);
        String tmp = id.toString();
        intent.putExtra(EXTRA_MESSAGE, tmp);
        startActivity(intent);
    }
    public void ShowBioDali(View v){
        Intent intent = new Intent(this, DisplayBiografy.class);
        ImageView iv = (ImageView)v;
        String tmp = iv.getTag().toString();
        intent.putExtra(Biografia, tmp);
        startActivity(intent);
    }
    public void ShowBioMagritt(View v){
        Intent intent = new Intent(this, DisplayBiografyMagritt.class);
        ImageView iv = (ImageView)v;
        String tmp = iv.getTag().toString();
        intent.putExtra(Biografia, tmp);
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "action_search selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
