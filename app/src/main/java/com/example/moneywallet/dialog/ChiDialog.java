package com.example.moneywallet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.Chi;
import com.example.moneywallet.entity.LoaiChi;
import com.example.moneywallet.ui.adapter.LoaiChiSpinnerAdapter;
import com.example.moneywallet.ui.chi.KhoanChiFragment;
import com.example.moneywallet.ui.chi.KhoanChiViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ChiDialog {
    private KhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etIDlc,etNamelc,etAmount,etNote;
    private Spinner spType;
    private boolean mEditMode;
    private LoaiChiSpinnerAdapter mAdapter;


    public ChiDialog(final Context context, KhoanChiFragment fragment, Chi...chi) {
        mViewModel=fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view =mLayoutInflater.inflate(R.layout.dialog_chi,null);
        etIDlc = view.findViewById(R.id.etIDlc);
        etNamelc = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        etNote = view.findViewById(R.id.etNote);
        spType = view.findViewById(R.id.spType2);
        mAdapter = new LoaiChiSpinnerAdapter(fragment.getActivity());
        mViewModel.getAllLoaiChi().observe(fragment.getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });
        spType.setAdapter(mAdapter);

        if(chi != null && chi.length>0){
            etIDlc.setText(""+chi[0].cid);
            etNamelc.setText(chi[0].ten1);
            etNote.setText(chi[0].ghichu);
            etAmount.setText(""+chi[0].sotien1);
            mEditMode = true;

        }else {
            mEditMode = false;
        }
        AlertDialog.Builder builder =new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Chi chi = new Chi();
                        chi.ten1 = etNamelc.getText().toString();
                        chi.sotien1= Float.parseFloat(etAmount.getText().toString());
                        chi.ghichu = etNote.getText().toString();
                        chi.lcid= ((LoaiChi)mAdapter.getItem(spType.getSelectedItemPosition())).cid;
                        if (mEditMode) {
                            chi.cid = Integer.parseInt(etIDlc.getText().toString());
                            mViewModel.update(chi);
                        } else {
                            mViewModel.insert(chi);
                            Toast.makeText(context, "Khoản chi đã được lưu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}
