package com.webzone.allbillpay.nav;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.webzone.allbillpay.Base.BaseActivity;
import com.webzone.allbillpay.R;
import com.webzone.allbillpay.recharge.ConfirmRechargeActivity;

/**
 * Created by Jitendra Soam on 13/01/17.
 */
public class MobileRechargeFragment extends Fragment implements View.OnClickListener {

    private View mRootView;
    private TextView tvOperater;
    private EditText txtMobile, txtAmount;
    private String mobile_operator_code = "operator";
    private String[] operator = {"AIRCEL", "BSNL TOPUP", "VIDEOCON", "VIDEOCON SPECIAL", "MTNL Mumbai", "MTNL Mumbai Special",
            "IDEA", "MTS", "RELIANCE CDMA", "RELIANCE GSM", "Docomo GSM", "Docomo GSM SPECIAL", "Docomo CDMA", "UNINOR TOP UP",
            "UNINOR SPECIAL", "MTNL Delhi", "MTNL Delhi Special", "VODAFONE", "TATA WALKY", "BSNL  Validity", "AIRTEL"};

    public MobileRechargeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_mobile_recharge, container, false);

        initView(mRootView);
        return mRootView;
    }

    private void initView(View v) {

        txtMobile = (EditText) v.findViewById(R.id.rc_txtMobile);
        txtAmount = (EditText) v.findViewById(R.id.rc_txtAmount);
        tvOperater = (TextView) v.findViewById(R.id.rc_txtOperater);

        tvOperater.setOnClickListener(this);
        v.findViewById(R.id.rc_txtProceed).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rc_txtOperater:
                showMenuPopup(getActivity());
                break;

            case R.id.rc_txtProceed:
                String mobile = txtMobile.getText().toString().trim();
                String amount = txtAmount.getText().toString().trim();
                String operator = tvOperater.getText().toString().trim();
                if (TextUtils.isEmpty(mobile) || mobile.length() < 10) {
                    txtMobile.setError("Required");
                } else if (TextUtils.isEmpty(amount)) {
                    txtAmount.setError("Required");
                } else if (TextUtils.isEmpty(operator)) {
                    tvOperater.setError("Required");
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "mobile");
                    bundle.putString("mobile", mobile);
                    bundle.putString("operator", operator);
                    bundle.putString("amount", amount);
                    bundle.putString("operatorcode", mobile_operator_code);
                    ((BaseActivity) getActivity()).launchIntent(ConfirmRechargeActivity.class, bundle, false);
                }
                break;
            default:
                break;
        }
    }

    private void showMenuPopup(final Context ctx) {

        final Dialog dialog = new Dialog(ctx);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_operator);
        dialog.setCancelable(false);

        ListView lvOperator = (ListView) dialog.findViewById(R.id.lvOperator);

        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, operator);
        lvOperator.setAdapter(adapter);

        lvOperator.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                switch (position) {
                    case 0:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "AIRC";
                        break;
                    case 1:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "BSNLT";
                        break;
                    case 2:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "VCON";
                        break;
                    case 3:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "VCONS";
                        break;
                    case 4:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "MTNLTM";
                        break;
                    case 5:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "MTNLSM";
                        break;
                    case 6:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "IDEA";
                        break;
                    case 7:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "MTS";
                        break;
                    case 8:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "RIMC";
                        break;
                    case 9:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "RIMG";
                        break;
                    case 10:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "TDCT";
                        break;
                    case 11:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "TDCTS";
                        break;
                    case 12:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "TDCS";
                        break;
                    case 13:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "UNRT";
                        break;
                    case 14:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "UNRS";
                        break;
                    case 15:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "MTNLTD";
                        break;
                    case 16:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "MTNLSD";
                        break;
                    case 17:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "VODA";
                        break;
                    case 18:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "TATAW";
                        break;
                    case 19:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "BSNLV";
                        break;
                    case 20:
                        tvOperater.setText(operator[position]);
                        mobile_operator_code = "ARTL";
                        break;
                    default:
                        break;
                }

                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
