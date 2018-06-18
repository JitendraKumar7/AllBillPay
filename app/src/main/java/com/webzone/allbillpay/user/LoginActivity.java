package com.webzone.allbillpay.user;

import android.app.Dialog;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.webzone.allbillpay.Base.BaseActivity;
import com.webzone.allbillpay.R;
import com.webzone.allbillpay.app.AppController;
import com.webzone.allbillpay.home.HomeActivity;
import com.webzone.allbillpay.listener.ConnectivityReceiver;
import com.webzone.allbillpay.utils.AppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {

    private EditText txtLoginId, txtPassword;
    private TextView tvBtnForgot, tvBtnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        txtLoginId = (EditText) findViewById(R.id.login_txtLoginId);
        txtPassword = (EditText) findViewById(R.id.login_txtPassword);

        tvBtnForgot = (TextView) findViewById(R.id.login_txtBtnForgot);
        tvBtnSignup = (TextView) findViewById(R.id.login_txtBtnSignup);

        tvBtnForgot.setPaintFlags(tvBtnSignup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvBtnSignup.setPaintFlags(tvBtnSignup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvBtnForgot.setOnClickListener(this);
        tvBtnSignup.setOnClickListener(this);

        findViewById(R.id.login_txtBtnLogin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.login_txtBtnForgot:
                showForgot();
                break;

            case R.id.login_txtBtnLogin:
                String loginUserId = txtLoginId.getText().toString().trim();
                String loginPassword = txtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(loginUserId)) {
                    txtLoginId.setError("Required");
                } else if (TextUtils.isEmpty(loginPassword)) {
                    txtPassword.setError("Required");
                } else if (loginPassword.length() > 5) {
                    //launchIntent(HomeActivity.class, true);
                    if (ConnectivityReceiver.isConnected()) {
                        webService(loginUserId, loginPassword);
                    } else {
                        showToast("Network Error! Please check your network settings.");
                    }
                } else {
                    txtPassword.setError("Password length min 6 character");
                }
                break;

            case R.id.login_txtBtnSignup:
                launchIntent(SignupActivity.class, true);
                break;

            default:
                break;
        }
    }

    private void showForgot() {

        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);

        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        // set the custom dialog components - text, image and button
        final TextView tvLoginId = (TextView) dialog.findViewById(R.id.fp_txtLoginId);
        // if button is clicked, close the custom dialog
        dialog.findViewById(R.id.fp_txtProceed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String forgetUserid = tvLoginId.getText().toString().trim();

                if (TextUtils.isEmpty(forgetUserid)) {
                    tvLoginId.setError("Required");
                } else {
                    dialog.dismiss();
                    webService(forgetUserid);
                }
            }
        });

        dialog.show();
    }

    @SuppressWarnings("unchecked")
    private void webService(final String forgetUserid) {
        showProgress();
        final StringRequest stringRequest = new StringRequest(Request.Method.GET,
                AppConstants.Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                dismissProgress();
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    String status = obj.getString("Status");
                    String message = obj.getString("Message");
                    if (status.equals("1")) {
                    } else {
                        showToast(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
                params.put("action", "FORGOT");
                params.put("username", forgetUserid);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, TAG);
    }

    @SuppressWarnings("unchecked")
    private void webService(final String loginUserId, final String loginPassword) {
        showProgress();
        final StringRequest stringRequest = new StringRequest(Request.Method.POST,
                AppConstants.Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                dismissProgress();
                Log.d(TAG, response.toString());
                try {
                    JSONObject obj = new JSONObject(response);
                    String status = obj.getString("Status");
                    String message = obj.getString("Message");
                    if (status.equals("1")) {
                        AppController.getInstance().saveBoolean(AppConstants.IS_LOGGED_IN, true);
                        AppController.getInstance().saveString(AppConstants.USER_LOGIN_ID, loginUserId);
                        AppController.getInstance().saveString(AppConstants.USER_LOGIN_PASS, loginPassword);
                        AppController.getInstance().saveString(AppConstants.USER_LOGIN_NAME, obj.getString("Name"));
                        AppController.getInstance().saveString(AppConstants.USER_LOGIN_EMAIL, obj.getString("Email"));
                        AppController.getInstance().saveString(AppConstants.USER_LOGIN_MOBILE, obj.getString("Mobile"));
                        AppController.getInstance().saveString(AppConstants.USER_LOGIN_BALANCE, obj.getString("Balance"));
                        launchIntent(HomeActivity.class, true);
                    } else {
                        showToast(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
                params.put("action", "LOGIN");
                params.put("username", loginUserId);
                params.put("trpass", loginPassword);
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, TAG);
    }

}