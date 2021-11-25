package com.example.moneywallet.ui.thu;

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
import com.example.moneywallet.dialog.LoaiChiDialog;
import com.example.moneywallet.dialog.LoaiThuDetailDialog;
import com.example.moneywallet.dialog.LoaiThuDialog;
import com.example.moneywallet.entity.LoaiThu;
import com.example.moneywallet.ui.adapter.ItemClickListener;
import com.example.moneywallet.ui.adapter.LoaiThuRecyclerviewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoaiThuFragment extends Fragment {
    private RecyclerView mRv;
    private LoaiThuRecyclerviewAdapter mAdapter;
    private LoaiThuViewModel mViewModel;

    public static LoaiThuFragment newInstance() {
        return new LoaiThuFragment();
    }

    public LoaiThuViewModel getViewModel()
    {
        return mViewModel;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loai_thu_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new LoaiThuRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final LoaiThuFragment currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = mAdapter.getItem(position);
                LoaiThuDialog dialog = new LoaiThuDialog(getActivity(),currentFragment , loaiThu);
                dialog.show();
            }
        });

        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu = mAdapter.getItem(position);
                LoaiThuDetailDialog dialog = new LoaiThuDetailDialog(getActivity(),currentFragment,loaiThu);
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
                        LoaiThu lt = mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Loai Thu da duoc xoa", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(lt);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoaiThuViewModel.class);
        mViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
    }

}