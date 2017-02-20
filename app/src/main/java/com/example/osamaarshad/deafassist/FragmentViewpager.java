package com.example.osamaarshad.deafassist;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by OsamaArshad on 19-Feb-17.
 */

public class FragmentViewpager extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.viewpager,container,false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewPager=(ViewPager)getView().findViewById(R.id.viewPager);
        tabLayout = (TabLayout)getView().findViewById(R.id.tab_layout);

        String[] names={"Speech-to-Text", "Text-to-Speech"};
        PagerAdapter pagerAdapter=new Pageadapter(getFragmentManager(),2,names);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }
}
