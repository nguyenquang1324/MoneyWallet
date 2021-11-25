package com.example.moneywallet.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.Chi;
import com.example.moneywallet.entity.LoaiChi;
import com.example.moneywallet.entity.LoaiThu;
import com.example.moneywallet.ui.chi.KhoanChiFragment;

import java.util.List;

public class LoaiChiSpinnerAdapter extends BaseAdapter {
    private List<LoaiChi> mList;
    private LayoutInflater mLayoutInflater;
    public LoaiChiSpinnerAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);

    }

    public void setList(List<LoaiChi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(mList ==null)
            return 0;
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        if(mList ==null)
            return null;
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        KhoanChiVieHolder holder;
        if(view == null){
            view = mLayoutInflater.inflate(R.layout.spinner_chi_item,null ,false);
            holder = new KhoanChiVieHolder(view);
            view.setTag(holder);
        }else {
            holder = (KhoanChiVieHolder) view.getTag();
        }
        holder.tvName.setText(mList.get(i).ten1);
        return view;
    }
    public static class KhoanChiVieHolder{
        public TextView tvName;
        public KhoanChiVieHolder(View view){
            tvName = view.findViewById(R.id.tvName);

        }
    }
}
