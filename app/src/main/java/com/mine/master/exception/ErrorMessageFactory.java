package com.mine.master.exception;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

public class ErrorMessageFactory {
    private static final String TAG = ErrorMessageFactory.class.getSimpleName();

    private ErrorMessageFactory() {
    }

    public static String create(Context context, Exception exception) {
        if (TextUtils.isEmpty(exception.getMessage())) {
            Log.e(TAG, exception.getMessage());
        }
        return exception.getMessage();
    }
}
