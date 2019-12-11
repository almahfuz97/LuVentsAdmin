package com.example.myapplication.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.Fragments.Tab1;
import com.example.myapplication.Fragments.Tab2;

public class PagerAdapter extends FragmentPagerAdapter {
    int mNoOfTabs;
    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.mNoOfTabs=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Tab1 tab1=new Tab1();
                return tab1;

            case 1:
                Tab2 tab2=new Tab2();
                return  tab2;

                default:
                    return null;

        }

    }

    @Override
    public int getCount() {

        return mNoOfTabs;
    }
}
