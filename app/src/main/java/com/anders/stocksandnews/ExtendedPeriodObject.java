package com.anders.stocksandnews;

/**
 * Created by anders on 10/29/2016.
 */

public class ExtendedPeriodObject {

    String mHigh,mlow,mVolume, m50DayAvg, m200DayAvg, mYearLow, mYearHigh, mYearRange, mAfterHours;

    public ExtendedPeriodObject(String mHigh, String mlow, String mVolume, String m50DayAvg, String m200DayAvg, String mYearLow, String mYearHigh, String mYearRange, String mAfterHours) {
        this.mHigh = mHigh;
        this.mlow = mlow;
        this.mVolume = mVolume;
        this.m50DayAvg = m50DayAvg;
        this.m200DayAvg = m200DayAvg;
        this.mYearLow = mYearLow;
        this.mYearHigh = mYearHigh;
        this.mYearRange = mYearRange;
        this.mAfterHours = mAfterHours;
    }

    public String getmHigh() {
        return mHigh;
    }

    public void setmHigh(String mHigh) {
        this.mHigh = mHigh;
    }

    public String getMlow() {
        return mlow;
    }

    public void setMlow(String mlow) {
        this.mlow = mlow;
    }

    public String getmVolume() {
        return mVolume;
    }

    public void setmVolume(String mVolume) {
        this.mVolume = mVolume;
    }

    public String getM50DayAvg() {
        return m50DayAvg;
    }

    public void setM50DayAvg(String m50DayAvg) {
        this.m50DayAvg = m50DayAvg;
    }

    public String getM200DayAvg() {
        return m200DayAvg;
    }

    public void setM200DayAvg(String m200DayAvg) {
        this.m200DayAvg = m200DayAvg;
    }

    public String getmYearLow() {
        return mYearLow;
    }

    public void setmYearLow(String mYearLow) {
        this.mYearLow = mYearLow;
    }

    public String getmYearHigh() {
        return mYearHigh;
    }

    public void setmYearHigh(String mYearHigh) {
        this.mYearHigh = mYearHigh;
    }

    public String getmYearRange() {
        return mYearRange;
    }

    public void setmYearRange(String mYearRange) {
        this.mYearRange = mYearRange;
    }

    public String getmAfterHours() {
        return mAfterHours;
    }

    public void setmAfterHours(String mAfterHours) {
        this.mAfterHours = mAfterHours;
    }
}
