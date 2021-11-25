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
import com.example.moneywallet.entity.LoaiThu;
import com.example.moneywallet.entity.Thu;
import com.example.moneywallet.ui.adapter.LoaiThuSpinnerAdapter;
import com.example.moneywallet.ui.thu.KhoanThuViewModel;
import com.example.moneywallet.ui.thu.KhoanThuFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ThuDialog {
    private KhoanThuViewModel mViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mDialog;
    private Spinner spType;
    private TextInputEditText etID,etName,etAmount,etNote;
    private boolean mEditMode;
    private LoaiThuSpinnerAdapter mAdapter;

    public ThuDialog(final Context context, KhoanThuFragment fragment, Thu ...thu) {
        mViewModel=fragment.getViewModel();
        mLayoutInflater = LayoutInflater.from(context);
        View view =mLayoutInflater.inflate(R.layout.dialog_thu,null);
        etID = view.findViewById(R.id.etId);
        etName = view.findViewById(R.id.etName);
        etAmount = view.findViewById(R.id.etAmount);
        etNote = view.findViewById(R.id.etNote);
        mAdapter = new LoaiThuSpinnerAdapter(fragment.getActivity());
        spType = view.findViewById(R.id.spType2);
        mViewModel.getAllLoaiThu().observe(fragment.getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
        spType.setAdapter(mAdapter);
        if(thu != null && thu.length>0){
            etID.setText(""+thu[0].tid);
            etName.setText(thu[0].ten);
            etAmount.setText(""+thu[0].sotien);
            etNote.setText(thu[0].ghichu);
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
                        Thu lt =new Thu();
                        lt.ten = etName.getText().toString();
                        lt.sotien = Float.parseFloat(etAmount.getText().toString());
                        lt.ghichu = etNote.getText().toString();
                        lt.ltid= ((LoaiThu)mAdapter.getItem(spType.getSelectedItemPosition())).lid;
                        if(mEditMode){
                            lt.tid = Integer.parseInt(etID.getText().toString());
                            mViewModel.update(lt);
                        }else {
                            mViewModel.insert(lt);
                        Toast.makeText(context,"Khoản thu đã được lưu",Toast.LENGTH_SHORT).show();
                    }
                }
                });
        mDialog = builder.create();
    }
    public void show(){
        mDialog.show();
    }
}
