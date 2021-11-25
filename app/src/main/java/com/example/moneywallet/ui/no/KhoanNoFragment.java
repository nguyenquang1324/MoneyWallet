package com.example.moneywallet.ui.no;

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
import com.example.moneywallet.dialog.NoDetailDialog;
import com.example.moneywallet.dialog.NoDialog;
import com.example.moneywallet.dialog.ThuDetailDialog;
import com.example.moneywallet.entity.No;
import com.example.moneywallet.entity.Thu;
import com.example.moneywallet.ui.adapter.ItemClickListener;
import com.example.moneywallet.ui.adapter.NoRecyclerViewAdapter;
import com.example.moneywallet.ui.adapter.ThuRecyclerviewAdapter;
import com.example.moneywallet.ui.thu.KhoanThuFragment;
import com.example.moneywallet.ui.thu.KhoanThuViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class KhoanNoFragment extends Fragment {

    private RecyclerView mRv;
    private NoRecyclerViewAdapter mAdapter;
    private KhoanNoViewModel mViewModel;

    public static KhoanNoFragment newInstance() {
        return new KhoanNoFragment();
    }

    public KhoanNoViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.khoan_no_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        mAdapter = new NoRecyclerViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        final KhoanNoFragment currentFragment = this;
        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                No no = mAdapter.getItem(position);
                NoDialog dialog = new NoDialog(getActivity(),currentFragment,no);
                dialog.show();
            }
        });
        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                No no = mAdapter.getItem(position);
                NoDetailDialog dialog = new NoDetailDialog(getActivity(),currentFragment,no);
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
                      No no = mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Khoản nợ da duoc xoa", Toast.LENGTH_SHORT).show();
                        mViewModel.delete(no);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KhoanNoViewModel.class);
        mViewModel.getAllNo().observe(getActivity(), new Observer<List<No>>() {
            @Override
            public void onChanged(List<No> nos) {
                mAdapter.setList(nos);
            }
        });
    }

}