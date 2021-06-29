package com.example.moneywallet.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.moneywallet.ui.chi.KhoanChiFragment;
import com.example.moneywallet.ui.chi.LoaiChiFragment;
import com.example.moneywallet.ui.thu.KhoanThuFragment;
import com.example.moneywallet.ui.thu.LoaiThuFragment;

import org.jetbrains.annotations.NotNull;

public class ThuViewPager2Adapter extends FragmentStateAdapter {
    public ThuViewPager2Adapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position==0)
        {
            fragment= KhoanThuFragment.newInstance();
        }
        else
        {
            fragment= LoaiThuFragment.newInstance();
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
