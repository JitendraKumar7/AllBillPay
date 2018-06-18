package com.webzone.allbillpay.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.webzone.allbillpay.listener.ConnectivityReceiver;

/**
 * Created by Jitendra Soam on 2/9/2016.
 */
public class AppController extends Application {

    private Context mContext;
    private RequestQueue mRequestQueue;
    private static AppController mInstance;
    public static final String TAG = AppController.class.getSimpleName();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    public void saveString(String field, String value) {
        SharedPreferences sp = mContext.getSharedPreferences(getPackageName(), 0);
        sp.edit().putString(field, value).commit();
    }

    public String getString(String field, String def) {
        SharedPreferences sp = mContext.getSharedPreferences(getPackageName(), 0);
        return sp.getString(field, def);
    }

    public void saveBoolean(String key, boolean data) {
        final SharedPreferences SpyAppData = mContext.getSharedPreferences(getPackageName(), 0);
        SharedPreferences.Editor editor = SpyAppData.edit();
        editor.putBoolean(key, data);
        editor.commit();
    }

    public boolean getBoolean(String key) {
        final SharedPreferences ToolsAppData = mContext.getSharedPreferences(getPackageName(), 0);
        final boolean preData = ToolsAppData.getBoolean(key, false);
        return preData;

    }

}