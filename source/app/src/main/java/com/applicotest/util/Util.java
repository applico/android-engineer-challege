package com.applicotest.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Nirajan on 9/22/2015.
 */
public class Util {

    public static final String ERROR_MSG = "Request could not be completed. Please try again later!";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();

        return activeNetworkInfo != null
                && activeNetworkInfo.isConnected();
    }

}
