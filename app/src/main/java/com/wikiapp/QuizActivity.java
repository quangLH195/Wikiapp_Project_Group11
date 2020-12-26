package com.wikiapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    View v;
    int question_number, checkAnswer;
    Button btn_quiz;
    CollapsingToolbarLayout ctl;
    int score;
    ViewPager mViewPager;
    FloatingActionButton fab, fab_startpage;
    TextView fab_num;
    public static final String Quiz_Database = "QuizScoreDatabase" ;
    public static final String Quiz_Highscore1 = "highScore1";
    public static final String Quiz_Highscore2 = "highScore2";
    public static final String Quiz_Highscore3 = "highScore3";
    SharedPreferences sharedpreferences;
    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = getLayoutInflater().inflate(R.layout.activity_quiz, null);
        setContentView(v);

        question_number = 0;
        score = 0;
        checkAnswer = 0;

        // initializing toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_quiz_activity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ctl = findViewById(R.id.ctl_quiz_activity);
        ctl.setTitle(getResources().getString(R.string.card6_menu_title));

        // initializing Quiz ViewPager
        mViewPager = v.findViewById(R.id.quiz_pager);
        mViewPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager()));

        getFont();

        initUI();
    }

    private void getFont() {
        tf= Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
    }

    public class SamplePagerAdapter extends FragmentPagerAdapter {

        SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new QuizStartPage();

                case 1:
                    return new QuizQuestionFactory();

                case 2:
                    return new QuizQuestionFactory();

                case 3:
                    return new QuizQuestionFactory();

                case 4:
                    return new QuizQuestionFactory();

                case 5:
                    return new QuizQuestionFactory();

                case 6:
                    return new QuizQuestionFactory();

                case 7:
                    return new QuizQuestionFactory();

                case 8:
                    return new QuizQuestionFactory();

                case 9:
                    return new QuizQuestionFactory();

                case 10:
                    return new QuizQuestionFactory();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 11;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

    }

    private void initCTL() {
        ctl.setTitle(getResources().getString(R.string.title_quiz_activity));
    }

    private void initCTL2() {
        ctl.setTitle(getResources().getString(R.string.title_quiz_activity2) + " " + String.valueOf(question_number));
    }

    private void initUI() {
        btn_quiz = v.findViewById(R.id.btn_quiz);
        btn_quiz.setOnClickListener(this);
        btn_quiz.setTypeface(tf);
        btn_quiz.setText(getResources().getString(R.string.btn_start_quiz));
        fab = v.findViewById(R.id.quiz_fab);
        fab_startpage = v.findViewById(R.id.quiz_fab_startpage);
        fab_num = v.findViewById(R.id.quiz_fab_num);
        fab_num.setTypeface(tf);
        fab_startpage.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.INVISIBLE);
        fab_startpage.setOnClickListener(this);
        fab.setOnClickListener(this);

        ctl.setExpandedTitleTypeface(tf);

        showFABsrc();
    }

    private void initFABnum() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fab_num.setText(String.valueOf(question_number));
            }
        }, 500);
    }

    private void animateFAB() {
        fab.hide();
        fab_num.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fab.show();
                fab_num.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

    private void hideFABsrc() {
        fab_startpage.hide();
        fab_startpage.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
    }

    private void showFABsrc() {
        fab_startpage.hide();
        fab_startpage.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fab_startpage.show();
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_quiz:
                if (question_number == 0) {
                    btn_quiz.setText(getResources().getString(R.string.btn_next_quiz));
                    mViewPager.setCurrentItem(getItem(+1), true);
                    question_number++;
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                        initCTL();
                        hideFABsrc();
                        initFABnum();
                        animateFAB();
                    } else {
                        initCTL2();
                    }
                } else {
                    if (question_number == 10) {
                        registerInput();
                        if (checkAnswer == 1) {
                            resetNumberHistory();
                            showQuizResultDialog();
                        } else if (checkAnswer == 2) {
                            Toast.makeText(QuizActivity.this, getResources().getString(R.string.warn_quiz_noanswer), Toast.LENGTH_SHORT).show();
                        }
                        checkAnswer = 0;
                    } else {
                        btn_quiz.setText(getResources().getString(R.string.btn_next_quiz));
                        registerInput();
                        if (checkAnswer == 1) {
                            question_number++;
                            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                                initCTL();
                                initFABnum();
                                animateFAB();
                            } else {
                                initCTL2();
                            }
                            mViewPager.setCurrentItem(getItem(+1), true);
                        } else if (checkAnswer == 2) {
                            Toast.makeText(QuizActivity.this, getResources().getString(R.string.warn_quiz_noanswer), Toast.LENGTH_SHORT).show();
                        }
                        if (question_number == 10) {
                            btn_quiz.setText(getResources().getString(R.string.btn_submit_quiz));
                        }
                    }
                    checkAnswer = 0;
                }
                break;

            case R.id.quiz_fab_startpage:
                showAboutQuizDialog();
                break;

            case R.id.quiz_fab:
                Toast.makeText(QuizActivity.this, getResources().getString(R.string.quiz_question_info) + " " + String.valueOf(question_number), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void resetNumberHistory() {
        for (int i = 0; i < 10; i++) {
            Bundle resetBundle = new Bundle();
            resetBundle.putString("num" + String.valueOf(i + 1), "");
        }
    }

    private int getItem (int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void registerInput() {
        if (question_number != 0) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                checkAnswer = 1;
                String checkAnswer = bundle.getString("UserAnswer");
                if (checkAnswer != null) {
                    if (checkAnswer.equals("true")) {
                        score += 10;
                    }
                }
                getIntent().removeExtra("UserAnswer");
                bundle.clear();
            } else {
                checkAnswer = 2;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (question_number == 0) {
            finish();
        } else {
            showExitWarningDialog();
        }
    }

    private void showExitWarningDialog() {
        final Dialog dialog = new Dialog(QuizActivity.this, R.style.Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_quiz_exit_warning);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                }
                return true;
            }
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView txt1 = dialog.findViewById(R.id.txt1_dialog_quiz_exit_warning);
        TextView txt2 = dialog.findViewById(R.id.txt2_dialog_quiz_exit_warning);
        txt1.setTypeface(tf);
        txt2.setTypeface(tf);
        Button btn_exit = dialog.findViewById(R.id.btn_exit_quiz);
        Button btn_decline_exit = dialog.findViewById(R.id.btn_decline_exit_quiz);
        btn_exit.setTypeface(tf);
        btn_decline_exit.setTypeface(tf);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_decline_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showAboutQuizDialog() {
        final Dialog dialog = new Dialog(QuizActivity.this, R.style.Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_about_quiz);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                }
                return true;
            }
        });
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // initializing score textview & card
        TextView noscore_history_placeholder = dialog.findViewById(R.id.quiz_scoretext_nohistory);
        TextView score_text1 = dialog.findViewById(R.id.quiz_score1_text);
        TextView score_text2 = dialog.findViewById(R.id.quiz_score2_text);
        TextView score_text3 = dialog.findViewById(R.id.quiz_score3_text);
        TextView txt1 = dialog.findViewById(R.id.txt1_dialog_about_quiz);
        TextView txt2 = dialog.findViewById(R.id.txt2_dialog_about_quiz);
        noscore_history_placeholder.setTypeface(tf);
        score_text1.setTypeface(tf);
        score_text2.setTypeface(tf);
        score_text3.setTypeface(tf);
        txt1.setTypeface(tf);
        txt2.setTypeface(tf);
        LinearLayout frame_score = dialog.findViewById(R.id.quiz_frame_score);
        LinearLayout score_card1 = dialog.findViewById(R.id.quiz_score1_card);
        LinearLayout score_card2 = dialog.findViewById(R.id.quiz_score2_card);
        LinearLayout score_card3 = dialog.findViewById(R.id.quiz_score3_card);

        // fetching score from database
        sharedpreferences = getSharedPreferences(Quiz_Database, Context.MODE_PRIVATE);
        String highScore1 = sharedpreferences.getString(Quiz_Highscore1, "");
        String highScore2 = sharedpreferences.getString(Quiz_Highscore2, "");
        String highScore3 = sharedpreferences.getString(Quiz_Highscore3, "");

        // setting UI
        if (highScore1.equals("") && highScore2.equals("") && highScore3.equals("")) {
            noscore_history_placeholder.setVisibility(View.VISIBLE);
        } else if (!highScore1.equals("") && highScore2.equals("") && highScore3.equals("")) {
            frame_score.setVisibility(View.VISIBLE);
            score_text1.setText(highScore1);
            score_card1.setVisibility(View.VISIBLE);
        } else if (!highScore1.equals("") && !highScore2.equals("") && highScore3.equals("")) {
            frame_score.setVisibility(View.VISIBLE);
            score_text1.setText(highScore1);
            score_card1.setVisibility(View.VISIBLE);
            score_text2.setText(highScore2);
            score_card2.setVisibility(View.VISIBLE);
        } else if (!highScore1.equals("") && !highScore2.equals("") && !highScore3.equals("")) {
            frame_score.setVisibility(View.VISIBLE);
            score_text1.setText(highScore1);
            score_card1.setVisibility(View.VISIBLE);
            score_text2.setText(highScore2);
            score_card2.setVisibility(View.VISIBLE);
            score_text3.setText(highScore3);
            score_card3.setVisibility(View.VISIBLE);
        }

        dialog.show();
    }

    private void showQuizResultDialog() {
        final Dialog dialog = new Dialog(QuizActivity.this, R.style.Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_result_quiz);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView score_ui = dialog.findViewById(R.id.quiz_result_num);
        TextView txt1 = dialog.findViewById(R.id.txt1_dialog_result_quiz);
        TextView txt2 = dialog.findViewById(R.id.txt2_dialog_result_quiz);
        TextView txt3 = dialog.findViewById(R.id.txt3_dialog_result_quiz);
        score_ui.setTypeface(tf);
        txt1.setTypeface(tf);
        txt2.setTypeface(tf);
        txt3.setTypeface(tf);
        score_ui.setText(String.valueOf(score));
        Button btn_restart = dialog.findViewById(R.id.btn_restart_quiz);
        Button btn_finish = dialog.findViewById(R.id.btn_finish_quiz);
        btn_restart.setTypeface(tf);
        btn_finish.setTypeface(tf);

        sharedpreferences = getSharedPreferences(Quiz_Database, Context.MODE_PRIVATE);
        final String highScore1 = sharedpreferences.getString(Quiz_Highscore1, "");
        final String highScore2 = sharedpreferences.getString(Quiz_Highscore2, "");
        final String highScore3 = sharedpreferences.getString(Quiz_Highscore3, "");

        // setting highscore badge
        LinearLayout highscore_badge = dialog.findViewById(R.id.highscore_badge);
        if (highScore1.equals("") || score > Integer.parseInt(highScore1)) {
            highscore_badge.setVisibility(View.VISIBLE);
        }

        btn_restart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void onClick(View v) {
                // saving score to database
                SharedPreferences.Editor editor = sharedpreferences.edit();

                if (highScore1.equals("") && highScore2.equals("") && highScore3.equals("")) {
                    editor.putString(Quiz_Highscore1, String.valueOf(score));
                    editor.commit();
                } else if (!highScore1.equals("") && highScore2.equals("") && highScore3.equals("")) {
                    if (score > Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore2, highScore1);
                        editor.putString(Quiz_Highscore1, String.valueOf(score));
                    } else if (score < Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore2, String.valueOf(score));
                    }
                    editor.commit();
                } else if (!highScore1.equals("") && !highScore2.equals("") && highScore3.equals("")) {
                    if (score > Integer.parseInt(highScore2) && score > Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, highScore2);
                        editor.putString(Quiz_Highscore2, highScore1);
                        editor.putString(Quiz_Highscore1, String.valueOf(score));
                    } else if (score > Integer.parseInt(highScore2) && score < Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, highScore2);
                        editor.putString(Quiz_Highscore2, String.valueOf(score));
                    } else if (score < Integer.parseInt(highScore2) && score < Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, String.valueOf(score));
                    }
                    editor.commit();
                } else if (!highScore1.equals("") && !highScore2.equals("") && !highScore3.equals("")) {
                    if (score > Integer.parseInt(highScore3) && score > Integer.parseInt(highScore2) && score > Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, highScore2);
                        editor.putString(Quiz_Highscore2, highScore1);
                        editor.putString(Quiz_Highscore1, String.valueOf(score));
                    } else if (score > Integer.parseInt(highScore3) && score > Integer.parseInt(highScore2) && score < Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, highScore2);
                        editor.putString(Quiz_Highscore2, String.valueOf(score));
                    } else if (score > Integer.parseInt(highScore3) && score < Integer.parseInt(highScore2) && score < Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, String.valueOf(score));
                    }
                    editor.commit();
                }

                // resetting score
                score = 0;

                // responding action
                Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // saving score to database
                SharedPreferences.Editor editor = sharedpreferences.edit();

                if (highScore1.equals("") && highScore2.equals("") && highScore3.equals("")) {
                    editor.putString(Quiz_Highscore1, String.valueOf(score));
                    editor.commit();
                } else if (!highScore1.equals("") && highScore2.equals("") && highScore3.equals("")) {
                    if (score > Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore2, highScore1);
                        editor.putString(Quiz_Highscore1, String.valueOf(score));
                    } else if (score < Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore2, String.valueOf(score));
                    }
                    editor.commit();
                } else if (!highScore1.equals("") && !highScore2.equals("") && highScore3.equals("")) {
                    if (score > Integer.parseInt(highScore2) && score > Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, highScore2);
                        editor.putString(Quiz_Highscore2, highScore1);
                        editor.putString(Quiz_Highscore1, String.valueOf(score));
                    } else if (score > Integer.parseInt(highScore2) && score < Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, highScore2);
                        editor.putString(Quiz_Highscore2, String.valueOf(score));
                    } else if (score < Integer.parseInt(highScore2) && score < Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, String.valueOf(score));
                    }
                    editor.commit();
                } else if (!highScore1.equals("") && !highScore2.equals("") && !highScore3.equals("")) {
                    if (score > Integer.parseInt(highScore3) && score > Integer.parseInt(highScore2) && score > Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, highScore2);
                        editor.putString(Quiz_Highscore2, highScore1);
                        editor.putString(Quiz_Highscore1, String.valueOf(score));
                    } else if (score > Integer.parseInt(highScore3) && score > Integer.parseInt(highScore2) && score < Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, highScore2);
                        editor.putString(Quiz_Highscore2, String.valueOf(score));
                    } else if (score > Integer.parseInt(highScore3) && score < Integer.parseInt(highScore2) && score < Integer.parseInt(highScore1)) {
                        editor.putString(Quiz_Highscore3, String.valueOf(score));
                    }
                    editor.commit();
                }

                // resetting score
                score = 0;
                finish();
            }
        });

        dialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onDestroy() {
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
