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
import com.example.moneywallet.dialog.LoaiThuDetailDialog;
import com.example.moneywallet.dialog.LoaiThuDialog;
import com.example.moneywallet.dialog.ThuDetailDialog;
import com.example.moneywallet.dialog.ThuDialog;
import com.example.moneywallet.entity.LoaiThu;
import com.example.moneywallet.entity.Thu;
import com.example.moneywallet.ui.adapter.ItemClickListener;
import com.example.moneywallet.ui.adapter.ThuRecyclerviewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhoanThuFragment extends Fragment {

    private KhoanThuViewModel mViewModel;
    private RecyclerView mRv;
    private ThuRecyclerviewAdapter mAdapter;

    public static KhoanThuFragment newInstance() {
        return new KhoanThuFragment();
    }

    public KhoanThuViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoan_thu_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView3);
        mAdapter = new ThuRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final KhoanThuFragment currentFragment = this;
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = mAdapter.getItem(position);
                ThuDetailDialog dialog = new ThuDetailDialog(getActivity(),currentFragment,thu);
                dialog.show();
            }
        });
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu = mAdapter.getItem(position);
                ThuDialog dialog = new ThuDialog(getActivity(),currentFragment , thu);
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
                        Thu thu = mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Khoan Thu da duoc xoa", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(thu);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanThuViewModel.class);
        mViewModel.getAllThu().observe(getActivity(), new Observer<List<Thu>>() {
    @Override
    public void onChanged(List<Thu> thus) {
        mAdapter.setList(thus);
    }
});


    }

}