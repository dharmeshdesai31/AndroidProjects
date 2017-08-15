package com.crazycrystalstudio.evidentidmodel;

import android.graphics.Bitmap;

import com.crazycrystalstudio.evidentidmodel.Weather;

/**
 * Created by DharmeshDesai on 8/13/17.
 */

public interface DownloadingTaskListener {

    void onTaskStarted();
    void onTaskFinished(Weather node, Bitmap bitmap);
    void invalidURL();
}
