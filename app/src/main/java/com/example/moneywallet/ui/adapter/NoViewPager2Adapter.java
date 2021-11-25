package com.example.moneywallet.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.moneywallet.ui.no.KhoanNoFragment;
import com.example.moneywallet.ui.no.NguoinoFragment;


import org.jetbrains.annotations.NotNull;

public class NoViewPager2Adapter extends FragmentStateAdapter {
    public NoViewPager2Adapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position==0)
        {
            fragment= KhoanNoFragment.newInstance();
        }
        else
        {
            fragment= NguoinoFragment.newInstance();
        }

        return fragment;
    }


    @Override
    public int getItemCount() {
        return 2;
    }
}
