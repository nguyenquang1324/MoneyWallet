package com.example.moneywallet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.Nguoino;
import com.example.moneywallet.ui.no.NguoinoFragment;
import com.example.moneywallet.ui.no.NguoinoViewModel;


public class NguoiNoDetailDialog {

    private NguoinoViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private TextView tvId,tvName,tvadd,tvsdt;


    public NguoiNoDetailDialog(Context context, NguoinoFragment fragment, Nguoino nguoino)
    {
        mViewModel=fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view =mLayoutInflater.inflate(R.layout.dialog_detail_nguoi_no,null);
        tvId = view.findViewById(R.id.tvId);
        tvName = view.findViewById(R.id.tvName);
        tvadd = view.findViewById(R.id.tvadd);
        tvsdt = view.findViewById(R.id.tvsdt);
        tvId.setText(""+nguoino.nid);
        tvName.setText(nguoino.ten);
        tvadd.setText(nguoino.add);
        tvsdt.setText(nguoino.sdt);
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

