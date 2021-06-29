package com.example.moneywallet.ui.chi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneywallet.R;
import com.example.moneywallet.ui.adapter.ChiViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChiFragment extends Fragment {
    private ViewPager2 mVp;
    private TabLayout mTl;

    public ChiFragment() {
        // Required empty public constructor
    }

    public static ChiFragment newInstance(String param1, String param2) {
        ChiFragment fragment = new ChiFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVp=view.findViewById(R.id.viewPager2);
        mTl=view.findViewById(R.id.tabLayout);

        ChiViewPager2Adapter adapter= new ChiViewPager2Adapter(getActivity());
        mVp.setAdapter(adapter);
        new TabLayoutMediator(mTl, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull @NotNull TabLayout.Tab tab, int position) {
                if (position==0)
                {
                    tab.setIcon(R.drawable.ic_menu_camera);
                    tab.setText("Khoản Chi");
                }
                else
                {
                    tab.setIcon(R.drawable.ic_menu_camera);
                    tab.setText("Loại Chi");
                }
            }
        }).attach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chi, container, false);
    }
}