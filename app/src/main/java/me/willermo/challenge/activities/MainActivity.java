package me.willermo.challenge.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.willermo.challenge.R;
import me.willermo.challenge.fragments.FragmentAppDetail;
import me.willermo.challenge.fragments.FragmentAppList;
import me.willermo.challenge.fragments.FragmentCategories;
import me.willermo.challenge.messages.RecyclerViewClickMessage;
import me.willermo.challenge.messages.StartAppListMessage;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CATEGORY_LIST = "CATEGORY_LIST";
    public static final String APP_LIST = "APP_LIST";

    @BindView(R.id.contentMainFrame)
    FrameLayout frameLayout;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentCategories fragmentCategories = new FragmentCategories();
        MainActivity.this.getSupportFragmentManager().beginTransaction().add(R.id.contentMainFrame,fragmentCategories,CATEGORY_LIST).commit();

        handler = new Handler();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(final RecyclerViewClickMessage recyclerViewClickMessage){


        if(recyclerViewClickMessage.listType==0){

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",recyclerViewClickMessage.id);
                    FragmentAppList fragmentAppList = new FragmentAppList();
                    fragmentAppList.setArguments(bundle);
                    FragmentTransaction ft = MainActivity.this.getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(android.support.v7.appcompat.R.anim.abc_fade_in, android.support.v7.appcompat.R.anim.abc_fade_out);
                    ft.replace(R.id.contentMainFrame,fragmentAppList,APP_LIST).addToBackStack(APP_LIST).commit();
                }
            },300);

        }else{
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",recyclerViewClickMessage.id);
                    FragmentAppDetail fragmentAppDetail = new FragmentAppDetail();
                    fragmentAppDetail.setArguments(bundle);
                    FragmentTransaction ft = MainActivity.this.getSupportFragmentManager().beginTransaction();
                    ft.setCustomAnimations(android.support.v7.appcompat.R.anim.abc_grow_fade_in_from_bottom, android.support.v7.appcompat.R.anim.abc_shrink_fade_out_from_bottom);
                    ft.replace(R.id.contentMainFrame,fragmentAppDetail,APP_LIST).addToBackStack(APP_LIST).commit();

                }
            },300);

        }

    }
}
