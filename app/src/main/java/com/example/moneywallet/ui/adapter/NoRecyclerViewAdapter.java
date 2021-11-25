package com.example.moneywallet.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneywallet.R;
import com.example.moneywallet.entity.No;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NoRecyclerViewAdapter extends RecyclerView.Adapter<NoRecyclerViewAdapter.NoViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<No> mList;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public NoRecyclerViewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        NoRecyclerViewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        NoRecyclerViewAdapter.itemViewClickListener = itemViewClickListener;
    }



    @NonNull
    @NotNull
    @Override
    public NoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_no_item,parent,false);
        return new NoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoViewHolder holder, int position) {

        if (mList != null){
            holder.tvName.setText(mList.get(position).ten);
            holder.tvAmount.setText(""+mList.get(position).sotien +"VNĐ");
            holder.tvNote.setText(mList.get(position).ghichu);
            holder.position = position;

        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }
    public No getItem(int position){
        if(mList == null || position >= mList.size()){
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<No> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class NoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName,tvAmount,tvNote;
        public ImageView ivEdit,ivView;
        public CardView cv;
        public int position;
        public NoViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivView = itemView.findViewById(R.id.ivView);
            tvAmount = itemView.findViewById(R.id.tvAmount6);
            tvNote = itemView.findViewById(R.id.tvNote);
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
