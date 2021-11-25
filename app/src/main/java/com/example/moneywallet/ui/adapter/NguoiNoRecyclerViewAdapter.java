package com.example.moneywallet.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.LoaiThu;
import com.example.moneywallet.entity.Nguoino;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NguoiNoRecyclerViewAdapter extends RecyclerView.Adapter<NguoiNoRecyclerViewAdapter.NguoiNoViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Nguoino> mList;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public NguoiNoRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        NguoiNoRecyclerViewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        NguoiNoRecyclerViewAdapter.itemViewClickListener = itemViewClickListener;
    }



    @NonNull
    @NotNull
    @Override
    public NguoiNoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_nguoi_no_item,parent,false);
        return new NguoiNoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NguoiNoViewHolder holder, int position) {

        if (mList != null){
            holder.tvName.setText(mList.get(position).ten);

            holder.position = position;

        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }
    public Nguoino getItem(int position){
        if(mList == null || position >= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Nguoino> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class NguoiNoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public ImageView ivEdit,ivView;
        public CardView cv;
        public int position;
        public NguoiNoViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivView = itemView.findViewById(R.id.ivView);
            cv = (CardView) itemView;
            ivView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemViewClickListener!=null){
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemEditClickListener != null){
                        itemEditClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
