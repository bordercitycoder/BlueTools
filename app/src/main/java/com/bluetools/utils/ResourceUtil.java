package com.bluetools.utils;

import android.content.Context;
import android.support.annotation.StringRes;

public class ResourceUtil implements IResourceUtil {

    Context context;

    public ResourceUtil(Context context) {
        this.context = context;
    }

    public String getString(@StringRes int resId) {
        return context.getString(resId);
    }
}
