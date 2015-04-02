package com.framework.app.component.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast显示工具类
 * 
 * @ClassName: ToastUtils.java
 * 
 * @author Xun.Zhang
 * 
 * @date 2014-12-8 上午10:33:54
 */
public class ToastUtil {

    /**
     * Show Toast in short time by system
     * 
     * @param context
     * @param strMsg
     */
    public static void showMessage(final Context context, final String strMsg) {
        Toast.makeText(context, strMsg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show Toast in short time by system
     * 
     * @param context
     * @param resId
     */
    public static void showMessage(final Context context, final int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show Toast in long time by system
     * 
     * @param context
     * @param resId
     */
    public static void showMessageLong(final Context context, final int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    /**
     * Show Toast in long time by system
     * 
     * @param context
     * @param strMsg
     */
    public static void showMessageLong(final Context context, final String strMsg) {
        if (context != null) {
            Toast.makeText(context, strMsg, Toast.LENGTH_LONG).show();
        }
    }

}
