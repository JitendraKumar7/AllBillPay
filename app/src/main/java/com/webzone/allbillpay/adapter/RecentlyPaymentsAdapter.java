package com.webzone.allbillpay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webzone.allbillpay.R;

/**
 * Created by Jitendra Soam on 1/16/2017.
 */

public class RecentlyPaymentsAdapter extends RecyclerView.Adapter<RecentlyPaymentsAdapter.CustomViewHolder> {

    private Context mContext;

    public RecentlyPaymentsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivType;
        private TextView tvStatus;

        public CustomViewHolder(View itemView) {
            super(itemView);

            ivType = (ImageView) itemView.findViewById(R.id.view_ivType);
            tvStatus = (TextView) itemView.findViewById(R.id.view_txtStatus);
        }

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_recently_payments, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        if (position % 5 == 0) {
            holder.tvStatus.setText("Failed");
            int colorRed = mContext.getResources().getColor(R.color.colorRed);
            holder.tvStatus.setTextColor(colorRed);
        } else {
            holder.tvStatus.setText("Success");
            int colorGreen = mContext.getResources().getColor(R.color.colorGreen);
            holder.tvStatus.setTextColor(colorGreen);
        }

        if (position % 4 == 0) {
            holder.ivType.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_datacard));
        } else if (position % 2 == 0) {
            holder.ivType.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_mobile));
        } else {
            holder.ivType.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_dth));
        }

    }

    @Override
    public int getItemCount() {
        return 17;
    }
}

