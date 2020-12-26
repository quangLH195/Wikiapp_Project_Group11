package com.wikiapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    final Context context = this;
    View v;
    Typeface tf;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switch (State) {
                        case "encyclopedia":
                            State = "home";
                            fragmentTransaction.replace(R.id.fragment_container,
                                    new FragmentMenu()).addToBackStack(null).commit();
                            break;

                        default:
                            State = "home";
                            break;
                    }
                    return true;

                case R.id.navigation_dashboard:
                    switch (State) {
                        case "home":
                            State = "encyclopedia";
                            fragmentTransaction.replace(R.id.fragment_container,
                                    new FragmentEncyclopedia()).addToBackStack(null).commit();
                            break;

                        default:
                            State = "encyclopedia";
                            break;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            switch (State) {
                case "home" :
                    fragmentTransaction.replace(R.id.fragment_container,
                            new FragmentMenu()).addToBackStack(null).commit();
                    break;

                case "encyclopedia":
                    fragmentTransaction.replace(R.id.fragment_container,
                            new FragmentEncyclopedia()).addToBackStack(null).commit();
                    break;
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            switch (State) {
                case "home" :
                    fragmentTransaction.replace(R.id.fragment_container,
                            new FragmentMenu()).addToBackStack(null).commit();
                    break;

                case "encyclopedia":
                    fragmentTransaction.replace(R.id.fragment_container,
                            new FragmentEncyclopedia()).addToBackStack(null).commit();
                    break;
            }
        }
    }

    String State;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = getLayoutInflater().inflate(R.layout.activity_dashboard, null);
        setContentView(v);

        // showing default fragment
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,
                    new FragmentMenu()).addToBackStack(null).commit();
            State = "home";
        }

        // initializing Bottom Bar
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // initializing Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbar.showOverflowMenu();

        // initializing font
        getFont();

        initUI();
    }

    private void getFont() {
        tf= Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
    }

    private void initUI() {
        TextView txt1 = v.findViewById(R.id.txt1_dashboard);
        txt1.setTypeface(tf);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_app:
                showAboutAppDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    int i;

    @SuppressLint("SetTextI18n")
    private void showAboutAppDialog() {
        final Dialog dialogAboutApp = new Dialog(context, R.style.Dialog);

        i = 0;

        dialogAboutApp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAboutApp.setContentView(R.layout.dialog_about_app);
        dialogAboutApp.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogAboutApp, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (i == 0) {
                        dialogAboutApp.dismiss();
                    } else {
                        i = 0;
                    }
                }
                return true;
            }
        });
        dialogAboutApp.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogAboutApp.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // initializing version & text
        TextView txt1 = dialogAboutApp. findViewById(R.id.about_app_title);
        txt1.setTypeface(tf);

        TextView version = dialogAboutApp.findViewById(R.id.about_app_version);
        version.setTypeface(tf);
        version.setText(getResources().getString(R.string.about_app_version) + " " + getResources().getString(R.string.app_version));

        TextView txt3 = dialogAboutApp. findViewById(R.id.about_app_description);
        txt3.setTypeface(tf);

        // initializing button
        Button btn_source_reference = dialogAboutApp.findViewById(R.id.btn_source);
        btn_source_reference.setTypeface(tf);

        Button btn_app_lib = dialogAboutApp.findViewById(R.id.btn_app_lib);
        btn_app_lib.setTypeface(tf);

        Button btn_dev = dialogAboutApp.findViewById(R.id.btn_dev);
        btn_dev.setTypeface(tf);

        btn_source_reference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                showAboutAppResDialog();
            }
        });
        btn_app_lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                showAboutAppLibDialog();
            }
        });
        btn_dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                showAboutDevDialog();
            }
        });

        dialogAboutApp.show();
    }

    private void showAboutDevDialog() {
        final Dialog dialogAboutDev = new Dialog(context, R.style.Dialog);
        dialogAboutDev.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAboutDev.setContentView(R.layout.dialog_about_dev);
        dialogAboutDev.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogAboutDev, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialogAboutDev.dismiss();
                }
                return true;
            }
        });
        dialogAboutDev.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogAboutDev.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView txt1 = dialogAboutDev.findViewById(R.id.txt1_dialog_about_dev);
        txt1.setTypeface(tf);

        TextView txt2 = dialogAboutDev.findViewById(R.id.txt2_dialog_about_dev);
        txt2.setTypeface(tf);

        dialogAboutDev.setCanceledOnTouchOutside(true);
        dialogAboutDev.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                i = 0;
            }
        });

        dialogAboutDev.show();
    }

    private void showAboutAppResDialog() {
        final Dialog dialogAboutRes = new Dialog(context, R.style.Dialog);
        dialogAboutRes.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAboutRes.setContentView(R.layout.dialog_app_res);
        dialogAboutRes.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogAboutRes, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialogAboutRes.dismiss();
                }
                return true;
            }
        });
        dialogAboutRes.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogAboutRes.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView txt0 = dialogAboutRes.findViewById(R.id.txt0_dialog_about_app_res);
        txt0.setTypeface(tf);

        TextView txt1 = dialogAboutRes.findViewById(R.id.txt1_dialog_about_app_res);
        txt1.setTypeface(tf);

        TextView txt2 = dialogAboutRes.findViewById(R.id.txt2_dialog_about_app_res);
        txt2.setTypeface(tf);

        TextView txt3 = dialogAboutRes.findViewById(R.id.txt3_dialog_about_app_res);
        txt3.setTypeface(tf);

        Button btn1 = dialogAboutRes.findViewById(R.id.btn1_dialog_about_app_res);
        btn1.setTypeface(tf);
        Button btn2 = dialogAboutRes.findViewById(R.id.btn2_dialog_about_app_res);
        btn2.setTypeface(tf);
        Button btn3 = dialogAboutRes.findViewById(R.id.btn3_dialog_about_app_res);
        btn3.setTypeface(tf);
        Button btn4 = dialogAboutRes.findViewById(R.id.btn4_dialog_about_app_res);
        btn4.setTypeface(tf);
        Button btn5 = dialogAboutRes.findViewById(R.id.btn5_dialog_about_app_res);
        btn5.setTypeface(tf);
        Button btn6 = dialogAboutRes.findViewById(R.id.btn6_dialog_about_app_res);
        btn6.setTypeface(tf);
        Button btn7 = dialogAboutRes.findViewById(R.id.btn7_dialog_about_app_res);
        btn7.setTypeface(tf);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikipedia.com"));
                startActivity(browserIntent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikihow.com"));
                startActivity(browserIntent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(browserIntent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.stackoverflow.com"));
                startActivity(browserIntent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/index.html"));
                startActivity(browserIntent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.flaticon.com"));
                startActivity(browserIntent);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.freepik.com"));
                startActivity(browserIntent);
            }
        });

        dialogAboutRes.setCanceledOnTouchOutside(true);
        dialogAboutRes.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                i = 0;
            }
        });

        dialogAboutRes.show();
    }

    private void showAboutAppLibDialog() {
        final Dialog dialogAboutLib = new Dialog(context, R.style.Dialog);
        dialogAboutLib.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogAboutLib.setContentView(R.layout.dialog_app_lib);
        dialogAboutLib.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogAboutLib, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialogAboutLib.dismiss();
                }
                return true;
            }
        });
        dialogAboutLib.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogAboutLib.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView txt1 = dialogAboutLib.findViewById(R.id.txt1_dialog_about_app_lib);
        txt1.setTypeface(tf);

        Button btn1 = dialogAboutLib.findViewById(R.id.btn1_dialog_about_app_lib);
        btn1.setTypeface(tf);
        Button btn2 = dialogAboutLib.findViewById(R.id.btn2_dialog_about_app_lib);
        btn2.setTypeface(tf);
        Button btn3 = dialogAboutLib.findViewById(R.id.btn3_dialog_about_app_lib);
        btn3.setTypeface(tf);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/square/picasso"));
                startActivity(browserIntent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/vinc3m1/RoundedImageView"));
                startActivity(browserIntent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hdodenhof/CircleImageView"));
                startActivity(browserIntent);
            }
        });

        dialogAboutLib.setCanceledOnTouchOutside(true);
        dialogAboutLib.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                i = 0;
            }
        });

        dialogAboutLib.show();
    }

    @Override
    public void onBackPressed() {
        showExitAppWarningDialog();
    }

    private void showExitAppWarningDialog() {
        final Dialog dialogExitApp = new Dialog(context, R.style.Dialog);
        dialogExitApp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogExitApp.setContentView(R.layout.dialog_app_exit_warning);
        dialogExitApp.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogExitApp, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialogExitApp.dismiss();
                }
                return true;
            }
        });
        dialogExitApp.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogExitApp.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView txt1 = dialogExitApp.findViewById(R.id.txt1_dialog_app_exit_warning);
        txt1.setTypeface(tf);
        TextView txt2 = dialogExitApp.findViewById(R.id.txt2_dialog_app_exit_warning);
        txt2.setTypeface(tf);

        Button btn1 = dialogExitApp.findViewById(R.id.btn_exit_app_yes);
        btn1.setTypeface(tf);
        Button btn2 = dialogExitApp.findViewById(R.id.btn_exit_app_no);
        btn2.setTypeface(tf);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogExitApp.dismiss();
            }
        });

        dialogExitApp.show();
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
        super.onDestroy();
    }
}
