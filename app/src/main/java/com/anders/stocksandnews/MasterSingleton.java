package com.anders.stocksandnews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anders on 10/29/2016.
 */

public class MasterSingleton {

    public ArrayList<StockObj> mStockObj;
    public static MasterSingleton INSTANCE;
    public List<HistoricalInfo> mHistorical;

    public static MasterSingleton getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new MasterSingleton();
        }
        else{}
        return INSTANCE;
    }

    public List<HistoricalInfo> getHistocicalArray() {
        if (mHistorical == null){
            mHistorical = new ArrayList<>();
            return mHistorical;
        }
        else{
            return mHistorical;
        }
    }

    public void setHistoricalData(List<HistoricalInfo> historical){
        mHistorical = historical;
    }

    public void clearHistoricalData(){
        if (mHistorical == null){
            mHistorical = new ArrayList<>();
        }
        else{
            mHistorical.clear();
        }
    }

    public ArrayList<StockObj> getmStockObj() {
        if (mStockObj == null){
            mStockObj = new ArrayList<>();
        }
        else{}
        return mStockObj;
    }

    public void addStocks(StockObj stockObj){
        if(mStockObj == null){
            mStockObj = new ArrayList<>();
            mStockObj.add(stockObj);
        }
        else{
            mStockObj.add(stockObj);
        }
    }

    public void removeStock(StockObj stockObj){
        if (mStockObj != null){
            mStockObj.remove(stockObj);
        }
    }

}
