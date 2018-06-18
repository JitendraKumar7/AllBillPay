package com.webzone.allbillpay.user;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.webzone.allbillpay.Base.BaseActivity;
import com.webzone.allbillpay.R;
import com.webzone.allbillpay.home.HomeActivity;

public class SignupActivity extends BaseActivity {

    private EditText txtName, txtEmail, txtMobile, txtPassword, txtConPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();

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
    }

    private void initView() {

        txtName = (EditText) findViewById(R.id.signup_txtName);
        txtEmail = (EditText) findViewById(R.id.signup_txtEmail);
        txtMobile = (EditText) findViewById(R.id.signup_txtMobile);
        txtPassword = (EditText) findViewById(R.id.signup_txtPassword);
        txtConPass = (EditText) findViewById(R.id.signup_txtConPassword);

        findViewById(R.id.signup_txtBtnRegister).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.signup_txtBtnRegister:
                launchIntent(HomeActivity.class, true);
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        launchIntent(LoginActivity.class, true);
    }

    private void checkValidation() {
        /*String string_emailId = registerEmailid.getText().toString();
        String string_password = registerPassword.getText().toString();
        String string_mobile = registerMobile.getText().toString();

        if (AppUtils.isValidEmail(string_emailId)) {
            if (string_password.length() >= 6) {
                if (string_mobile.length() == 0 || string_mobile.length() == 10) {
                    Toast.makeText(this, getResources().getString(R.string.register_done), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                } else {
                    AppUtils.DefaultPopup(this, getResources().getString(R.string.wrong_mobile));
                }
            } else {
                AppUtils.DefaultPopup(this, getResources().getString(R.string.wrong_password));
            }
        } else {
            AppUtils.DefaultPopup(this, getResources().getString(R.string.wrong_usrId));
        }
    }*/
    }

}