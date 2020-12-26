package com.wikiapp;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MenuActivity extends AppCompatActivity {

    String getCardID, title;
    CollapsingToolbarLayout ctl;
    ImageView cover;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    View v;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = getLayoutInflater().inflate(R.layout.activity_menu, null);
        setContentView(v);

        // initializing toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_card_menu_activity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ctl = findViewById(R.id.ctl_card_menu_activity);
        cover = findViewById(R.id.ctl_card_menu_activity_cover);

        // starting initializing fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        // receiving Card ID
        getCardID();

        // initializing UI
        if (getCardID != null) {
            switch (getCardID) {
                case "m1":
                    initRes1();
                    break;

                case "m2":
                    initRes2();
                    break;

                case "m3":
                    initRes3();
                    break;

                case "m4":
                    initRes4();
                    break;
            }
            setUI();
        } else {
            Log.d(getResources().getString(R.string.app_package_name), "Cant find $getCardID Bundled Data");
            finish();
        }
    }

    private void setUI() {
        Typeface tf= Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
        ctl.setExpandedTitleTypeface(tf);
        ctl.setCollapsedTitleTypeface(tf);
        ctl.setTitle(title);
    }

    private void initRes1() {
        title = getResources().getString(R.string.card1_menu_title);
        ctl.setContentScrimColor(getResources().getColor(R.color.colorScrim_menu_card1));
        Picasso.with(cover.getContext())
                .load(R.drawable.c1)
                .into(cover);
        fragmentTransaction.replace(R.id.fragment_menu_container, new Menu_1_Card()).commit();
    }

    private void initRes2() {
        title = getResources().getString(R.string.card2_menu_title);
        ctl.setContentScrimColor(getResources().getColor(R.color.colorScrim_menu_card2));
        Picasso.with(cover.getContext())
                .load(R.drawable.c2)
                .into(cover);
        fragmentTransaction.replace(R.id.fragment_menu_container, new Menu_2_Card()).commit();
    }

    private void initRes3() {
        title = getResources().getString(R.string.card3_menu_title);
        ctl.setContentScrimColor(getResources().getColor(R.color.colorScrim_menu_card3));
        Picasso.with(cover.getContext())
                .load(R.drawable.c3)
                .into(cover);
        fragmentTransaction.replace(R.id.fragment_menu_container, new Menu_3_Card()).commit();
    }

    private void initRes4() {
        title = getResources().getString(R.string.card4_menu_title);
        ctl.setContentScrimColor(getResources().getColor(R.color.colorScrim_menu_card4));
        Picasso.with(cover.getContext())
                .load(R.drawable.c4)
                .into(cover);
        fragmentTransaction.replace(R.id.fragment_menu_container, new Menu_4_Card()).commit();
    }

    private void getCardID() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            getCardID = bundle.getString("cardMenuID");
        } else {
            Log.d(getResources().getString(R.string.app_package_name), "Value $getCardID is null!");
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
