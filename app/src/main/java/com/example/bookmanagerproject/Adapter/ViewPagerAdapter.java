package com.example.bookmanagerproject.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bookmanagerproject.Fragment.SplashFragment;
import com.example.bookmanagerproject.Fragment.SplashFragment1;
import com.example.bookmanagerproject.Fragment.SplashFragment2;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SplashFragment();
            case 1:
                return new SplashFragment1();
            case 2:
                return new SplashFragment2();
            default:
                return new SplashFragment();
        }
    }

    @Override
    public int getCount() { // trả về số lượng trang giới thiệu
        return 3;
    }
}
