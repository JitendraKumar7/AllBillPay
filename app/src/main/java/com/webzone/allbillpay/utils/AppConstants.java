package com.webzone.allbillpay.utils;

import android.os.Environment;

/*
 * The Class AppConstants.
 * 
 */

public class AppConstants {

    /**
     * The Constant SUCCESS.
     */
    public static final int SUCCESS = 0;

    /**
     * The Constant FAILURE.
     */
    public static final int FAILURE = 0;

    /**
     * The Constant SECOND. 1000 mili-second
     */
    public static final long SECOND = 1000L;

    /**
     * The Constant MIN.
     */
    public static final long MINUTE = 60L * SECOND;

    /**
     * The Constant HOUR.
     */
    public static final long HOUR = 60L * MINUTE;

    /**
     * The Constant MINIMUM_PASSWORD_LENGTH.
     */
    public static int MINIMUM_PASSWORD_LENGTH = 5;

    /**
     * The Constant MAXIMUM_PASSWORD_LENGTH.
     */
    public static int MAXIMUM_PASSWORD_LENGTH = 15;

    /****************
     * Basant
     ***********************/
    public static final String SERVICE_SERVER_ADDRESS = "http://kissmyapp.in:8080/Drfit";

    /**
     * The Constant SERVICE_METHOD_Signup.
     */
    public final static String SERVICE_METHOD_Signup = "signup.html";

    /**
     * The Constant SERVICE_METHOD_Login.
     */
    public final static String SERVICE_METHOD_Login = "signIn.html";


    /**
     * The Constant LOG_FILENAME.
     */
    public static final String LOG_FILENAME = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Drfit.txt";

    /**
     * The Constant APP_SHARED_PREF_NAME.
     */
    public static final String APP_SHARED_PREF_NAME = "drfit";

    /**
     * The Constant GENERIC_DIALOG_TAG.
     */
    public static final String GENERIC_DIALOG_TAG = "FragmentDialog";


    /**
     * The Constant ASSET_JSON_FILENAME.
     */
    public static final String ASSET_JSON_FILENAME = "testJson.json";

    /**
     * The Constant MB.
     */
    public static final int MB = 1 * 1024 * 1024;

    /**
     * The Constant MAX_BITMAP_CACHE_SIZE.
     */
    public static final int MAX_MEMORY_CACHE_SIZE = 4 * MB;

    /**
     * The Constant MAX_DISK_CACHE_SIZE.
     */
    public static final int MAX_DISK_CACHE_SIZE = 50 * MB;

    /**
     * The _is logging enabled.
     */
    public static boolean _isLoggingEnabled = false;

    /**
     * The _is file logging enabled.
     */
    public static boolean _isFileLoggingEnabled = false;

    /**
     * The _is exception logging enabled.
     */
    public static boolean _isFileExceptionLoggingEnabled = false;

    public static boolean iS_APPEND_LOG = true;


    /**************
     * The Constant FUNDUSMS_PLAYSTORE_URL.
     *************/
    public static String DRFIT_PLAYSTORE_URL = "market://details?id=au.drfit";

    /************
     * The Constant FUNDUSMS_PLAYSTORE_WEB_URL.
     ***********/
    public static final String DRFIT_PLAYSTORE_WEB_URL = "https://play.google.com/store/apps/details?id=au.drfit";

    public static boolean _isLoadJsonFromFile = false;

    public static String Url = "http://allbillpay.com/MDDAPI/Mobapp_API.php";

    public static final String OK = "Successful";
    public static final String SERVER_ERROR = "Server Error";
    public static final String CLIENT_ERROR = "Opps! something wrong.";

    /*********
     * SHARED PREFERENCES
     *********/
    public static final String IS_LOGGED_IN = "loggedin";
    public static final String USER_LOGIN_ID = "userloginid";
    public static final String USER_LOGIN_PASS = "userloginpass";
    public static final String USER_LOGIN_NAME = "userloginname";
    public static final String USER_LOGIN_EMAIL = "userloginemail";
    public static final String USER_LOGIN_MOBILE = "userloginmobile";
    public static final String USER_LOGIN_BALANCE = "userloginbalance";


    public static final String USER_DATA = "userdata";
    public static final String TYPE_LOGGED_IN = "typeloggedin";
    public static final String TYPE_GOOGLE = "typegoogle";
    public static final String TYPE_FACEBOOK = "typefacebook";
    public static final String USER_ID = "userId";
    public static final String INIT_RESPONSE = "initResponse";

    public static final int CONTACT_PICKER_RESULT = 1;
    public static final String DEBUG_TAG = null;
}