package com.crazycrystalstudio.evidentidmodel;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.crazycrystalstudio.evidentid.R;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by DharmeshDesai on 8/12/17.
 */

public final class ConfigReader {

    private static String TAG = "ConfigReader";

    public static String getConfigValues(Context context, String name)
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
