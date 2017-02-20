package com.example.osamaarshad.deafassist;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class NavActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;

    private NavigationView nvDrawer;
    private Toolbar toolbar;

    private Fragment fragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("bundle");

        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });
       mDrawer=(DrawerLayout) findViewById(R.id.drawer_layout);
       // mToggle=new ActionBarDrawerToggle(this,mDrawer,R.string.open,R.string.close);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

//        View header = nvDrawer.getHeaderView(0);
//        TextView name = (TextView) header.findViewById(R.id.Navname);
//        TextView blood=(TextView) header.findViewById(R.id.Navsub);
//        name.setText(bundle.get("name").toString());
//        blood.setText(bundle.get("blood").toString());
        fragment=new FragmentViewpager();
        getSupportFragmentManager().beginTransaction().add(R.id.flContent,fragment).commit();
    }


    private void setupDrawerContent(NavigationView nvDrawer) {
        nvDrawer.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragment=new ProfileFragment();
                break;
            case R.id.nav_second_fragment:

                fragment=new FragmentViewpager();
                break;
            case R.id.nav_third_fragment:
                fragment=new CallRecordsFragment();
                break;
            case R.id.nav_forth_fragment:
                fragment=new FinalLoginFragment();
                break;
            case R.id.nav_fifth_fragment:
                fragment=new DemonstrationFragment();
                break;

        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack("").commit();
        toolbar.setTitle(menuItem.getTitle());
        menuItem.setChecked(true);
        mDrawer.closeDrawers();

    }
}
