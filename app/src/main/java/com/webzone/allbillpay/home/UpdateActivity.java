package com.webzone.allbillpay.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.webzone.allbillpay.Base.BaseActivity;
import com.webzone.allbillpay.R;


public class UpdateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView ivAvtar = (ImageView) findViewById(R.id.update_ivName);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("S", R.color.colorPrimary);
        ivAvtar.setImageDrawable(drawable);
    }

    @Override
    public void onClick(View v) {

    }
}
