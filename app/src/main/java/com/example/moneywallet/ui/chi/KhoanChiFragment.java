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
import com.example.moneywallet.dialog.ChiDetailDialog;
import com.example.moneywallet.dialog.ChiDialog;
import com.example.moneywallet.dialog.ThuDetailDialog;
import com.example.moneywallet.dialog.ThuDialog;
import com.example.moneywallet.entity.Chi;
import com.example.moneywallet.entity.Thu;
import com.example.moneywallet.ui.adapter.ChiRecyclerViewAdapter;
import com.example.moneywallet.ui.adapter.ItemClickListener;
import com.example.moneywallet.ui.adapter.ThuRecyclerviewAdapter;
import com.example.moneywallet.ui.thu.KhoanThuFragment;
import com.example.moneywallet.ui.thu.KhoanThuViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhoanChiFragment extends Fragment {

    private KhoanChiViewModel mViewModel;
    private RecyclerView mRv;
    private ChiRecyclerViewAdapter mAdapter;

    public static KhoanChiFragment newInstance() {
        return new KhoanChiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoan_chi_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new ChiRecyclerViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final KhoanChiFragment currentFragment = this;
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi chi = mAdapter.getItem(position);
                ChiDetailDialog dialog = new ChiDetailDialog(getActivity(),currentFragment,chi);
                dialog.show();
            }
        });
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi chi = mAdapter.getItem(position);
                ChiDialog dialog = new ChiDialog(getActivity(),currentFragment ,chi);
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
                        Chi chi = mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Khoản chi da duoc xoa", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(chi);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanChiViewModel.class);
        mViewModel.getAllChi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> chis) {
                mAdapter.setList(chis);
            }
        });
    }
    public KhoanChiViewModel getViewModel() {
        return mViewModel;
    }
}