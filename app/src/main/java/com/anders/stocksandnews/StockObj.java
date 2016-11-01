package com.anders.stocksandnews;

/**
 * Created by anders on 10/29/2016.
 */

public class StockObj {

    String mName, mSymbol, mOpen, mPrice, mAsk, mBid,
            mHigh, mLow, mVolume, m50DayAvg, m200DayAvg,
            mYearLow, mYearHigh, mYearRange, mAfterHours;

    public StockObj(String mName, String mPrice, String mOpen, String mAfterHours, String mHigh, String mLow,
                    String mSymbol,String mAsk, String mBid, String mVolume,
                    String m50DayAvg, String m200DayAvg, String mYearLow,
                    String mYearHigh, String mYearRange) {
        this.mName = mName;
        this.mHigh = mHigh;
        this.mLow = mLow;
        this.mSymbol = mSymbol;
        this.mOpen = mOpen;
        this.mPrice = mPrice;
        this.mAsk = mAsk;
        this.mBid = mBid;
        this.mVolume = mVolume;
        this.m50DayAvg = m50DayAvg;
        this.m200DayAvg = m200DayAvg;
        this.mYearLow = mYearLow;
        this.mYearHigh = mYearHigh;
        this.mYearRange = mYearRange;
        this.mAfterHours = mAfterHours;
    }

    public String getmName() {
        return mName;
    }

    public String getmHigh() {
        return mHigh;
    }

    public String getmVolume() {
        return mVolume;
    }

    public String getM50DayAvg() {
        return m50DayAvg;
    }

    public String getM200DayAvg() {
        return m200DayAvg;
    }

    public String getmYearLow() {
        return mYearLow;
    }

    public String getmYearHigh() {
        return mYearHigh;
    }

    public String getmYearRange() {
        return mYearRange;
    }

    public String getmAfterHours() {
        return mAfterHours;
    }

    public String getmLow() {
        return mLow;
    }

    public String getmSymbol() {
        return mSymbol;
    }

    public String getmOpen() {
        return mOpen;
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getmAsk() {
        return mAsk;
    }

    public String getmBid() {
        return mBid;
    }
}
