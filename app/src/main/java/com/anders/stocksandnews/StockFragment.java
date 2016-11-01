package com.anders.stocksandnews;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by anders on 10/29/2016.
 */

public class StockFragment extends Fragment {

    TextView mHigh, mlow, mVolume, m50DayAvg, m200DayAvg, mYearLow, mYearHigh, mYearRange, mAfterHours;
    String date, open, high, low, close, volume, exDividend, splitRatio,
            adjOpen, adjHigh, asjLow, adjClose, adjVolume;
    ArrayList<ExtendedPeriodObject> mPeriodObj;
    private static final String TAG = "StockFragment";
    List<HistoricalInfo> mHistoricalStockList;
    MasterSingleton mSingleton;
    GraphView mGraphView;
    LineGraphSeries<DataPoint> mSeriesMonth;
    public int index;
    Context mContext;
    double mTodaysBell, mLastBell;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.stock_fragment, container, false);
        mSingleton = MasterSingleton.getINSTANCE();
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");
        mContext = getContext();

        mGraphView = (GraphView) view.findViewById(R.id.graph);
        mGraphView.setTitle(mSingleton.getmStockObj().get(position).getmName());
        mGraphView.getViewport().setXAxisBoundsManual(true);
        mGraphView.getViewport().setMinX(1.0);
        mGraphView.setTitle("$5000");
        mGraphView.getViewport().setMaxX(365);

        mTodaysBell = 0;
        mLastBell = 0;

