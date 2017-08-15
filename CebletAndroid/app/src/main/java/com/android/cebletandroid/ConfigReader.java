package com.android.cebletandroid;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by DharmeshDesai on 8/7/17.
 */

final class ConfigReader {

    private static String TAG = "ConfigReader";

    protected static String getConfigValues(Context context, String name)
    {
        Resources resources = context.getResources();
        try{
            InputStream is = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(is);
            return properties.getProperty(name);
        }catch(Exception e){
            Log.e(TAG,e.getMessage());
        }
    return null;
    }
}
