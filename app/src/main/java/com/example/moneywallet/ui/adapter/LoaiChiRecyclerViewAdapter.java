package com.example.moneywallet.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.LoaiChi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LoaiChiRecyclerViewAdapter extends RecyclerView.Adapter<LoaiChiRecyclerViewAdapter.LoaiChiViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<LoaiChi> mlist;

    public LoaiChiRecyclerViewAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }
    public LoaiChiViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_loai_chi_item,parent,false);

        return new LoaiChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull LoaiChiViewHolder holder, int position) {
         if (mlist != null)
         {
          holder.tvNamelc.setText(mlist.get(position).ten1);

         }
    }

    @Override
    public int getItemCount() {
        if(mlist == null)
        return 0;
        return mlist.size();
    }

    public void setList(List<LoaiChi> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    public static class LoaiChiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNamelc;
        public ImageView ivViewlc ,ivEditlc;
        public CardView cv;
        public LoaiChiViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvNamelc = itemView.findViewById(R.id.tvNamelc);
            ivViewlc = itemView.findViewById(R.id.ivViewlc);
            ivEditlc = itemView.findViewById(R.id.ivEditlc);
            cv = (CardView) itemView;
        }
    }
}
