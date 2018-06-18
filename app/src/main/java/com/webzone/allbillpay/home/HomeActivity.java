package com.webzone.allbillpay.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.webzone.allbillpay.Base.BaseActivity;
import com.webzone.allbillpay.R;
import com.webzone.allbillpay.app.AppController;
import com.webzone.allbillpay.nav.AddNewUserFragment;
import com.webzone.allbillpay.nav.ChangePasswordFragment;
import com.webzone.allbillpay.nav.DataCardRechargeFragment;
import com.webzone.allbillpay.nav.DthRechargeFragment;
import com.webzone.allbillpay.nav.MobileRechargeFragment;
import com.webzone.allbillpay.nav.RecentPaymentFragment;
import com.webzone.allbillpay.user.LoginActivity;
import com.webzone.allbillpay.utils.AppConstants;

public class HomeActivity extends BaseActivity {

    private Fragment fragment = null;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar,
                R.string.app_name, R.string.app_name
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        replaceFragment(new MobileRechargeFragment());

        findViewById(R.id.nav_txtBtnDthRecharge).setOnClickListener(this);
        findViewById(R.id.nav_txtBtnMobileRecharge).setOnClickListener(this);
        findViewById(R.id.nav_txtBtnDatacardRecharge).setOnClickListener(this);
        findViewById(R.id.nav_txtBtnChangePassword).setOnClickListener(this);
        findViewById(R.id.nav_txtBtnMyTransactions).setOnClickListener(this);
        findViewById(R.id.nav_txtBtnRecentlyPayment).setOnClickListener(this);
        findViewById(R.id.nav_txtBtnAddNewUser).setOnClickListener(this);
        findViewById(R.id.nav_txtBtnLogout).setOnClickListener(this);


        TextView name = (TextView) findViewById(R.id.nav_txtUserName);
        TextView loginId = (TextView) findViewById(R.id.nav_txtLoginId);
        TextView balance = (TextView) findViewById(R.id.nav_txtBalance);

        name.setText(AppController.getInstance().getString(AppConstants.USER_LOGIN_NAME, ""));
        loginId.setText(AppController.getInstance().getString(AppConstants.USER_LOGIN_ID, ""));
        balance.setText("\u20b9" + AppController.getInstance().getString(AppConstants.USER_LOGIN_BALANCE, ""));

        ImageView ivAvtar = (ImageView) findViewById(R.id.nav_ivAvtar);
        ImageView ivName = (ImageView) findViewById(R.id.nav_ivName);
        ivName.setOnClickListener(this);
        ivAvtar.setOnClickListener(this);

        char first = (name.getText().toString().trim()).charAt(0);
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(String.valueOf(first),
                        R.color.colorPrimary);
        ivName.setImageDrawable(drawable);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.nav_ivName:
            case R.id.nav_ivAvtar:
                launchIntent(UpdateActivity.class, false);
                break;
            case R.id.nav_txtBtnDthRecharge:
                if (!(fragment instanceof DthRechargeFragment)) {
                    getSupportActionBar().setTitle("DTH Recharge");
                    replaceFragment(new DthRechargeFragment());
                }

                break;

            case R.id.nav_txtBtnMobileRecharge:
                if (!(fragment instanceof MobileRechargeFragment)) {
                    getSupportActionBar().setTitle("Mobile Recharge");
                    replaceFragment(new MobileRechargeFragment());
                }
                break;

            case R.id.nav_txtBtnDatacardRecharge:
                if (!(fragment instanceof DataCardRechargeFragment)) {
                    getSupportActionBar().setTitle("Datacard Recharge");
                    replaceFragment(new DataCardRechargeFragment());
                }
                break;

            case R.id.nav_txtBtnChangePassword:
                if (!(fragment instanceof ChangePasswordFragment)) {
                    getSupportActionBar().setTitle("Change Password");
                    replaceFragment(new ChangePasswordFragment());
                }
                break;

            case R.id.nav_txtBtnRecentlyPayment:
                if (!(fragment instanceof RecentPaymentFragment)) {
                    getSupportActionBar().setTitle("Recent Payment");
                    replaceFragment(new RecentPaymentFragment());
                }
                break;

            case R.id.nav_txtBtnAddNewUser:
                if (!(fragment instanceof AddNewUserFragment)) {
                    getSupportActionBar().setTitle("Add New User");
                    replaceFragment(new AddNewUserFragment());
                }
                break;

            case R.id.nav_txtBtnLogout:
                AppController.getInstance().saveBoolean(AppConstants.IS_LOGGED_IN, false);
                launchIntent(LoginActivity.class, true);
                break;

            default:
                break;
        }

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            //drawer is open
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            fragment = null;
            super.onBackPressed();
            getSupportActionBar().setTitle("AllBillPay");
        }
    }

    private void replaceFragment(Fragment fragment) {
        this.fragment = fragment;
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.containerView, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

}
