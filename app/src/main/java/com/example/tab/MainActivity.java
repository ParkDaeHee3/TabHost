package com.example.tab;
//import android.widget.TabHost;
//import android.widget.TabHost.TabSpec;
//import android.app.TabActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.widget.LinearLayout;


/*
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabSpec tabSpecSong = tabHost.newTabSpec("SONG").setIndicator("음악별");
        tabSpecSong.setContent(R.id.tabSong);
        tabHost.addTab(tabSpecSong);

        TabSpec tabSpecArtist = tabHost.newTabSpec("ARTIST").setIndicator("가수별");
        tabSpecArtist.setContent(R.id.tabArtist);
        tabHost.addTab(tabSpecArtist);

        TabSpec tabSpecAlbum = tabHost.newTabSpec("ALBUM").setIndicator("앨범별");
        tabSpecAlbum.setContent(R.id.tabAlbum);
        tabHost.addTab(tabSpecAlbum);

        tabHost.setCurrentTab(0);


    }
}
*/

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    ActionBar.Tab tabSong, tabArtist, tabAlbum;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tabSong = bar.newTab();
        tabSong.setText("음악별");
        tabSong.setTabListener(this);
        bar.addTab(tabSong);

        tabArtist = bar.newTab();
        tabArtist.setText("가수별");
        tabArtist.setTabListener(this);
        bar.addTab(tabArtist);

        tabAlbum = bar.newTab();
        tabAlbum.setText("앨범별");
        tabAlbum.setTabListener(this);
        bar.addTab(tabAlbum);
    }
    MyTabFragment myFrags[] = new MyTabFragment[3];
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft){
        MyTabFragment myTabFrag = null;
        if (myFrags[tab.getPosition()]==null){
            myTabFrag = new MyTabFragment();
            Bundle data = new Bundle();
            data.putString("tabName",tab.getText().toString());
            myTabFrag.setArguments(data);
            myFrags[tab.getPosition()] = myTabFrag;
        }else
            myTabFrag = myFrags[tab.getPosition()];

        ft.replace(android.R.id.content, myTabFrag);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft){
    }
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft){
    }

    public static class MyTabFragment extends androidx.fragment.app.Fragment{
        String tabName;
        //private  MainActivity mActivity;

        @Override
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            Bundle data = getArguments();
            tabName = data.getString("tabName");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            LinearLayout baseLayout = new LinearLayout(super.getActivity());
            baseLayout.setOrientation(LinearLayout.VERTICAL);
            baseLayout.setLayoutParams(params);
            if(tabName == "음악별")
                baseLayout.setBackgroundColor(Color.RED);
            if(tabName == "가수별")
                baseLayout.setBackgroundColor(Color.BLUE);
            if(tabName == "앨범별")
                baseLayout.setBackgroundColor(Color.GREEN);

            return baseLayout;
        }
    }
}

