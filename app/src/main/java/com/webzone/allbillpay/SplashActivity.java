package com.webzone.allbillpay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.webzone.allbillpay.Base.BaseActivity;
import com.webzone.allbillpay.app.AppController;
import com.webzone.allbillpay.home.HomeActivity;
import com.webzone.allbillpay.user.LoginActivity;
import com.webzone.allbillpay.utils.AppConstants;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (AppController.getInstance().getBoolean(AppConstants.IS_LOGGED_IN)) {
            launchIntent(HomeActivity.class, true);
        } else {
            launchIntent(LoginActivity.class, true);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
