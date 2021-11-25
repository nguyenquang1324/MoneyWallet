package com.example.moneywallet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.Nguoino;
import com.example.moneywallet.entity.No;
import com.example.moneywallet.ui.adapter.NospinnerAdapter;
import com.example.moneywallet.ui.no.KhoanNoFragment;
import com.example.moneywallet.ui.no.KhoanNoViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class NoDetailDialog {

    private KhoanNoViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private TextView tvID,tvName,tvsotien,tvghichu;

    public NoDetailDialog(final Context context, KhoanNoFragment fragment, No no)
    {
        mViewModel=fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view =mLayoutInflater.inflate(R.layout.dialog_detail_no,null);
        tvID = view.findViewById(R.id.textView19);
        tvName = view.findViewById(R.id.textView20);
        tvghichu = view.findViewById(R.id.textView25);
        tvsotien = view.findViewById(R.id.textView23);
        tvID.setText(""+no.oid);
        tvName.setText(no.ten);
        tvghichu.setText(no.ghichu);
        tvsotien.setText(""+no.sotien);
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

