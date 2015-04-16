package com.seanhu.android_misc;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.tapas.android.FavoritesFragment;import com.tapas.android.HomeFragment;import com.tapas.android.MeFragment;import com.tapas.android.TrendingFragment;import com.yicai.spirit.R;import java.lang.Override;


public class FooterNavActivity extends ActionBarActivity {
    ImageButton navHome;
    ImageButton navTrending;
    ImageButton navStars;
    ImageButton navMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer_nav);

        navHome      = (ImageButton) findViewById(R.id.navHome);
        navTrending  = (ImageButton) findViewById(R.id.navTrending);
        navStars     = (ImageButton) findViewById(R.id.navStars);
        navMe        = (ImageButton) findViewById(R.id.navMe);

        navHome.setOnClickListener(mNavListener);
        navTrending.setOnClickListener(mNavListener);
        navStars.setOnClickListener(mNavListener);
        navMe.setOnClickListener(mNavListener);

        select(0);
    }

    View.OnClickListener mNavListener = new View.OnClickListener() {
        public void onClick(View v) {
            int index = 0;
            if (v == navHome) {
                index = 0;
            } else if (v == navTrending) {
                index = 1;
            } else if (v == navStars) {
                index = 2;
            } else if (v == navMe) {
                index = 3;
            }
            onNavigationSelect(index);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void onNavigationSelect(int i) {
        int homeRes     = i == 0 ? R.drawable.ic_menu_inverted_light_blue_home      : R.drawable.ic_menu_home;
        int trendingRes = i == 1 ? R.drawable.ic_menu_inverted_light_blue_compass   : R.drawable.ic_menu_compass;
        int starRes     = i == 2 ? R.drawable.ic_menu_inverted_light_blue_star      : R.drawable.ic_menu_star;
        int meRes       = i == 3 ? R.drawable.ic_menu_inverted_light_blue_myplaces  : R.drawable.ic_menu_myplaces;

        //Button states
        navHome.setImageResource(homeRes);
        navTrending.setImageResource(trendingRes);
        navStars.setImageResource(starRes);
        navMe.setImageResource(meRes);

        //translate fragment
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (i) {
            case 0:
                transaction.replace(R.id.content, new HomeFragment()).commit();
                break;
            case 1:
                transaction.replace(R.id.content, new TrendingFragment()).commit();
                break;
            case 2:
                transaction.replace(R.id.content, new FavoritesFragment()).commit();
                break;
            case 3:
                transaction.replace(R.id.content, new MeFragment()).commit();
                break;
        }
    }

    public void select(int i) {
        onNavigationSelect(i);
    }

    public interface NavigationListener {
        void onSelect(int index);
    }
}
