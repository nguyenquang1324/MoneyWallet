package com.example.moneywallet.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.moneywallet.R;

import com.example.moneywallet.entity.Nguoino;

import java.util.List;

public class NospinnerAdapter extends BaseAdapter {

    private List<Nguoino> mList;
    private LayoutInflater mLayoutInflater;
    public NospinnerAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);

    }
    public void setList(List<Nguoino> mList) {
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
       KhoanNoViewHolder holder;
        if(view == null){
            view = mLayoutInflater.inflate(R.layout.spinner_no_item,null ,false);
            holder = new NospinnerAdapter.KhoanNoViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (KhoanNoViewHolder) view.getTag();
        }
        holder.textView15.setText(mList.get(i).ten);
        return view;
    }
    public static class KhoanNoViewHolder{
        public TextView textView15;
        public KhoanNoViewHolder(View view){
            textView15 = view.findViewById(R.id.textView15);

        }
    }
}
