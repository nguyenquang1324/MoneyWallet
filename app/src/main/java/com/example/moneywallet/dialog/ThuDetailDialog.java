package com.example.moneywallet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.moneywallet.R;
import com.example.moneywallet.entity.Thu;
import com.example.moneywallet.ui.thu.KhoanThuFragment;
import com.example.moneywallet.ui.thu.KhoanThuViewModel;

public class ThuDetailDialog {
    private KhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;

    private TextView tvID,tvName,tvAmount,tvNote;

    public ThuDetailDialog(Context context, KhoanThuFragment fragment, Thu thu) {
        mViewModel=fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view =mLayoutInflater.inflate(R.layout.dialog_detail_khoan_thu,null);
        tvID = view.findViewById(R.id.tvID);
        tvName = view.findViewById(R.id.tvName);
        tvAmount = view.findViewById(R.id.tvAmount3);
        tvNote = view.findViewById(R.id.tvNote3);
            tvID.setText(""+thu.tid);
            tvName.setText(thu.ten);
            tvAmount.setText(""+thu.sotien);
            tvNote.setText(thu.ghichu);

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
