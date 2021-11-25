package com.example.moneywallet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.LoaiChi;

import com.example.moneywallet.ui.chi.LoaiChiFragment;
import com.example.moneywallet.ui.chi.LoaiChiViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiChiDialog {
    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextInputEditText etIDlc,etNamelc;
    private boolean mEditMode;

    public LoaiChiDialog(Context context, LoaiChiFragment fragment, LoaiChi ...loaiChi) {
        mViewModel=fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view =mLayoutInflater.inflate(R.layout.dialog_loai_chi,null);
        etIDlc = view.findViewById(R.id.etIDlc);
        etNamelc = view.findViewById(R.id.etName);
        if(loaiChi != null && loaiChi.length>0){
            etIDlc.setText(""+loaiChi[0].cid);
            etNamelc.setText(loaiChi[0].ten1);
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
                        LoaiChi lc = new LoaiChi();
                        lc.ten1 = etNamelc.getText().toString();
                        if (mEditMode) {
                            lc.cid = Integer.parseInt(etIDlc.getText().toString());
                            mViewModel.update(lc);
                        } else {
                            mViewModel.insert(lc);
                            Toast.makeText(context, "Loại chi đã được lưu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}
