package com.bq.bqtest.utils;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.util.DisplayMetrics;

public class Utils
{

    public static boolean hasConnection(Context context)
    {
        try
        {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static float convertDpToPixel(float dp, Context context)
    {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static float convertPixelsToDp(float px, Context context)
    {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}
