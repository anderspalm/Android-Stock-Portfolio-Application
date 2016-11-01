package com.anders.stocksandnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by anders on 10/26/2016.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {

    Button m1a, m2a, m3a, m4a, m5a, m6a, m7a, m8a, m9a, m0a;
    Button m1b, m2b, m3b, m4b, m5b, m6b, m7b, m8b, m9b, m0b;
    private String passwordOne;
    private String passwordTwo;
    TextView firstPassword, mSecondPassword;
    LinearLayout mfirstPin, mSecondPin, mFirstPinLayout, mSecondPinLayout;

    AlphaAnimation mAnim;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.signup, container, false);

        passwordOne = "";
        passwordTwo = "";

        m1a = (Button) root.findViewById(R.id.one);
        m2a = (Button) root.findViewById(R.id.two);
        m3a = (Button) root.findViewById(R.id.three);
        m4a = (Button) root.findViewById(R.id.four);
        m5a = (Button) root.findViewById(R.id.five);
        m6a = (Button) root.findViewById(R.id.six);
        m7a = (Button) root.findViewById(R.id.seven);
        m8a = (Button) root.findViewById(R.id.eight);
        m9a = (Button) root.findViewById(R.id.nine);
        m0a = (Button) root.findViewById(R.id.zero);

        m1b = (Button) root.findViewById(R.id.one2nd);
        m2b = (Button) root.findViewById(R.id.two2nd);
        m3b = (Button) root.findViewById(R.id.three2nd);
        m4b = (Button) root.findViewById(R.id.four2nd);
        m5b = (Button) root.findViewById(R.id.five2nd);
        m6b = (Button) root.findViewById(R.id.six2nd);
        m7b = (Button) root.findViewById(R.id.seven2nd);
        m8b = (Button) root.findViewById(R.id.eight2nd);
        m9b = (Button) root.findViewById(R.id.nine2nd);
        m0b = (Button) root.findViewById(R.id.zero2nd);

        m1b.setOnClickListener(this);m2b.setOnClickListener(this);
        m3b.setOnClickListener(this);m4b.setOnClickListener(this);
        m5b.setOnClickListener(this);m6b.setOnClickListener(this);
        m7b.setOnClickListener(this);m8b.setOnClickListener(this);
        m9b.setOnClickListener(this);m0b.setOnClickListener(this);

        m1a.setOnClickListener(this);m2a.setOnClickListener(this);
        m3a.setOnClickListener(this);m4a.setOnClickListener(this);
        m5a.setOnClickListener(this);m6a.setOnClickListener(this);
        m7a.setOnClickListener(this);m8a.setOnClickListener(this);
        m9a.setOnClickListener(this);m0a.setOnClickListener(this);

        mAnim = new AlphaAnimation(1f,0.8f);
        mAnim.setDuration(100);

        mfirstPin = (LinearLayout) root.findViewById(R.id.firstPin);
        mSecondPin = (LinearLayout) root.findViewById(R.id.secondPin);
        mFirstPinLayout = (LinearLayout) root.findViewById(R.id.firstPinLayout);
        mSecondPinLayout = (LinearLayout) root.findViewById(R.id.secondPinLayout);

        firstPassword = (TextView) root.findViewById(R.id.passwordOne);
        mSecondPassword = (TextView) root.findViewById(R.id.passwordTwo);
        firstPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i(TAG, "afterTextChanged: " + passwordOne);
                mfirstPin.setVisibility(View.GONE);
                mSecondPin.setVisibility(View.VISIBLE);
                mFirstPinLayout.setVisibility(View.GONE);
                mSecondPinLayout.setVisibility(View.VISIBLE);
            }
        });

        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            default:
                Log.i(TAG, "onClick: Inside onClick");
            case R.id.one:
                v.startAnimation(mAnim);
                Log.i(TAG, "onClick: first fired");
                passwordOne += 1;
                firstPassword.setText(passwordOne);
                break;
            case R.id.two:
                v.startAnimation(mAnim);
                passwordOne += 2;
                firstPassword.setText(passwordOne);
                break;
            case R.id.three:
                v.startAnimation(mAnim);
                passwordOne += 3;
                firstPassword.setText(passwordOne);
                break;
            case R.id.four:
                v.startAnimation(mAnim);
                passwordOne += 4;
                firstPassword.setText(passwordOne);
                break;
            case R.id.five:
                v.startAnimation(mAnim);
                passwordOne += 5;
                firstPassword.setText(passwordOne);
                break;
            case R.id.six:
                v.startAnimation(mAnim);
                passwordOne += 6;
                firstPassword.setText(passwordOne);
                break;
            case R.id.seven:
                v.startAnimation(mAnim);
                passwordOne += 7;
                firstPassword.setText(passwordOne);
                break;
            case R.id.eight:
                v.startAnimation(mAnim);
                passwordOne += 8;
                firstPassword.setText(passwordOne);
                break;
            case R.id.nine:
                v.startAnimation(mAnim);
                passwordOne += 9;
                firstPassword.setText(passwordOne);
                break;
            case R.id.zero:
                v.startAnimation(mAnim);
                passwordOne += 0;
                firstPassword.setText(passwordOne);
                break;
            case R.id.one2nd:
                v.startAnimation(mAnim);
                passwordTwo += 1;
                firstPassword.setText(passwordTwo);
                break;
            case R.id.two2nd:
                v.startAnimation(mAnim);
                passwordTwo += 2;
                firstPassword.setText(passwordTwo);
                break;
            case R.id.three2nd:
                v.startAnimation(mAnim);
                passwordTwo += 3;
                firstPassword.setText(passwordTwo);
                break;
            case R.id.four2nd:
                v.startAnimation(mAnim);
                passwordTwo += 4;
                firstPassword.setText(passwordTwo);
                break;
            case R.id.five2nd:
                v.startAnimation(mAnim);
                passwordTwo += 5;
                firstPassword.setText(passwordTwo);
                break;
            case R.id.six2nd:
                v.startAnimation(mAnim);
                passwordTwo += 6;
                firstPassword.setText(passwordTwo);
                break;
            case R.id.seven2nd:
                v.startAnimation(mAnim);
                passwordTwo += 7;
                firstPassword.setText(passwordTwo);
                break;
            case R.id.eight2nd:
                v.startAnimation(mAnim);
                passwordTwo += 8;
                firstPassword.setText(passwordTwo);
                break;
            case R.id.nine2nd:
                v.startAnimation(mAnim);
                passwordTwo += 9;
                firstPassword.setText(passwordTwo);
                break;
            case R.id.zero2nd:
                v.startAnimation(mAnim);
                passwordTwo += 0;
                firstPassword.setText(passwordTwo);
                break;

        }
    }
}
