package com.example.moneywallet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.Chi;
import com.example.moneywallet.ui.chi.KhoanChiFragment;
import com.example.moneywallet.ui.chi.KhoanChiViewModel;

public class ChiDetailDialog {
    private KhoanChiViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvID,tvName,tvAmount,tvNote;
    private Spinner spType;
    private boolean mEditMode;



    public ChiDetailDialog(final Context context, KhoanChiFragment fragment, Chi chi) {
        mViewModel=fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view =mLayoutInflater.inflate(R.layout.dialog_detail_khoan_chi,null);
        tvID = view.findViewById(R.id.tvID);
        tvName = view.findViewById(R.id.tvName);
        tvAmount = view.findViewById(R.id.tvAmount1);
        tvNote = view.findViewById(R.id.tvAmount2);
            tvID.setText(""+chi.cid);
            tvName.setText(chi.ten1);
            tvNote.setText(chi.ghichu);
            tvAmount.setText(""+chi.sotien1);

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