//        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(mGraphView);
//        DateAsXAxisLabelFormatter date = new DateAsXAxisLabelFormatter(mContext,)
//        staticLabelsFormatter.setHorizontalLabels(new String[]{"J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"});
//        mGraphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        ArrayList<StockObj> array = MasterSingleton.getINSTANCE().getmStockObj();

        volleyStockRequest();

        mHigh = (TextView) view.findViewById(R.id.high);
        mlow = (TextView) view.findViewById(R.id.low);
        mVolume = (TextView) view.findViewById(R.id.volume);
        m50DayAvg = (TextView) view.findViewById(R.id.fifty_day_moving);
        m200DayAvg = (TextView) view.findViewById(R.id.two_hund_day_moving);
        mYearLow = (TextView) view.findViewById(R.id.year_low);
        mYearHigh = (TextView) view.findViewById(R.id.year_high);
        mYearRange = (TextView) view.findViewById(R.id.year_range);
        mAfterHours = (TextView) view.findViewById(R.id.aft_hrs_chg);

        mHigh.setText(array.get(position).getmHigh());
        mlow.setText(array.get(position).getmLow());
        mVolume.setText(array.get(position).getmVolume());
        m50DayAvg.setText(array.get(position).getM50DayAvg());
        m200DayAvg.setText(array.get(position).getM200DayAvg());
        mYearLow.setText(array.get(position).getmYearLow());
        mYearHigh.setText(array.get(position).getmYearHigh());
        mYearRange.setText(array.get(position).getmYearRange());
        mAfterHours.setText(array.get(position).getmAfterHours());


        return view;
    }

    public void volleyStockRequest() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://www.quandl.com/api/v3/datasets/WIKI/FB.json?api_key=eiYovs6gMvpaynxSANQp", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: " + response.toString());

                try {
                    JSONObject container = response.getJSONObject("dataset");
                    JSONArray data = container.getJSONArray("data");
                    Log.i(TAG, "onResponse: data " + data.length());

                    if (mHistoricalStockList == null) {
                        mHistoricalStockList = new ArrayList<>();
                    } else {
                        mHistoricalStockList.clear();
                        mSingleton.getHistocicalArray().clear();
                    }

                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
                    Calendar calendar = new GregorianCalendar();
                    String currentDate = dateFormat.format(calendar.getTime());

//                    there are 252 trading days per year so data.length()-504 = only the first year
                    for (int i = data.length() - 1; i >= 0; i--) {
                        String dataObj = data.get(i).toString().substring(1, data.get(i).toString().length() - 1);

                        String[] temp = dataObj.split(",");
                        List<String> list = Arrays.asList(temp);

                        double objyear = Double.parseDouble(list.get(0).substring(1, 5));
                        double objmonth = Double.parseDouble(list.get(0).substring(6, 8));
                        double lastyear = Double.parseDouble(currentDate.substring(0, 4)) - 1;
                        double thismonth = Double.parseDouble(currentDate.substring(5, 7));

                        if ((objyear == (lastyear) && objmonth > thismonth || objyear > lastyear && objmonth <= thismonth)) {

                            if (mTodaysBell == 0) {
                                mTodaysBell = Double.parseDouble(list.get(0).substring(9, 11));
//                                Log.i(TAG, "onResponse: + mTodaysBell " + mTodaysBell);
                            } else {
                                mLastBell = mTodaysBell;
//                                Log.i(TAG, "onResponse: + mLastBell " + mLastBell);
                                mTodaysBell = Double.parseDouble(list.get(0).substring(9, 11));
//                                Log.i(TAG, "onResponse: + mTodaysBell " + mTodaysBell);
                            }

                            if ((mTodaysBell - mLastBell) >= 3 || ((mTodaysBell - mLastBell) <= -3)) {
                                int num = (int) (mTodaysBell - mLastBell);
                                if (num < 0) {
                                    int num2 = (int) (num + mLastBell);
                                    Log.i(TAG, "onResponse: int num2 = (int) (num + mLastBell); " + num + mLastBell);
                                    for (int n = 1; n < num2; n++) {
                                        mHistoricalStockList.add(new HistoricalInfo("", "", "", "", "", "", "", "", "", "", "", "", ""));
//                                        Log.i(TAG, "onResponse: historical list size " + mHistoricalStockList.size() + " weekend ");
                                        Log.i(TAG, "onResponse: number 1 " + (mTodaysBell - mLastBell));
                                        Log.i(TAG, "onResponse: number 1 " + i);
                                    }
                                } else {
                                    for (int n = 1; n < num; n++) {
                                        mHistoricalStockList.add(new HistoricalInfo("", "", "", "", "", "", "", "", "", "", "", "", ""));
//                                        Log.i(TAG, "onResponse: historical list size " + mHistoricalStockList.size() + " weekend ");
                                        Log.i(TAG, "onResponse: number 2 " + (mTodaysBell - mLastBell));
                                        Log.i(TAG, "onResponse: number 2 " + i);
                                    }
                                }
//                                Log.i(TAG, ": Time Off ");
//                                Log.i(TAG, ":  last " + mLastBell + " :  today " + mTodaysBell);
                            } else {
//                                Log.i(TAG, ": Working ");
//                                Log.i(TAG, ":  last " + mLastBell + " :  today " + mTodaysBell);
                            }

//                            Log.i(TAG, " -------------------------------------------------------------------------------- ");
//                            Log.i(TAG, " -------------------------------------------------------------------------------- ");
                            date = list.get(0);
                            open = list.get(1);
                            high = list.get(2);
                            low = list.get(3);
                            close = list.get(4);
                            volume = list.get(5);
                            exDividend = list.get(6);
                            splitRatio = list.get(7);
                            adjOpen = list.get(8);
                            adjHigh = list.get(9);
                            asjLow = list.get(10);
                            adjClose = list.get(11);
                            adjVolume = list.get(12);

                            mHistoricalStockList.add(new HistoricalInfo(date, open,
                                    high, low, close, volume, exDividend, splitRatio,
                                    adjOpen, adjHigh, asjLow, adjClose, adjVolume));
//                            Log.i(TAG, "onResponse: historical list size " + mHistoricalStockList.size());
                        }
                    }

                    mSingleton.setHistoricalData(mHistoricalStockList);
//                    if (!mSingleton.getHistocicalArray().isEmpty()) {
                        setMonthlyGraphData();
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public void setMonthlyGraphData() {
        if (mSeriesMonth == null) {
//          if null instantiate the object
            mSeriesMonth = new LineGraphSeries<>(new DataPoint[]{});
            mSeriesMonth.setColor(Color.GREEN);

            for (int i = 0; i < mSingleton.getHistocicalArray().size(); i++) {
//                int random = new Random().nextInt(1000);
                if (mSingleton.getHistocicalArray().size() < i) {
                    double num = Double.parseDouble(mSingleton.getHistocicalArray().get(i).getClose());
                    switch (i) {
                        case 0:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 31:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 59:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 90:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 120:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 151:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 181:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 212:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 243:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 273:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 304:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 334:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        case 365:
                            mSeriesMonth.appendData(new DataPoint(i, num), false, 365);
                            break;
                        default:
                    }
                }
            }
//          The graph requires us to add the filled LineGraphSeries to the View like the following
            mGraphView.addSeries(mSeriesMonth);
        } else {
//          we are going to create and fill a new data point array
            mSeriesMonth.resetData(newMonthData());
        }
    }

    private DataPoint[] newMonthData() {
        DataPoint[] array = new DataPoint[12];

        index = 0;
        for (int i = 0; i < 366; i++) {
//            int random = new Random().nextInt(1000);
            double num = Double.parseDouble(mSingleton.getHistocicalArray().get(i).getClose());
            switch (i) {
                case 31:
                    arrayIndex(array, num, i);
                    break;
                case 59:
                    arrayIndex(array, num, i);
                    break;
                case 90:
                    arrayIndex(array, num, i);
                    break;
                case 120:
                    arrayIndex(array, num, i);
                    break;
                case 151:
                    arrayIndex(array, num, i);
                    break;
                case 181:
                    arrayIndex(array, num, i);
                    break;
                case 212:
                    arrayIndex(array, num, i);
                    break;
                case 243:
                    arrayIndex(array, num, i);
                    break;
                case 273:
                    arrayIndex(array, num, i);
                    break;
                case 304:
                    arrayIndex(array, num, i);
                    break;
                case 334:
                    arrayIndex(array, num, i);
                    break;
                case 365:
                    arrayIndex(array, num, i);
                    break;
                default:
            }
        }
        return array;
    }

    public void arrayIndex(DataPoint[] array, double value, int dataIndex) {
        array[index] = new DataPoint(dataIndex, value);
        index += 1;
    }
}
