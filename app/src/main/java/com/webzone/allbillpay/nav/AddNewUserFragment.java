package com.webzone.allbillpay.nav;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webzone.allbillpay.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewUserFragment extends Fragment implements View.OnClickListener {

    private View mRootView;
    private TextView tvName, tvUserName, tvEmailid, tvMobile, tvUserCity, tvUserState, tvReferedUser;

    public AddNewUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_add_new_user, container, false);
        initView(mRootView);

        return mRootView;
    }

    private void initView(View v) {
        tvName = (TextView) v.findViewById(R.id.adduser_txtName);
        tvUserName = (TextView) v.findViewById(R.id.adduser_txtUserName);
        tvEmailid = (TextView) v.findViewById(R.id.adduser_txtEmail);
        tvMobile = (TextView) v.findViewById(R.id.adduser_txtMobile);
        tvUserCity = (TextView) v.findViewById(R.id.adduser_txtUserCity);
        tvUserState = (TextView) v.findViewById(R.id.adduser_txtUserState);
        tvReferedUser = (TextView) v.findViewById(R.id.adduser_txtByUserName);

        v.findViewById(R.id.adduser_txtBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = tvName.getText().toString().trim();
        String userName = tvUserName.getText().toString().trim();
        String userEmailid = tvEmailid.getText().toString().trim();
        String userMobile = tvMobile.getText().toString().trim();
        String userCity = tvUserCity.getText().toString().trim();
        String userState = tvUserState.getText().toString().trim();
        String referedUser = tvReferedUser.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            tvName.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(userName)) {
            tvUserName.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(userEmailid)) {
            tvEmailid.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(userMobile)) {
            tvMobile.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(userCity)) {
            tvUserCity.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(userState)) {
            tvUserState.setError("Required");
            return;
        }
        if (TextUtils.isEmpty(referedUser)) {
            tvReferedUser.setError("Required");
            return;
        }

    }
}
