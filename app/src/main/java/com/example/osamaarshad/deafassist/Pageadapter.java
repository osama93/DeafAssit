package com.example.osamaarshad.deafassist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by OsamaArshad on 19-Feb-17.
 */

public class Pageadapter extends FragmentStatePagerAdapter {

    String[] names;
    public Pageadapter(FragmentManager fragmentManager, int tabs, String[] names){
        super(fragmentManager);
        this.names=names;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0:
                return new speecgTotext();
            case 1:
                return new textTospeech();
        }
        return null;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names[position];
    }

    @Override
    public int getCount() {
        return names.length;
    }
}
