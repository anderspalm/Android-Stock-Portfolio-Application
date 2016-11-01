package com.anders.stocksandnews;


public class HistoricalInfo {

    String date, open, high, low, close, volume, exDividend, splitRatio,
            adjOpen, adjHigh, asjLow, adjClose, adjVolume;

    public HistoricalInfo(String date, String open, String high, String low, String close,
                          String volume, String exDividend, String splitRatio, String adjOpen,
                          String adjHigh, String asjLow, String adjClose, String adjVolume) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.exDividend = exDividend;
        this.splitRatio = splitRatio;
        this.adjOpen = adjOpen;
        this.adjHigh = adjHigh;
        this.asjLow = asjLow;
        this.adjClose = adjClose;
        this.adjVolume = adjVolume;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getExDividend() {
        return exDividend;
    }

    public void setExDividend(String exDividend) {
        this.exDividend = exDividend;
    }

    public String getSplitRatio() {
        return splitRatio;
    }

    public void setSplitRatio(String splitRatio) {
        this.splitRatio = splitRatio;
    }

    public String getAdjOpen() {
        return adjOpen;
    }

    public void setAdjOpen(String adjOpen) {
        this.adjOpen = adjOpen;
    }

    public String getAdjHigh() {
        return adjHigh;
    }

    public void setAdjHigh(String adjHigh) {
        this.adjHigh = adjHigh;
    }

    public String getAsjLow() {
        return asjLow;
    }

    public void setAsjLow(String asjLow) {
        this.asjLow = asjLow;
    }

    public String getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(String adjClose) {
        this.adjClose = adjClose;
    }

    public String getAdjVolume() {
        return adjVolume;
    }

    public void setAdjVolume(String adjVolume) {
        this.adjVolume = adjVolume;
    }
}
