package com.example.moneywallet.ui.no;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
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
import com.example.moneywallet.dialog.NguoiNoDetailDialog;
import com.example.moneywallet.dialog.NguoiNoDialog;
import com.example.moneywallet.entity.LoaiChi;
import com.example.moneywallet.entity.Nguoino;

import com.example.moneywallet.ui.adapter.ItemClickListener;
import com.example.moneywallet.ui.adapter.NguoiNoRecyclerViewAdapter;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NguoinoFragment extends Fragment {

    private RecyclerView mRv;
    private NguoiNoRecyclerViewAdapter mAdapter;


    private NguoinoViewModel mViewModel;

    public static NguoinoFragment newInstance() {
        return new NguoinoFragment();
    }

    public NguoinoViewModel getViewModel(){
        return mViewModel;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nguoino_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new NguoiNoRecyclerViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final NguoinoFragment currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Nguoino nguoino = mAdapter.getItem(position);
                NguoiNoDialog dialog = new NguoiNoDialog(getActivity(),currentFragment , nguoino);
                dialog.show();
            }
        });
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Nguoino nguoino = mAdapter.getItem(position);
                NguoiNoDetailDialog dialog = new NguoiNoDetailDialog(getActivity(),currentFragment,nguoino);
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
                        Nguoino nguoino= mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Nguoi no da duoc xoa", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(nguoino);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NguoinoViewModel.class);
        mViewModel.getAllNguoiNo().observe(getActivity(), new Observer<List<Nguoino>>() {
            @Override
            public void onChanged(List<Nguoino> nguoinos) {
                mAdapter.setList(nguoinos);
            }
        });
    }

}