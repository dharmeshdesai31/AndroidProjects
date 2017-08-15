package com.crazycrystalstudio.evidentidmodel;

import android.graphics.Bitmap;

/**
 * Created by DharmeshDesai on 8/13/17.
 */

public interface DownloadingTaskListener {

    void onTaskStarted();
    void onTaskFinished(Weather node, Bitmap bitmap);
    void invalidURL();
}
