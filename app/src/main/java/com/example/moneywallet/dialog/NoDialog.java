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
import com.example.moneywallet.entity.Nguoino;
import com.example.moneywallet.entity.No;
import com.example.moneywallet.ui.adapter.NospinnerAdapter;
import com.example.moneywallet.ui.no.KhoanNoFragment;
import com.example.moneywallet.ui.no.KhoanNoViewModel;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class NoDialog {
    private KhoanNoViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private TextInputEditText etID,etName,etsotien,etghichu;
    private boolean mEditMode;
    private Spinner spType2;
    private NospinnerAdapter mAdapter;

    public NoDialog( final Context context, KhoanNoFragment fragment, No...no)
    {
        mViewModel=fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view =mLayoutInflater.inflate(R.layout.dialog_no,null);
        etID = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etghichu = view.findViewById(R.id.etghichu);
        etsotien = view.findViewById(R.id.etsotien);
        spType2 = view.findViewById(R.id.spType2);
        mAdapter = new NospinnerAdapter(fragment.getActivity());
        mViewModel.getmAllNguoino().observe(fragment.getActivity(), new Observer<List<Nguoino>>() {
            @Override
            public void onChanged(List<Nguoino> nguoinos) {
                mAdapter.setList(nguoinos);
            }
        });
        spType2.setAdapter(mAdapter);
        if(no != null && no.length>0){
            etID.setText(""+no[0].oid);
            etName.setText(no[0].ten);
            etsotien.setText(""+no[0].sotien);
            etghichu.setText(no[0].ghichu);
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
                        No n =new No();
                        n.ten = etName.getText().toString();
                        n.sotien=Float.parseFloat(etsotien.getText().toString());
                        n.ghichu = etghichu.getText().toString();
                        n.loid= ((Nguoino)mAdapter.getItem(spType2.getSelectedItemPosition())).nid;
                        if(mEditMode){
                            n.oid = Integer.parseInt(etID.getText().toString());
                            mViewModel.update(n);
                        }else {
                            mViewModel.insert(n);
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

