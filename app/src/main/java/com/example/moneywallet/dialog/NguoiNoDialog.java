package com.example.moneywallet.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.Nguoino;
import com.example.moneywallet.ui.no.NguoinoFragment;
import com.example.moneywallet.ui.no.NguoinoViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class NguoiNoDialog {

    private NguoinoViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private TextInputEditText etID,etName,etadd,etsdt;
    private boolean mEditMode;

    public NguoiNoDialog(Context context, NguoinoFragment fragment, Nguoino ...nguoino)
    {
        mViewModel=fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view =mLayoutInflater.inflate(R.layout.dialog_nguoi_no,null);
        etID = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etadd = view.findViewById(R.id.etadd);
        etsdt = view.findViewById(R.id.etghichu);
        if(nguoino != null && nguoino.length>0){
            etID.setText(""+nguoino[0].nid);
            etName.setText(nguoino[0].ten);
            etadd.setText(nguoino[0].add);
            etsdt.setText(nguoino[0].sdt);
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
                        Nguoino nn =new Nguoino();

                        nn.ten = etName.getText().toString();
                        nn.sdt= etsdt.getText().toString();
                        nn.add = etadd.getText().toString();
                        if(mEditMode){
                            nn.nid = Integer.parseInt(etID.getText().toString());
                            mViewModel.update(nn);
                        }else {
                            mViewModel.insert(nn);
                            Toast.makeText(context," đã được lưu",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}

