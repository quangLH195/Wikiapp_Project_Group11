package com.wikiapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

public class Splashscreen extends Activity {

    Thread splashTread;
    View v;
    Intent intent;

    @SuppressLint("InflateParams")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = getLayoutInflater().inflate(R.layout.activity_splashscreen, null);
        setContentView(v);
        initUI();
        initializeComponent();
    }

    private void initUI() {
        TextView txt1 = v.findViewById(R.id.txt_splashscreen);
        Typeface tf1= Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
        txt1.setTypeface(tf1);
    }

    private void initializeComponent() {
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 1500) {
                        sleep(100);
                        waited += 100;
                    }
                    intent = new Intent(Splashscreen.this, Dashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    Log.d(getResources().getString(R.string.app_package_name), "InterruptedException e [User interupted the starting process or an error happened when starting Application?]");
                }
            }
        };
        splashTread.start();
    }

    @Override
    public void onBackPressed() {
        Log.d(getResources().getString(R.string.app_package_name), "Splashscreen is in process!, can't do onBackPressed() function!");
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
