package com.anders.stocksandnews;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.CollapsibleActionView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class LandingPageActivity extends AppCompatActivity {

    Button mOurs, mSignup, mFacebook, mGitHub, mEmail;
    AlphaAnimation mAnim;
    LineGraphSeries<DataPoint> mSeries1, mSeries2;
    GraphView mGraph;
    Boolean mWeekActive, mMonthActive;
    private static final String TAG = "LandingPageActivity";
    ArrayList<String> mMonths;
    ArrayList<StockObj> mStocks;
    MasterSingleton mSingleton;
    public int index, month;
    RecyclerView mRecView;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        mContext = LandingPageActivity.this;

        mRecView = (RecyclerView) findViewById(R.id.stockList);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        mRecView.setLayoutManager(linearLayoutManager);


        mSingleton = MasterSingleton.getINSTANCE();
        mSingleton.addStocks(new StockObj("Microsoft int","MSFT","13.12","13.78","14.31","13.78","14.21"
                ,"13.12","14.31","130000","13.78","14.20","7.21","25.12","7.21 - 25.12"));


        mStocks = mSingleton.getmStockObj();
        FrameLayout stockFragmentLayout = (FrameLayout) findViewById(R.id.stockFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();

        ArrayAdapterRV adapter = new ArrayAdapterRV(mContext,mStocks, stockFragmentLayout, fragmentManager);
        mRecView.setAdapter(adapter);

        mAnim = new AlphaAnimation(1f, 0.8f);
        mMonths = new ArrayList<>();
        mMonths.add("u");
        mMonths.add("l");
        mMonths.add("f");
        mMonths.add("u");
        mMonths.add("u");
        mMonths.add("l");
        mMonths.add("l");
        mMonths.add("u");
        mMonths.add("u");
        mMonths.add("l");
        mMonths.add("u");
        mMonths.add("u");


        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 500); // see this max value coming back here, we animale towards that value
        animation.setDuration(5000); //in milliseconds
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setRepeatMode(ObjectAnimator.RESTART);
        animation.start();

        mWeekActive = false;
        mMonthActive = false;

        mOurs = (Button) findViewById(R.id.login);
        mSignup = (Button) findViewById(R.id.signup);
        mFacebook = (Button) findViewById(R.id.facebook);
        mGitHub = (Button) findViewById(R.id.gitHub);
        mEmail = (Button) findViewById(R.id.email);

        mFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setAnimation(mAnim);
            }
        });
        mGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setAnimation(mAnim);
            }
        });
        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setAnimation(mAnim);
            }
        });

        mGraph = (GraphView) findViewById(R.id.graph);
        mGraph.getViewport().setXAxisBoundsManual(true);
        mGraph.getViewport().setMinX(1.0);
        mGraph.setTitle("$5000");
        mGraph.getViewport().setMaxX(365);


//        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(mGraph);
//        staticLabelsFormatter.setHorizontalLabels(new String[]{"J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D"});
//        mGraph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

//        mSeries1 = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(1, 1),
//                new DataPoint(1.5, 1.7),
//                new DataPoint(2, 2),
//                new DataPoint(3, 1),
//                new DataPoint(4, 3),
//                new DataPoint(5, 1),
//                new DataPoint(6, 2),
//                new DataPoint(7, 1),
//                new DataPoint(8, 4),
//                new DataPoint(9, 1),
//                new DataPoint(10, 2),
//                new DataPoint(11, 1),
//                new DataPoint(12, 5)
//        });
//        mGraph.addSeries(mSeries1);

        mOurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.setAnimation(mAnim);
//                LoginFragment loginFragment = new LoginFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .add(R.id.logInFragment, loginFragment)
//                        .addToBackStack("login").commit();
//                FrameLayout frameLayout = (FrameLayout) findViewById(R.id.logInFragment);
//                frameLayout.setVisibility(View.VISIBLE);

                v.setAnimation(mAnim);

                if (mSeries1 == null) {

                    Log.i(TAG, "onClick: " + mMonthActive);

                    mSeries1 = new LineGraphSeries<>(new DataPoint[]{});
                    mSeries1.setColor(Color.MAGENTA);



                    for (int i = 0; i < 365; i++) {
                        if (i % 7 == 0) {
                            int random = new Random().nextInt(1000);
                            mSeries1.appendData(new DataPoint(i, random), false, 365);
                        }
                    }

                    mGraph.addSeries(mSeries1);
                } else {
                    mSeries1.resetData(newWeekData());
                }

            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setAnimation(mAnim);
//                SignUpFragment signUpFragment = new SignUpFragment();
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction()
//                        .add(R.id.signUpFragment, signUpFragment)
//                        .addToBackStack("signUp").commit();
//                FrameLayout frameLayout = (FrameLayout) findViewById(R.id.signUpFragment);
//                frameLayout.setVisibility(View.VISIBLE);


                if (mSeries2 == null) {

                    Log.i(TAG, "onClick: " + mMonthActive);
                    mWeekActive = false;

                    mSeries2 = new LineGraphSeries<>(new DataPoint[]{});
                    mSeries2.setColor(Color.GREEN);

                    for (int i = 0; i < 366; i++) {
                        int random = new Random().nextInt(1000);
                        switch (i) {
                            case 0:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 31:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 59:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 90:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 120:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 151:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 181:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 212:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 243:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 273:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 304:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 334:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            case 365:
                                mSeries2.appendData(new DataPoint(i, random), false, 365);
                                break;
                            default:
                        }
                    }
                    mMonthActive = true;
                    mGraph.addSeries(mSeries2);
                } else {
                    mSeries2.resetData(newMonthData());
                }
            }
        });
    }

    private DataPoint[] newWeekData() {
        int weekIndex = 0;
        int weekVisible = 1;
        DataPoint[] array = new DataPoint[53];
        for (int i = 0; i < 365; i++) {
            if ((i % 7) == 0) {
                Log.i(TAG, "newWeekData: " + i);
                int random = new Random().nextInt(1000);
                array[weekIndex] = new DataPoint(i, random);
                weekVisible += 1;
                weekIndex += 1;
            }
        }
        return array;
    }

    private DataPoint[] newMonthData() {
        DataPoint[] array = new DataPoint[12];

//        month = 1;
        index = 0;
        for (int i = 0; i < 366; i++) {
            int random = new Random().nextInt(1000);
            switch (i) {
                case 31:
                    arrayIndex(array, random, i);
                    break;
                case 59:
                    arrayIndex(array, random, i);
                    break;
                case 90:
                    arrayIndex(array, random, i);
                    break;
                case 120:
                    arrayIndex(array, random, i);
                    break;
                case 151:
                    arrayIndex(array, random, i);
                    break;
                case 181:
                    arrayIndex(array, random, i);
                    break;
                case 212:
                    arrayIndex(array, random, i);
                    break;
                case 243:
                    arrayIndex(array, random, i);
                    break;
                case 273:
                    arrayIndex(array, random, i);
                    break;
                case 304:
                    arrayIndex(array, random, i);
                    break;
                case 334:
                    arrayIndex(array, random, i);
                    break;
                case 365:
                    arrayIndex(array, random, i);
                    break;
                default:
            }
        }
        return array;
    }

    public void arrayIndex(DataPoint[] array, int random, int num) {
        array[index] = new DataPoint(num, random);
//        month += 1;
        index += 1;
    }

}
