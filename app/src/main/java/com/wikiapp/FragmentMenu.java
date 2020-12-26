package com.wikiapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class FragmentMenu extends Fragment implements View.OnClickListener{
    
    View v;
    String cardID;
    Intent intent;
    Typeface tf;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_menu, container, false);

        getFont();

        // initializing card
        // card 1
        initCard1();

        // card 2
        initCard2();

        // card 3
        initCard3();

        // card 4
        initCard4();

        // card 5
        /*initCard5();*/

        // card 6
        /*initCard6();*/

        // initializing intent
        intent = new Intent(getActivity(), MenuActivity.class);
        
        return v;
    }

    private void getFont() {
        tf= Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
    }

    private void initCard1() {
        CardView card = v.findViewById(R.id.card1);
        card.setOnClickListener(this);
        ImageView img = v.findViewById(R.id.img_card1);
        Picasso.with(img.getContext())
                .load(R.drawable.c1)
                .into(img);
        TextView text = v.findViewById(R.id.text_card1);
        text.setTypeface(tf);
        text.setText(getResources().getString(R.string.card1_menu_title));
    }

    private void initCard2() {
        CardView card = v.findViewById(R.id.card2);
        card.setOnClickListener(this);
        ImageView img = v.findViewById(R.id.img_card2);
        Picasso.with(img.getContext())
                .load(R.drawable.c2)
                .into(img);
        TextView text = v.findViewById(R.id.text_card2);
        text.setTypeface(tf);
        text.setText(getResources().getString(R.string.card2_menu_title));
    }

    private void initCard3() {
        CardView card = v.findViewById(R.id.card3);
        card.setOnClickListener(this);
        ImageView img = v.findViewById(R.id.img_card3);
        Picasso.with(img.getContext())
                .load(R.drawable.c3)
                .into(img);
        TextView text = v.findViewById(R.id.text_card3);
        text.setTypeface(tf);
        text.setText(getResources().getString(R.string.card3_menu_title));
    }

    private void initCard4() {
        CardView card = v.findViewById(R.id.card4);
        card.setOnClickListener(this);
        ImageView img = v.findViewById(R.id.img_card4);
        Picasso.with(img.getContext())
                .load(R.drawable.c4)
                .into(img);
        TextView text = v.findViewById(R.id.text_card4);
        text.setTypeface(tf);
        text.setText(getResources().getString(R.string.card4_menu_title));
    }

    /*private void initCard5() {
        CardView card = v.findViewById(R.id.card5);
        card.setOnClickListener(this);
        ImageView img = v.findViewById(R.id.img_card5);
        Picasso.with(img.getContext())
                .load(R.drawable.test)
                .into(img);
        TextView text = v.findViewById(R.id.text_card5);
        text.setTypeface(tf);
        text.setText(getResources().getString(R.string.card5_menu_title));
    }*/

    /*private void initCard6() {
        CardView card = v.findViewById(R.id.card6);
        card.setOnClickListener(this);
        ImageView img = v.findViewById(R.id.img_card6);
        Picasso.with(img.getContext())
                .load(R.drawable.test)
                .into(img);
        TextView text = v.findViewById(R.id.text_card6);
        text.setTypeface(tf);
        text.setText(getResources().getString(R.string.card6_menu_title));
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card1:
                cardID = getResources().getString(R.string.card1_menu);
                intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("cardMenuID", cardID);
                startActivity(intent);
                /*showToast();*/
                break;

            case R.id.card2:
                cardID = getResources().getString(R.string.card2_menu);
                intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("cardMenuID", cardID);
                startActivity(intent);
                /*showToast();*/
                break;

            case R.id.card3:
                cardID = getResources().getString(R.string.card3_menu);
                intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("cardMenuID", cardID);
                startActivity(intent);
                /*showToast();*/
                break;

            case R.id.card4:
                cardID = getResources().getString(R.string.card4_menu);
                intent = new Intent(getActivity(), MenuActivity.class);
                intent.putExtra("cardMenuID", cardID);
                startActivity(intent);
                /*showToast();*/
                break;

            case R.id.card5:
                /*showToast();*/
                break;

            case R.id.card6:
                /*intent = new Intent(getActivity(), QuizActivity.class);
                startActivity(intent);*/
                /*showToast();*/
                break;
        }
    }

    /*private void showToast() {
        Toast.makeText(getActivity(), getResources().getString(R.string.wip), Toast.LENGTH_SHORT).show();
    }*/

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
