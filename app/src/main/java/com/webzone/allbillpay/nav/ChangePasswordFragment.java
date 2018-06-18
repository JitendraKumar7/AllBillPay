package com.webzone.allbillpay.nav;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * Created by Jitendra Soam on 13/01/17.
 */
public class ChangePasswordFragment extends Fragment {

    private View mRootView;
    private TextView tvOldPassword, tvNewPassword, tvConPassword;
    private String TAG = ChangePasswordFragment.class.getSimpleName();

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_change_password, container, false);

        initView(mRootView);
        return mRootView;
    }

    private void initView(View v) {

        tvOldPassword = (TextView) v.findViewById(R.id.pwd_txtOldPassword);
        tvNewPassword = (TextView) v.findViewById(R.id.pwd_txtNewPassword);
        tvConPassword = (TextView) v.findViewById(R.id.pwd_txtConPassword);

        v.findViewById(R.id.pwd_txtBtnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OldPassword = tvOldPassword.getText().toString().trim();
                String NewPassword = tvNewPassword.getText().toString().trim();
                String ConPassword = tvConPassword.getText().toString().trim();
                if (TextUtils.isEmpty(OldPassword)) {
                    tvOldPassword.setError("Required");
                } else if (OldPassword.length() < 6) {
                    tvOldPassword.setError("Password length min 6 character");
                } else if (TextUtils.isEmpty(NewPassword)) {
                    tvNewPassword.setError("Required");
                } else if (NewPassword.length() < 6) {
                    tvNewPassword.setError("Password length min 6 character");
                } else if (NewPassword.equals(ConPassword)) {
                    if (ConnectivityReceiver.isConnected()) {
                        webService(OldPassword, NewPassword);
                    } else {
                        ((BaseActivity) getActivity()).showToast("Network Error! Please check your network settings.");
                    }
                } else {
                    tvConPassword.setError("Password does not match the confirm password");
                }
            }
        });
    }

    private void webService(final String oldpassword, final String newpassword) {
        ((BaseActivity) getActivity()).showProgress();
        final StringRequest stringRequest = new StringRequest(Request.Method.POST,
                AppConstants.Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                ((BaseActivity) getActivity()).dismissProgress();
                Log.d(TAG, response.toString());

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                ((BaseActivity) getActivity()).dismissProgress();
                ((BaseActivity) getActivity()).showToast("Network Error! Connection timed out");
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", "FORGOT_PASS");
                params.put("oldpassword", oldpassword);
                params.put("newpassword", newpassword);
                params.put("username", AppController.getInstance().getString(AppConstants.USER_LOGIN_ID, null));
                params.put("trpass", AppController.getInstance().getString(AppConstants.USER_LOGIN_PASS, null));
                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, TAG);
    }

}
