package com.webzone.allbillpay.recharge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.webzone.allbillpay.Base.BaseActivity;
import com.webzone.allbillpay.R;
import com.webzone.allbillpay.app.AppController;
import com.webzone.allbillpay.listener.ConnectivityReceiver;
import com.webzone.allbillpay.utils.AppConstants;

import java.util.HashMap;
import java.util.Map;

public class ConfirmRechargeActivity extends BaseActivity {

    private Bundle bundle;
    private TextView tvMobile, tvMobileNum, tvOperator, tvAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_recharge);
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

        tvMobile = (TextView) findViewById(R.id.con_txtMobile);
        tvMobileNum = (TextView) findViewById(R.id.con_txtMobileNumber);
        tvOperator = (TextView) findViewById(R.id.con_txtOperator);
        tvAmount = (TextView) findViewById(R.id.con_txtAmount);

        bundle = this.getIntent().getExtras();
        if (bundle.getString("type").equalsIgnoreCase("dth")) {
            tvMobile.setText(R.string.hintDthId);
        } else if (bundle.getString("type").equalsIgnoreCase("data")) {
            tvMobile.setText(R.string.hintDatacard);
        } else if (bundle.getString("type").equalsIgnoreCase("mobile")) {
            tvMobile.setText(R.string.hintMobile);
        } else {
            finish();
            showToast("Opps! Something went wrong!");
        }

        tvMobileNum.setText(bundle.getString("mobile"));
        tvOperator.setText(bundle.getString("operator"));
        tvAmount.setText("\u20b9" + bundle.getString("amount"));

        findViewById(R.id.con_txtBtnRecharge).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.con_txtBtnRecharge)
            if (ConnectivityReceiver.isConnected()) {
                webService();
            } else {
                showToast("Network Error! Please check your network settings.");
            }
        //showToast("Opps! Something went wrong!");
    }

    private void webService() {
        showProgress();
        final StringRequest stringRequest = new StringRequest(Request.Method.POST,
                AppConstants.Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                dismissProgress();
                Log.d(TAG, response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                dismissProgress();
                showToast("Network Error! Connection timed out");
                VolleyLog.d(TAG, "Error: " + error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", "RECHARGE");
                params.put("mobileno", bundle.getString("mobile"));
                params.put("opt_code", bundle.getString("operatorcode"));
                params.put("recharge_amount", bundle.getString("amount"));
                params.put("username", AppController.getInstance().getString(AppConstants.USER_LOGIN_ID, null));
                params.put("trpass", AppController.getInstance().getString(AppConstants.USER_LOGIN_PASS, null));
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, TAG);
    }

}
