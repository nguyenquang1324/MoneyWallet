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
import com.example.moneywallet.entity.Chi;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChiRecyclerViewAdapter extends RecyclerView.Adapter<ChiRecyclerViewAdapter.ChiViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Chi> mlist;

    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;

    public ChiRecyclerViewAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        ChiRecyclerViewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        ChiRecyclerViewAdapter.itemViewClickListener = itemViewClickListener;
    }
    public ChiViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.recyclerview_chi_item,parent,false);

        return new ChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChiViewHolder holder, int position) {
         if (mlist != null)
         {
          holder.tvNamelc.setText(mlist.get(position).ten1);
          holder.tvAmount.setText(""+mlist.get(position).sotien1 +"VNĐ");
          holder.tvNote.setText(mlist.get(position).ghichu);
          holder.position = position;

         }
    }

    @Override
    public int getItemCount() {
        if(mlist == null)
        return 0;
        return mlist.size();
    }
    public Chi getItem(int position){
        if(mlist == null || position>=mlist.size()){
            return null;
        }
        return mlist.get(position);
    }

    public void setList(List<Chi> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    public static class ChiViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNamelc,tvNote,tvAmount;
        public ImageView ivViewlc ,ivEditlc;
        public CardView cv;
        public int position;
        public ChiViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvNamelc = itemView.findViewById(R.id.tvNamelc);
            tvAmount = itemView.findViewById(R.id.tvAmount4);
            tvNote  = itemView.findViewById(R.id.tvAmount5);
            ivViewlc = itemView.findViewById(R.id.ivViewlc);
            ivEditlc = itemView.findViewById(R.id.ivEditlc);
            cv = (CardView) itemView;
            ivViewlc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemViewClickListener!=null){
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
            ivEditlc.setOnClickListener(new View.OnClickListener() {
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
