package com.flc.bankpda.base.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.flc.bankpda.base.BaseMvpFragment;

import java.util.List;

public class CommonPagerAdapter extends FragmentPagerAdapter {
    private List<BaseMvpFragment> mFragments;

    public CommonPagerAdapter(FragmentManager fm, List<BaseMvpFragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (mFragments != null) {
            ret = mFragments.size();
        }
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getFragmentTitle();
    }
}
