package com.wikiapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;



public class QuizStartPage extends Fragment {

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.quiz_startpage, container, false);

        initUI();

        return v;
    }

    private void initUI() {
        TextView txt1 = v.findViewById(R.id.txt1_quiz_startpage);
        Typeface tf1= Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
        txt1.setTypeface(tf1);

        TextView txt2= v.findViewById(R.id.txt2_quiz_startpage);
        Typeface tf2= Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
        txt2.setTypeface(tf2);
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
