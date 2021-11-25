package com.example.moneywallet.ui.chi;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moneywallet.R;
import com.example.moneywallet.dialog.LoaiChiDetailDialog;
import com.example.moneywallet.dialog.LoaiChiDialog;
import com.example.moneywallet.dialog.LoaiThuDetailDialog;
import com.example.moneywallet.dialog.LoaiThuDialog;
import com.example.moneywallet.entity.LoaiChi;
import com.example.moneywallet.entity.LoaiThu;
import com.example.moneywallet.entity.Thu;
import com.example.moneywallet.ui.adapter.ItemClickListener;
import com.example.moneywallet.ui.adapter.LoaiChiRecyclerViewAdapter;
import com.example.moneywallet.ui.thu.LoaiThuFragment;

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
        final LoaiChiFragment currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = mAdapter.getItem(position);
                LoaiChiDialog dialog = new LoaiChiDialog(getActivity(),currentFragment , loaiChi);
                dialog.show();
            }
        });
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi = mAdapter.getItem(position);
                LoaiChiDetailDialog dialog = new LoaiChiDetailDialog(getActivity(),currentFragment,loaiChi);
                dialog.show();
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {

                        int position = viewHolder.getAdapterPosition();
                         LoaiChi loaiChi= mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Loai chi da duoc xoa", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(loaiChi);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
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