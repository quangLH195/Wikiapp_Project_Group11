package com.wikiapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class Encyclopedia_5_Card extends Fragment implements View.OnClickListener {

    View v;
    String cardID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.card_encyclopedia_5, container,
                false);

        
        initCard();

        return v;
    }

    private void initCard() {
        cardID = getResources().getString(R.string.card5_encyclopedia);
        CardView cardView = v.findViewById(R.id.card5_encyclopedia);
        cardView.setOnClickListener(this);
        ImageView imageView = v.findViewById(R.id.img_card5_encyclopedia);
        Picasso.with(imageView.getContext())
                .load(R.drawable.img5_encyclopedia)
                .into(imageView);
        TextView textView = v.findViewById(R.id.text_card5_encyclopedia);
        Typeface tf= Typeface.createFromAsset(getActivity().getApplicationContext().getAssets(),"fonts/Honeyguide.otf");
        textView.setTypeface(tf);
        textView.setText(getResources().getString(R.string.card5_ens_title));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card5_encyclopedia:
                Intent intent = new Intent(getActivity(), EncyclopediaActivity.class);
                intent.putExtra("cardEncyclopediaID", cardID);
                startActivity(intent);
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
