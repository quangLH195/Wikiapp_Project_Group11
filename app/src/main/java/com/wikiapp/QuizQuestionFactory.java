package com.wikiapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;



public class QuizQuestionFactory extends Fragment implements View.OnClickListener {

    View v;
    int random_number, usr_answer, question_number, stateNum;
    String question, answer, img_enabled, rb1, rb2, rb3, rb4;
    TextView question_ui;
    RadioButton rb1_ui, rb2_ui, rb3_ui, rb4_ui;
    ImageView img;
    CollapsingToolbarLayout ctl;
    ViewPager mViewPager;
    Typeface tf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.quiz_question, container, false);

        usr_answer = 0;
        question_number = 0;

        question_ui = v.findViewById(R.id.text_question);
        rb1_ui = v.findViewById(R.id.rb_quiz1);
        rb2_ui = v.findViewById(R.id.rb_quiz2);
        rb3_ui = v.findViewById(R.id.rb_quiz3);
        rb4_ui = v.findViewById(R.id.rb_quiz4);
        img = v.findViewById(R.id.img_question);
        ctl = getActivity().findViewById(R.id.ctl_quiz_activity);
        mViewPager = getActivity().findViewById(R.id.quiz_pager);

        // random number
        numberFactory();

        // initialize question
        questionFactory();
        getFont();
        initUI();

        return v;
    }

    @SuppressLint("ApplySharedPref")
    private void numberFactory() {
        Log.d(getResources().getString(R.string.app_package_name), "numberFactory()");
        Bundle getBundle = getActivity().getIntent().getExtras();
        Bundle putBundle = new Bundle();
        Intent intent = getActivity().getIntent();

        // generating random number
        Random random = new Random();
        int max = 10;
        int min = 1;
        int generated_num = random.nextInt((max - min) + 1) + min;

        // checking random number
        Integer[] numHistory = new Integer[10];
        for (int i = 0; i < 10; i++) {
            String getNumHistory = getBundle.getString("num" + String.valueOf(i + 1));
            if (getNumHistory != null && getNumHistory.equals("")) {
                numHistory[i] = Integer.parseInt(getNumHistory);
            } else {
                numHistory[i] = 0;
            }
        }
        for (int i=0; i < 10; i++) {
            if (random_number == numHistory[i]) {
                while (true) {
                    Random random2 = new Random();
                    int max2 = 10;
                    int min2 = 1;
                    int generated_num2 = random2.nextInt((max2 - min2) + 1) + min2;
                    if (generated_num != generated_num2) {
                        random_number = generated_num2;
                        stateNum = i + 1;
                        break;
                    }
                }
                break;
            } else if (numHistory[i] == 0){
                random_number = generated_num;
                stateNum = i + 1;
                break;
            }
        }

        // recording number history
        putBundle.putString("num" + String.valueOf(stateNum), String.valueOf(random_number));
        Log.d(getResources().getString(R.string.app_package_name), "num[" + String.valueOf(stateNum) + "] : " + String.valueOf(random_number));
        intent.putExtras(putBundle);
    }

    private void questionFactory() {
        switch (random_number) {
            case 1:
                question = getResources().getString(R.string.quest1);
                answer = getResources().getString(R.string.quest1_answer);
                img_enabled = getResources().getString(R.string.quest1_img);
                rb1 = getResources().getString(R.string.quest1_op1);
                rb2 = getResources().getString(R.string.quest1_op2);
                rb3 = getResources().getString(R.string.quest1_op3);
                rb4 = getResources().getString(R.string.quest1_op4);
                break;

            case 2:
                question = getResources().getString(R.string.quest2);
                answer = getResources().getString(R.string.quest2_answer);
                img_enabled = getResources().getString(R.string.quest2_img);
                rb1 = getResources().getString(R.string.quest2_op1);
                rb2 = getResources().getString(R.string.quest2_op2);
                rb3 = getResources().getString(R.string.quest2_op3);
                rb4 = getResources().getString(R.string.quest2_op4);
                break;

            case 3:
                question = getResources().getString(R.string.quest3);
                answer = getResources().getString(R.string.quest3_answer);
                img_enabled = getResources().getString(R.string.quest3_img);
                rb1 = getResources().getString(R.string.quest3_op1);
                rb2 = getResources().getString(R.string.quest3_op2);
                rb3 = getResources().getString(R.string.quest3_op3);
                rb4 = getResources().getString(R.string.quest3_op4);
                break;

            case 4:
                question = getResources().getString(R.string.quest4);
                answer = getResources().getString(R.string.quest4_answer);
                img_enabled = getResources().getString(R.string.quest4_img);
                rb1 = getResources().getString(R.string.quest4_op1);
                rb2 = getResources().getString(R.string.quest4_op2);
                rb3 = getResources().getString(R.string.quest4_op3);
                rb4 = getResources().getString(R.string.quest4_op4);
                break;

            case 5:
                question = getResources().getString(R.string.quest5);
                answer = getResources().getString(R.string.quest5_answer);
                img_enabled = getResources().getString(R.string.quest5_img);
                rb1 = getResources().getString(R.string.quest5_op1);
                rb2 = getResources().getString(R.string.quest5_op2);
                rb3 = getResources().getString(R.string.quest5_op3);
                rb4 = getResources().getString(R.string.quest5_op4);
                break;

            case 6:
                question = getResources().getString(R.string.quest6);
                answer = getResources().getString(R.string.quest6_answer);
                img_enabled = getResources().getString(R.string.quest6_img);
                rb1 = getResources().getString(R.string.quest6_op1);
                rb2 = getResources().getString(R.string.quest6_op2);
                rb3 = getResources().getString(R.string.quest6_op3);
                rb4 = getResources().getString(R.string.quest6_op4);
                break;

            case 7:
                question = getResources().getString(R.string.quest7);
                answer = getResources().getString(R.string.quest7_answer);
                img_enabled = getResources().getString(R.string.quest7_img);
                rb1 = getResources().getString(R.string.quest7_op1);
                rb2 = getResources().getString(R.string.quest7_op2);
                rb3 = getResources().getString(R.string.quest7_op3);
                rb4 = getResources().getString(R.string.quest7_op4);
                break;

            case 8:
                question = getResources().getString(R.string.quest8);
                answer = getResources().getString(R.string.quest8_answer);
                img_enabled = getResources().getString(R.string.quest8_img);
                Picasso.with(img.getContext())
                        .load(R.drawable.img1_encyclopedia)
                        .into(img);
                rb1 = getResources().getString(R.string.quest8_op1);
                rb2 = getResources().getString(R.string.quest8_op2);
                rb3 = getResources().getString(R.string.quest8_op3);
                rb4 = getResources().getString(R.string.quest8_op4);
                break;

            case 9:
                question = getResources().getString(R.string.quest9);
                answer = getResources().getString(R.string.quest9_answer);
                img_enabled = getResources().getString(R.string.quest9_img);
                Picasso.with(img.getContext())
                        .load(R.drawable.img3_encyclopedia)
                        .into(img);
                rb1 = getResources().getString(R.string.quest9_op1);
                rb2 = getResources().getString(R.string.quest9_op2);
                rb3 = getResources().getString(R.string.quest9_op3);
                rb4 = getResources().getString(R.string.quest9_op4);
                break;

            case 10:
                question = getResources().getString(R.string.quest10);
                answer = getResources().getString(R.string.quest10_answer);
                img_enabled = getResources().getString(R.string.quest10_img);
                Picasso.with(img.getContext())
                        .load(R.drawable.img6_encyclopedia)
                        .into(img);
                rb1 = getResources().getString(R.string.quest10_op1);
                rb2 = getResources().getString(R.string.quest10_op2);
                rb3 = getResources().getString(R.string.quest10_op3);
                rb4 = getResources().getString(R.string.quest10_op4);
                break;
        }
    }

    private void getFont() {
        tf= Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
    }

    private void initUI() {
        question_ui.setText(question);
        switch (img_enabled) {
            case "true":
                img.setVisibility(View.VISIBLE);
                break;

            case "false":
                img.setVisibility(View.GONE);
                break;
        }
        rb1_ui.setTypeface(tf);
        rb1_ui.setText(rb1);
        rb1_ui.setOnClickListener(this);
        rb2_ui.setTypeface(tf);
        rb2_ui.setText(rb2);
        rb2_ui.setOnClickListener(this);
        rb3_ui.setTypeface(tf);
        rb3_ui.setText(rb3);
        rb3_ui.setOnClickListener(this);
        rb4_ui.setTypeface(tf);
        rb4_ui.setText(rb4);
        rb4_ui.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_quiz1:
                rb1_ui.setChecked(true);
                rb2_ui.setChecked(false);
                rb3_ui.setChecked(false);
                rb4_ui.setChecked(false);
                usr_answer = 1;
                setAnswer();
                break;

            case R.id.rb_quiz2:
                rb1_ui.setChecked(false);
                rb2_ui.setChecked(true);
                rb3_ui.setChecked(false);
                rb4_ui.setChecked(false);
                usr_answer = 2;
                setAnswer();
                break;

            case R.id.rb_quiz3:
                rb1_ui.setChecked(false);
                rb2_ui.setChecked(false);
                rb3_ui.setChecked(true);
                rb4_ui.setChecked(false);
                usr_answer = 3;
                setAnswer();
                break;

            case R.id.rb_quiz4:
                rb1_ui.setChecked(false);
                rb2_ui.setChecked(false);
                rb3_ui.setChecked(false);
                rb4_ui.setChecked(true);
                usr_answer = 4;
                setAnswer();
                break;
        }
    }

    private void setAnswer() {
        Bundle bundle = new Bundle();
        Bundle getBundle = getActivity().getIntent().getExtras();
        if (Integer.parseInt(answer) == usr_answer) {
            if (getBundle != null) {
                getActivity().getIntent().removeExtra("UserAnswer");
                bundle.clear();
            }
            bundle.putString("UserAnswer", "true");
            Intent intent = getActivity().getIntent();
            intent.putExtras(bundle);
        } else {
            if (getBundle != null) {
                getActivity().getIntent().removeExtra("UserAnswer");
                bundle.clear();
            }
            bundle.putString("UserAnswer", "false");
            Intent intent = getActivity().getIntent();
            intent.putExtras(bundle);
        }
    }

    @Override
    public void onDestroy() {
        unbindDrawables(v);
        if (v != null) {
            v = null;
        }
        System.gc();
        super.onDestroy();
    }

    protected void unbindDrawables(View view) {
        if (view != null) {
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
                }
                ((ViewGroup) view).removeAllViews();
            }
        }
    }
}
