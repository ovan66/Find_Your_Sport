package com.bastian.findyousport.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bastian.findyousport.views.details.EventsFragment;
import com.bastian.findyousport.views.details.profile.ProfileFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ProfileFragment.newInstance();
            case 1:
                return EventsFragment.newInstance();
            default:
                return EventsFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Perfil";
            case 1:
                return "Clases";
        }
        return null;
    }
}
