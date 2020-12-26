package com.wikiapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EncyclopediaActivity extends AppCompatActivity implements View.OnClickListener{

    String orientation, getCardID, title, header1, header2, header3, header4, founder, description, gallery_link, detail_link;
    TextView ctlTitle, ui_header1, ui_header2, ui_header3, ui_header4, ui_founder, ui_description;
    ImageView cover, img_founder;
    Button btn_detail;
    CardView gallery;
    Intent browserIntent;
    LinearLayout frame_founder;
    FrameLayout frame1_header, frame2_header, frame3_header, frame4_header;
    View v;
    Typeface tf, tf2;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            orientation = "landscape";
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            orientation = "potrait";
        }
    }

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = getLayoutInflater().inflate(R.layout.activity_encyclopedia, null);
        setContentView(v);

        // initializing toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_encyclopediaactivity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        CollapsingToolbarLayout ctl = findViewById(R.id.ctl_encyclopediaactivity);
        ctl.setTitleEnabled(false);
        ctlTitle = findViewById(R.id.ctl_encyclopediaactivity_title);
        cover = findViewById(R.id.ctl_encyclopediaactivity_cover);

        // initializing content
        ui_header1 = findViewById(R.id.encyclopedia_header1);
        ui_header2 = findViewById(R.id.encyclopedia_header2);
        ui_header3 = findViewById(R.id.encyclopedia_header3);
        ui_header4 = findViewById(R.id.encyclopedia_header4);
        ui_founder = findViewById(R.id.encyclopedia_founder);
        ui_description = findViewById(R.id.encyclopedia_description);
        img_founder = findViewById(R.id.img_encyclopedia_founder);
        gallery = findViewById(R.id.gallery_card);
        btn_detail = findViewById(R.id.btn_detail);
        frame_founder = findViewById(R.id.frame_founder);
        frame1_header = findViewById(R.id.frame_header1_encyclopedia);
        frame2_header = findViewById(R.id.frame_header2_encyclopedia);
        frame3_header = findViewById(R.id.frame_header3_encyclopedia);
        frame4_header = findViewById(R.id.frame_header4_encyclopedia);

        // receiving Card ID
        getCardID();

        // initializing font
        getFont();

        // initializing UI
        if (getCardID != null) {
            switch (getCardID) {
                case "e1":
                    initRes1();
                    break;

                case "e2":
                    initRes2();
                    break;

                case "e3":
                    initRes3();
                    break;

                case "e4":
                    initRes4();
                    break;

                case "e5":
                    initRes5();
                    break;

                case "e6":
                    initRes6();
                    break;

                case "e7":
                    initRes7();
                    break;

                case "e8":
                    initRes8();
                    break;

                case "e9":
                    initRes9();
                    break;

                case "e10":
                    initRes10();
                    break;

                case "e11":
                    initRes11();
                    break;

                case "e12":
                    initRes12();
                    break;

                case "e13":
                    initRes13();
                    break;

                case "e14":
                    initRes14();
                    break;

                case "e15":
                    initRes15();
                    break;

                case "e16":
                    initRes16();
                    break;

                case "e17":
                    initRes17();
                    break;

                case "e18":
                    initRes18();
                    break;

                case "e19":
                    initRes19();
                    break;

                case "e20":
                    initRes20();
                    break;
            }
            checkData();
            setUI();
        } else {
            Log.d(getResources().getString(R.string.app_package_name), "Cant find $getCardID Bundled Data");
        }
    }

    private void getFont() {
        tf = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
        tf2 = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/RobotoLight.ttf");
    }

    private void checkData() {
        if (header1.equals("-")) {
            frame1_header.setVisibility(View.GONE);
        }
        if (header2.equals("-")) {
            frame2_header.setVisibility(View.GONE);
        }
        if (header3.equals("-")) {
            frame3_header.setVisibility(View.GONE);
        }
        if (header4.equals("-")) {
            frame4_header.setVisibility(View.GONE);
        }
        if (founder.equals("-")) {
            frame_founder.setVisibility(View.GONE);
        }
    }

    private void setUI() {
        ctlTitle.setTypeface(tf);
        ctlTitle.setText(title);
        ui_header1.setTypeface(tf);
        ui_header1.setText(header1);
        ui_header2.setTypeface(tf);
        ui_header2.setText(header2);
        ui_header3.setTypeface(tf);
        ui_header3.setText(header3);
        ui_header4.setTypeface(tf);
        ui_header4.setText(header4);
        ui_founder.setTypeface(tf);
        ui_founder.setText(founder);
        ui_description.setTypeface(tf2);
        ui_description.setText(description);
        gallery.setOnClickListener(this);
        btn_detail.setOnClickListener(this);
        TextView txt1 = findViewById(R.id.encyclopedia_txt1);
        txt1.setTypeface(tf);
        TextView txt2 = findViewById(R.id.encyclopedia_txt2);
        txt2.setTypeface(tf);
        TextView txt3 = findViewById(R.id.encyclopedia_txt3);
        txt3.setTypeface(tf);
        TextView txt4 = findViewById(R.id.encyclopedia_txt4);
        txt4.setTypeface(tf);
        TextView txt5 = findViewById(R.id.encyclopedia_txt5);
        txt5.setTypeface(tf);
        TextView txt6 = findViewById(R.id.encyclopedia_txt6);
        txt6.setTypeface(tf);
        TextView txt7 = findViewById(R.id.encyclopedia_txt7);
        txt7.setTypeface(tf);
        TextView txt8 = findViewById(R.id.encyclopedia_txt8);
        txt8.setTypeface(tf);
        btn_detail.setTypeface(tf);
    }

    private void initRes1() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img1_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder1_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card1_ens_title);
        header1 = getResources().getString(R.string.card1_ens_header1);
        header2 = getResources().getString(R.string.card1_ens_header2);
        header3 = getResources().getString(R.string.card1_ens_header3);
        header4 = getResources().getString(R.string.card1_ens_header4);
        founder = getResources().getString(R.string.card1_ens_founder);
        description = getResources().getString(R.string.card1_ens_description);
    }

    private void initRes2() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img2_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder6_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card2_ens_title);
        header1 = getResources().getString(R.string.card2_ens_header1);
        header2 = getResources().getString(R.string.card2_ens_header2);
        header3 = getResources().getString(R.string.card2_ens_header3);
        header4 = getResources().getString(R.string.card2_ens_header4);
        founder = getResources().getString(R.string.card2_ens_founder);
        description = getResources().getString(R.string.card2_ens_description);
    }

    private void initRes3() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img3_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder6_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card3_ens_title);
        header1 = getResources().getString(R.string.card3_ens_header1);
        header2 = getResources().getString(R.string.card3_ens_header2);
        header3 = getResources().getString(R.string.card3_ens_header3);
        header4 = getResources().getString(R.string.card3_ens_header4);
        founder = getResources().getString(R.string.card3_ens_founder);
        description = getResources().getString(R.string.card3_ens_description);
    }

    private void initRes4() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img4_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder8_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card4_ens_title);
        header1 = getResources().getString(R.string.card4_ens_header1);
        header2 = getResources().getString(R.string.card4_ens_header2);
        header3 = getResources().getString(R.string.card4_ens_header3);
        header4 = getResources().getString(R.string.card4_ens_header4);
        founder = getResources().getString(R.string.card4_ens_founder);
        description = getResources().getString(R.string.card4_ens_description);
    }

    private void initRes5() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img5_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder7_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card5_ens_title);
        header1 = getResources().getString(R.string.card5_ens_header1);
        header2 = getResources().getString(R.string.card5_ens_header2);
        header3 = getResources().getString(R.string.card5_ens_header3);
        header4 = getResources().getString(R.string.card5_ens_header4);
        founder = getResources().getString(R.string.card5_ens_founder);
        description = getResources().getString(R.string.card5_ens_description);
    }

    private void initRes6() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img6_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder6_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card6_ens_title);
        header1 = getResources().getString(R.string.card6_ens_header1);
        header2 = getResources().getString(R.string.card6_ens_header2);
        header3 = getResources().getString(R.string.card6_ens_header3);
        header4 = getResources().getString(R.string.card6_ens_header4);
        founder = getResources().getString(R.string.card6_ens_founder);
        description = getResources().getString(R.string.card6_ens_description);
    }

    private void initRes7() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img7_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder7_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card7_ens_title);
        header1 = getResources().getString(R.string.card7_ens_header1);
        header2 = getResources().getString(R.string.card7_ens_header2);
        header3 = getResources().getString(R.string.card7_ens_header3);
        header4 = getResources().getString(R.string.card7_ens_header4);
        founder = getResources().getString(R.string.card7_ens_founder);
        description = getResources().getString(R.string.card7_ens_description);
    }

    private void initRes8() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img8_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder8_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card8_ens_title);
        header1 = getResources().getString(R.string.card8_ens_header1);
        header2 = getResources().getString(R.string.card8_ens_header2);
        header3 = getResources().getString(R.string.card8_ens_header3);
        header4 = getResources().getString(R.string.card8_ens_header4);
        founder = getResources().getString(R.string.card8_ens_founder);
        description = getResources().getString(R.string.card8_ens_description);
    }

    private void initRes9() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img9_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder9_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card9_ens_title);
        header1 = getResources().getString(R.string.card9_ens_header1);
        header2 = getResources().getString(R.string.card9_ens_header2);
        header3 = getResources().getString(R.string.card9_ens_header3);
        header4 = getResources().getString(R.string.card9_ens_header4);
        founder = getResources().getString(R.string.card9_ens_founder);
        description = getResources().getString(R.string.card9_ens_description);
    }

    private void initRes10() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img10_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder10_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card10_ens_title);
        header1 = getResources().getString(R.string.card10_ens_header1);
        header2 = getResources().getString(R.string.card10_ens_header2);
        header3 = getResources().getString(R.string.card10_ens_header3);
        header4 = getResources().getString(R.string.card10_ens_header4);
        founder = getResources().getString(R.string.card10_ens_founder);
        description = getResources().getString(R.string.card10_ens_description);
    }
    
    private void initRes11() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img11_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder11_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card11_ens_title);
        header1 = getResources().getString(R.string.card11_ens_header1);
        header2 = getResources().getString(R.string.card11_ens_header2);
        header3 = getResources().getString(R.string.card11_ens_header3);
        header4 = getResources().getString(R.string.card11_ens_header4);
        founder = getResources().getString(R.string.card11_ens_founder);
        description = getResources().getString(R.string.card11_ens_description);
    }

    private void initRes12() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img12_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder12_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card12_ens_title);
        header1 = getResources().getString(R.string.card12_ens_header1);
        header2 = getResources().getString(R.string.card12_ens_header2);
        header3 = getResources().getString(R.string.card12_ens_header3);
        header4 = getResources().getString(R.string.card12_ens_header4);
        founder = getResources().getString(R.string.card12_ens_founder);
        description = getResources().getString(R.string.card12_ens_description);
    }

    private void initRes13() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img13_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder13_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card13_ens_title);
        header1 = getResources().getString(R.string.card13_ens_header1);
        header2 = getResources().getString(R.string.card13_ens_header2);
        header3 = getResources().getString(R.string.card13_ens_header3);
        header4 = getResources().getString(R.string.card13_ens_header4);
        founder = getResources().getString(R.string.card13_ens_founder);
        description = getResources().getString(R.string.card13_ens_description);
    }

    private void initRes14() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img14_encyclopedia)
                .into(cover);
        title = getResources().getString(R.string.card14_ens_title);
        header1 = getResources().getString(R.string.card14_ens_header1);
        header2 = getResources().getString(R.string.card14_ens_header2);
        header3 = getResources().getString(R.string.card14_ens_header3);
        header4 = getResources().getString(R.string.card14_ens_header4);
        founder = getResources().getString(R.string.card14_ens_founder);
        description = getResources().getString(R.string.card14_ens_description);
    }

    private void initRes15() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img15_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder15_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card15_ens_title);
        header1 = getResources().getString(R.string.card15_ens_header1);
        header2 = getResources().getString(R.string.card15_ens_header2);
        header3 = getResources().getString(R.string.card15_ens_header3);
        header4 = getResources().getString(R.string.card15_ens_header4);
        founder = getResources().getString(R.string.card15_ens_founder);
        description = getResources().getString(R.string.card15_ens_description);
    }

    private void initRes16() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img16_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder8_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card16_ens_title);
        header1 = getResources().getString(R.string.card16_ens_header1);
        header2 = getResources().getString(R.string.card16_ens_header2);
        header3 = getResources().getString(R.string.card16_ens_header3);
        header4 = getResources().getString(R.string.card16_ens_header4);
        founder = getResources().getString(R.string.card16_ens_founder);
        description = getResources().getString(R.string.card16_ens_description);
    }

    private void initRes17() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img17_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder17_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card17_ens_title);
        header1 = getResources().getString(R.string.card17_ens_header1);
        header2 = getResources().getString(R.string.card17_ens_header2);
        header3 = getResources().getString(R.string.card17_ens_header3);
        header4 = getResources().getString(R.string.card17_ens_header4);
        founder = getResources().getString(R.string.card17_ens_founder);
        description = getResources().getString(R.string.card17_ens_description);
    }

    private void initRes18() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img18_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder18_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card18_ens_title);
        header1 = getResources().getString(R.string.card18_ens_header1);
        header2 = getResources().getString(R.string.card18_ens_header2);
        header3 = getResources().getString(R.string.card18_ens_header3);
        header4 = getResources().getString(R.string.card18_ens_header4);
        founder = getResources().getString(R.string.card18_ens_founder);
        description = getResources().getString(R.string.card18_ens_description);
    }

    private void initRes19() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img19_encyclopedia)
                .into(cover);
        Picasso.with(img_founder.getContext())
                .load(R.drawable.founder19_encyclopedia)
                .into(img_founder);
        title = getResources().getString(R.string.card19_ens_title);
        header1 = getResources().getString(R.string.card19_ens_header1);
        header2 = getResources().getString(R.string.card19_ens_header2);
        header3 = getResources().getString(R.string.card19_ens_header3);
        header4 = getResources().getString(R.string.card19_ens_header4);
        founder = getResources().getString(R.string.card19_ens_founder);
        description = getResources().getString(R.string.card19_ens_description);
    }

    private void initRes20() {
        Picasso.with(cover.getContext())
                .load(R.drawable.img20_encyclopedia)
                .into(cover);
        title = getResources().getString(R.string.card20_ens_title);
        header1 = getResources().getString(R.string.card20_ens_header1);
        header2 = getResources().getString(R.string.card20_ens_header2);
        header3 = getResources().getString(R.string.card20_ens_header3);
        header4 = getResources().getString(R.string.card20_ens_header4);
        founder = getResources().getString(R.string.card20_ens_founder);
        description = getResources().getString(R.string.card20_ens_description);
    }

    private void getCardID() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            getCardID = bundle.getString("cardEncyclopediaID");
        } else {
            Log.d(getResources().getString(R.string.app_package_name), "Value $getCardID is null!");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gallery_card:
                if (getCardID != null) {
                    switch (getCardID) {
                        case "e1":
                            gallery_link = "https://www.google.co.id/search?q=phalaenopsis&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiN8JPDrN_YAhXEo5QKHXAmAasQ_AUICigB&biw=1305&bih=602";
                            break;

                        case "e2":
                            gallery_link = "https://www.google.co.id/search?biw=1305&bih=602&tbm=isch&sa=1&ei=l3BfWrqgM4zt0ASzm4KYAQ&q=dendrobium&oq=dendrobium&gs_l=psy-ab.3...0.0.0.25524.0.0.0.0.0.0.0.0..0.0....0...1c..64.psy-ab..0.0.0....0.XeOOJFEcEto";
                            break;

                        case "e3":
                            gallery_link = "https://www.google.co.id/search?biw=1305&bih=602&tbm=isch&sa=1&ei=s3BfWszDB8HW0gSp_4GYBg&q=cymbidium&oq=cymbidium&gs_l=psy-ab.3..0i67k1j0l9.14002.15839.0.16112.9.5.0.4.4.0.174.645.0j4.4.0....0...1c.1.64.psy-ab..1.8.684....0.E--D-2Xro_c";
                            break;

                        case "e4":
                            gallery_link = "https://www.google.co.id/search?biw=1305&bih=602&tbm=isch&sa=1&ei=y3BfWvTIGI7-0gSexYnYBQ&q=cattleya&oq=catt&gs_l=psy-ab.3.0.0i67k1j0l9.22495.23101.0.24166.4.4.0.0.0.0.216.524.0j2j1.3.0....0...1c.1.64.psy-ab..1.3.521....0.lNxB_RpSVFI";
                            break;

                        case "e5":
                            gallery_link = "https://www.google.co.id/search?biw=1305&bih=602&tbm=isch&sa=1&ei=53BfWo23EsW_0ASPioWgDQ&q=vanda&oq=vanda&gs_l=psy-ab.3..0i67k1j0l2j0i67k1j0j0i67k1j0l4.49032.49504.0.49956.5.5.0.0.0.0.179.341.0j2.2.0....0...1c.1.64.psy-ab..3.2.340....0.M261rREj_OE";
                            break;

                        case "e6":
                            gallery_link = "https://www.google.co.id/search?biw=1305&bih=602&tbm=isch&sa=1&ei=G3FfWoSOO4K40AS6-omgAw&q=oncidium&oq=oncidi&gs_l=psy-ab.3.0.0i67k1j0l9.22421.23164.0.24733.6.6.0.0.0.0.247.586.0j2j1.3.0....0...1c.1.64.psy-ab..3.3.585....0._vL7h_OgRNQ";
                            break;

                        case "e7":
                            gallery_link = "https://www.google.co.id/search?biw=1305&bih=602&tbm=isch&sa=1&ei=7nFfWpLUJczJ0ATWzYb4DA&q=paphiopedilum&oq=paphiopedilum&gs_l=psy-ab.3..0i67k1j0l9.2117.2117.0.2674.1.1.0.0.0.0.192.192.0j1.1.0....0...1c.1.64.psy-ab..0.1.190....0.FdrteD6Vuw8";
                            break;

                        case "e8":
                            gallery_link = "https://www.google.co.id/search?hl=id&tbm=isch&source=hp&biw=1305&bih=651&ei=EVdnWrP3C8q40gS6-JewCA&q=miltonia&oq=miltonia&gs_l=img.3..0l2j0i30k1l8.1365.2691.0.3032.9.8.0.1.1.0.198.721.0j4.4.0....0...1ac.1.64.img..4.5.728.0...0.upwnp0mG5Mo";
                            break;

                        case "e9":
                            gallery_link = "https://www.google.co.id/search?hl=id&biw=1305&bih=651&tbm=isch&sa=1&ei=FVdnWpK9FIGi0gSokbr4Ag&q=epidendrum&oq=epidendrum&gs_l=psy-ab.3..0l3j0i30k1l7.22338.24153.0.24974.10.9.0.1.1.0.249.1191.0j4j2.6.0....0...1c.1.64.psy-ab..3.7.1199...0i67k1.0.3PFwVoX2iro";
                            break;

                        case "e10":
                            gallery_link = "https://www.google.co.id/search?hl=id&biw=1305&bih=651&tbm=isch&sa=1&ei=M1dnWv8rh83SBO6morAH&q=catasetum&oq=catasetum&gs_l=psy-ab.3..0l4j0i30k1l6.17954.19722.0.20181.9.6.0.3.3.0.216.781.0j2j2.4.0....0...1c.1.64.psy-ab..2.7.808...0i67k1.0.XkjIyBAC1mM";
                            break;

                        case "e11":
                            gallery_link = "https://www.google.co.id/search?q=Bulbophyllum&dcr=0&stick=H4sIAAAAAAAAAONgFuLQz9U3MC3ISlLiBLEM0_IscrQss5Ot9JMy83Py0yv184vSE_Myi3Pjk3MSi4sz0zKTE0sy8_OscvLLU4sUUAWLHzEWcAu8_HFPWCp90pqT1xgTucg3S0iFi801rySzpFJIiotHCu5SDQYpLi44jwcANweZmsUAAAA&biw=1305&bih=651&source=lnms&tbm=isch&sa=X&ved=0ahUKEwilweG6ypLZAhUINo8KHdKkAqsQ_AUICigB";
                            break;

                        case "e12":
                            gallery_link = "https://www.google.co.id/search?q=Brassia&dcr=0&biw=1305&bih=651&stick=H4sIAAAAAAAAAONgFuLQz9U3MC3ISlLiBLFMUszKLLUss5Ot9JMy83Py0yv184vSE_Myi3Pjk3MSi4sz0zKTE0sy8_OscvLLU4sUUAWLHzEWcAu8_HFPWCp90pqT1xgTucg3S0iFi801rySzpFJIiotHCu5SDQYpLi44jwcAtbn4W8UAAAA&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiL-7v705LZAhUWSY8KHSr6A8kQ_AUICigB#imgrc=_";
                            break;

                        case "e13":
                            gallery_link = "https://www.google.co.id/search?q=Orchis&dcr=0&biw=1305&bih=651&stick=H4sIAAAAAAAAAONgFuLQz9U3MC3ISlLiBLHMi8zKDLUss5Ot9JMy83Py0yv184vSE_Myi3Pjk3MSi4sz0zKTE0sy8_OscvLLU4sUUAWLHzEWcAu8_HFPWCp90pqT1xgTucg3S0iFi801rySzpFJIiotHCu5SDQYpLi44jwcAvsQxssUAAAA&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiksqDF1ZLZAhXLpo8KHSgZB7IQ_AUICigB";
                            break;

                        case "e14":
                            gallery_link = "https://www.google.co.id/search?q=Cypripedioideae&dcr=0&biw=1305&bih=651&stick=H4sIAAAAAAAAAONgFuLQz9U3MC3ISlLiBLEMc9JyzLUss5Ot9JMy83Py0yv184vSE_Myi3Pjk3MSi4sz0zKTE0sy8_OscvLLU4sUUAWLHzEWcAu8_HFPWCp90pqT1xgTucg3S0iFi801rySzpFJIiotHCu5SDQYpLi44jwcA_diYEsUAAAA&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiYmbPC15LZAhWHRo8KHaieAuQQ_AUICigB";
                            break;

                        case "e15":
                            gallery_link = "https://www.google.co.id/search?q=vanilla+plant&dcr=0&biw=1305&bih=651&stick=H4sIAAAAAAAAAONgFuLQz9U3MC3ISlLiBLFM0pKMM7Qss5Ot9JMy83Py0yv184vSE_Myi3Pjk3MSi4sz0zKTE0sy8_OscvLLU4sUUAWLHzEWcAu8_HFPWCp90pqT1xgTucg3S0iFi801rySzpFJIiotHCu5SDQYpLi44jwcAdpmQa8UAAAA&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjZ9onC2JLZAhVFQI8KHZPLAKAQ_AUICigB";
                            break;

                        case "e16":
                            gallery_link = "https://www.google.co.id/search?q=Laelia&dcr=0&stick=H4sIAAAAAAAAAONgFuLQz9U3MC3ISlLiBLGMsyrTTbQss5Ot9JMy83Py0yv184vSE_Myi3Pjk3MSi4sz0zKTE0sy8_OscvLLU4sUUAWLHzEWcAu8_HFPWCp90pqT1xgTucg3S0iFi801rySzpFJIiotHCu5SDQYpLi44jwcA-BWYxcUAAAA&source=lnms&tbm=isch&sa=X&ved=0ahUKEwistOCFjZPZAhXFwI8KHSu_BAYQ_AUICigB";
                            break;

                        case "e17":
                            gallery_link = "https://www.google.co.id/search?q=Zygopetalum&dcr=0&stick=H4sIAAAAAAAAAONgFuLQz9U3MC3ISlLiBLEM4_MK0rUss5Ot9JMy83Py0yv184vSE_Myi3Pjk3MSi4sz0zKTE0sy8_OscvLLU4sUUAWLHzEWcAu8_HFPWCp90pqT1xgTucg3S0iFi801rySzpFJIiotHCu5SDQYpLi44jwcA9hbRhcUAAAA&source=lnms&tbm=isch&sa=X&ved=0ahUKEwik0rOvkpPZAhXGto8KHf_5CxEQ_AUICigB";
                            break;

                        case "e18":
                            gallery_link = "https://www.google.co.id/search?q=Ludisia&dcr=0&stick=H4sIAAAAAAAAAONgFuLQz9U3MC3ISlLiArHMTAxyLA21LLOTrfSTMvNz8tMr9fOL0hPzMotz45NzEouLM9MykxNLMvPzrHLyy1OLFFAFix8xFnALvPxxT1gqfdKak9cYE7nIN0tIhYvNNa8ks6RSSIqLRwruVA0GKS4uOI8HAAPXMCzGAAAA&source=lnms&tbm=isch&sa=X&ved=0ahUKEwi34pmolpPZAhVFqY8KHUBDAWcQ_AUICigB";
                            break;

                        case "e19":
                            gallery_link = "https://www.google.co.id/search?q=Masdevallia&dcr=0&stick=H4sIAAAAAAAAAONgFuLQz9U3MC3ISlLiBLEM00pyirUss5Ot9JMy83Py0yv184vSE_Myi3Pjk3MSi4sz0zKTE0sy8_OscvLLU4sUUAWLHzEWcAu8_HFPWCp90pqT1xgTucg3S0iFi801rySzpFJIiotHCu5SDQYpLi44jwcAyrnJ88UAAAA&source=lnms&tbm=isch&sa=X&ved=0ahUKEwie8ObjspPZAhWELY8KHeuCAdsQ_AUICigB";
                            break;

                        case "e20":
                            gallery_link = "https://www.google.co.id/search?q=Vanili&dcr=0&stick=H4sIAAAAAAAAAONgFuLQz9U3MC3ISlLiArGMCsqr4k21LLOTrfSTMvNz8tMr9fOL0hPzMotz45NzEouLM9MykxNLMvPzrHLyy1OLFFAFix8xFnALvPxxT1gqfdKak9cYE7nIN0tIhYvNNa8ks6RSSIqLRwruVA0GKS4uOI8HAM1BrabGAAAA&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiHrJuws5PZAhVGr48KHfUBA-MQ_AUICigB#imgrc=yPUB8jVtzhwzdM:";
                            break;
                    }
                    launchGalleryBrowser();
                } else {
                    Log.d(getResources().getString(R.string.app_package_name), "Cant find $getCardID Bundled Data");
                }
                break;

            case R.id.btn_detail:
                if (getCardID != null) {
                    switch (getCardID) {
                        case "e1":
                            detail_link = "https://en.wikipedia.org/wiki/Phalaenopsis";
                            break;

                        case "e2":
                            detail_link = "https://id.wikipedia.org/wiki/Dendrobium";
                            break;

                        case "e3":
                            detail_link = "https://id.wikipedia.org/wiki/Cymbidium";
                            break;

                        case "e4":
                            detail_link = "https://id.wikipedia.org/wiki/Cattleya";
                            break;

                        case "e5":
                            detail_link = "https://en.wikipedia.org/wiki/Vanda";
                            break;

                        case "e6":
                            detail_link = "https://id.wikipedia.org/wiki/Oncidium";
                            break;

                        case "e7":
                            detail_link = "https://en.wikipedia.org/wiki/Paphiopedilum";
                            break;

                        case "e8":
                            detail_link = "https://en.wikipedia.org/wiki/Miltonia";
                            break;

                        case "e9":
                            detail_link = "https://en.wikipedia.org/wiki/Epidendrum";
                            break;

                        case "e10":
                            detail_link = "https://en.wikipedia.org/wiki/Catasetum";
                            break;

                        case "e11":
                            detail_link = "https://en.wikipedia.org/wiki/Bulbophyllum";
                            break;

                        case "e12":
                            detail_link = "https://en.wikipedia.org/wiki/Brassia";
                            break;

                        case "e13":
                            detail_link = "https://en.wikipedia.org/wiki/Orchis";
                            break;

                        case "e14":
                            detail_link = "https://id.wikipedia.org/wiki/Cypripedioideae";
                            break;

                        case "e15":
                            detail_link = "https://en.wikipedia.org/wiki/Vanilla_(genus)";
                            break;

                        case "e16":
                            detail_link = "https://en.wikipedia.org/wiki/Laelia";
                            break;

                        case "e17":
                            detail_link = "https://en.wikipedia.org/wiki/Zygopetalum";
                            break;

                        case "e18":
                            detail_link = "https://en.wikipedia.org/wiki/Ludisia";
                            break;

                        case "e19":
                            detail_link = "https://en.wikipedia.org/wiki/Masdevallia";
                            break;

                        case "e20":
                            detail_link = "https://id.wikipedia.org/wiki/Vanili";
                            break;
                    }
                    launchDetailBrowser();
                } else {
                    Log.d(getResources().getString(R.string.app_package_name), "Cant find $getCardID Bundled Data");
                }
                break;
        }
    }

    private void launchGalleryBrowser() {
        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(gallery_link));
        startActivity(browserIntent);
    }

    private void launchDetailBrowser() {
        browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(detail_link));
        startActivity(browserIntent);
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
