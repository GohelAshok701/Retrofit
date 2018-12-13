package com.retrofit.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Ashok on 12/13/2018.
 */

public class Utility {

    public static boolean isNetworkAvailable(Activity activity)
    {

        ConnectivityManager connectivityManager =  (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
