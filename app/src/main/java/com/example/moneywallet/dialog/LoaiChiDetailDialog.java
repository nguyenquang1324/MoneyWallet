package com.example.moneywallet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.LoaiChi;
import com.example.moneywallet.ui.chi.LoaiChiFragment;
import com.example.moneywallet.ui.chi.LoaiChiViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiChiDetailDialog {
    private LoaiChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvId,tvName;
    public LoaiChiDetailDialog(Context context, LoaiChiFragment fragment, LoaiChi ...loaiChi) {
        mViewModel=fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view =mLayoutInflater.inflate(R.layout.dialog_detail_loai_chi,null);
        tvId= view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvName);
            tvId.setText(""+loaiChi[0].cid);
            tvName.setText(loaiChi[0].ten1);

        AlertDialog.Builder builder =new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog.dismiss();
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}
