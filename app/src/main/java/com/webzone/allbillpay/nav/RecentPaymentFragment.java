package com.webzone.allbillpay.nav;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webzone.allbillpay.R;
import com.webzone.allbillpay.adapter.RecentlyPaymentsAdapter;

/**
 * Created by Jitendra Soam on 13/01/17.
 */
public class RecentPaymentFragment extends Fragment {

    private View mRootView;

    public RecentPaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_recent_payment, container, false);

        initView(mRootView);

        return mRootView;
    }

    private void initView(View v) {
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        RecentlyPaymentsAdapter mAdapter = new RecentlyPaymentsAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

}
