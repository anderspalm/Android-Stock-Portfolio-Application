package com.anders.stocksandnews;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.zip.Inflater;

/**
 * Created by anders on 10/29/2016.
 */

public class ArrayAdapterRV extends RecyclerView.Adapter {

    ArrayList<StockObj> mList;
    Context mContext;
    FrameLayout mFrameLayout;
    FragmentManager mFragMan;
    ArrayViewHolder AVH;

    public ArrayAdapterRV(Context context, ArrayList<StockObj> list,
                          FrameLayout frameLayout, FragmentManager fragmentManager) {
        mContext = context;
        mList = list;
        mFrameLayout = frameLayout;
        mFragMan = fragmentManager;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.stock_items, parent, false);
        AVH = new ArrayViewHolder(root);
        return AVH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AVH.mName.setText(mList.get(position).getmName());
        AVH.mSymbol.setText(mList.get(position).getmSymbol());
        AVH.mOpen.setText(mList.get(position).getmOpen());
        AVH.mPrice.setText(mList.get(position).getmPrice());
        AVH.mAsk.setText(mList.get(position).getmAsk());
        AVH.mBid.setText(mList.get(position).getmBid());
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        } else {
            return 0;
        }
    }

    public class ArrayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mName, mSymbol, mOpen, mPrice, mAsk, mBid;

        public ArrayViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            mName = (TextView) view.findViewById(R.id.name);
            mSymbol = (TextView) view.findViewById(R.id.symbol);
            mOpen = (TextView) view.findViewById(R.id.open);
            mPrice = (TextView) view.findViewById(R.id.updatedPrice);
            mAsk = (TextView) view.findViewById(R.id.ask);
            mBid = (TextView) view.findViewById(R.id.bid);
        }

        @Override
        public void onClick(View v) {
            mFrameLayout.setVisibility(View.VISIBLE);
            StockFragment stockFragment = new StockFragment();
            Bundle bundle = new Bundle();
            int position = getAdapterPosition();
            bundle.putInt("position",position);
            stockFragment.setArguments(bundle);

            mFragMan.beginTransaction().add(R.id.stockFragment, stockFragment)
                    .addToBackStack("stock").commit();

        }
    }
}
