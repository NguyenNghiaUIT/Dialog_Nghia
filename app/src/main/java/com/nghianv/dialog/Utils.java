package com.nghianv.dialog;

import android.content.Context;

/**
 * Created by nguyennghia on 28/03/2016.
 */
public class Utils {

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static float convertDptoPx(){
        return  0f;
    }

    public static float convertPxtoPd(){
        return 0f;
    }

}
