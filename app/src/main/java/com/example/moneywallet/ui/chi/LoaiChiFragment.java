package com.example.moneywallet.ui.chi;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.LoaiChi;
import com.example.moneywallet.ui.adapter.LoaiChiRecyclerViewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoaiChiFragment extends Fragment {
    private RecyclerView mRv;
    private LoaiChiRecyclerViewAdapter mAdapter;
    private LoaiChiViewModel mViewModel;

    public static LoaiChiFragment newInstance() {
        return new LoaiChiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loai_chi_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView2);
        mAdapter = new LoaiChiRecyclerViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiChiViewModel.class);
        mViewModel.getAllLoaiChi().observe(getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });
    }

    public LoaiChiViewModel getViewModel() {
        return mViewModel;
    }
}