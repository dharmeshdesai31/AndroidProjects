package com.crazycrystalstudio.evidentid;

import android.graphics.Bitmap;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.crazycrystalstudio.evidentidmodel.DailySummary;
import com.crazycrystalstudio.evidentidmodel.DownloadingTaskListener;
import com.crazycrystalstudio.evidentidmodel.History;
import com.crazycrystalstudio.evidentidmodel.MyDate;
import com.crazycrystalstudio.evidentidmodel.Observations;
import com.crazycrystalstudio.evidentidmodel.Weather;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by DharmeshDesai on 8/13/17.
 */

@RunWith(AndroidJUnit4.class)
public class FetchJsonDataTaskTests {

    JSONDownloader downloader;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    MainActivity mActivity = mActivityRule.getActivity();

    @Before
    public void createJSONDownloader(){
        downloader = new JSONDownloader(mActivity, "http://api.wunderground.com/api/1063a57b7332ca7b/history_20170113/q/GA/Alpharetta.json", 0);
    }

    @Test
    public void instantiateJSONDownloaderClass(){
        assertTrue("Unable to instantiate JSONDownloader",(downloader != null));
    }

    @Test
    public void testRetrivedDataIsInCorrectForm()
    {
        Log.i("inside ", "testRetrivedDataIsInCorrectForm()");
        downloader.setListener(new DownloadingTaskListener() {
            @Override
            public void onTaskStarted() {

            }

            @Override
            public void onTaskFinished(Weather node, Bitmap bitmap) {
                /*
                CHECK ALL THE OBJECTS IS IN CORRECT FORM
                 */
                assertTrue("Was unable to get Weather instance from Json String.", node instanceof Weather);
                assertTrue("Was unable to get History instance from Weather.",node.getHistory() instanceof History);
                assertTrue("Was unable to get MyDate instance from Weather.",node.getHistory().getObservation(0).getDate() instanceof MyDate);
                //assertTrue("Was unable to get UTCDate instance from Weather.",node.getHistory().getUtcdate() instanceof MyUTCDate);
                assertTrue("Was unable to get DailySummery instance from Weather.",node.getHistory().getDailysummary() instanceof DailySummary[]);
                assertTrue("Was unable to get Observation instance from Weather.",node.getHistory().getObservation(0) instanceof Observations);

                /*
                TEST ACTUAL VALUES
                 */
                //compare date Mydate class parameter
                assertEquals("Year is not equal.",node.getHistory().getObservation(0).getDate().getYear(), 2017);
                assertEquals("Month is not equal.",node.getHistory().getObservation(0).getDate().getMon(), 01);
                assertEquals("Year is not equal.",node.getHistory().getObservation(0).getDate().getMday(), 13);

                //compare other parameter values
                assertEquals(node.getHistory().getObservation(0).getTempi(), new Float(53.6), 0);
                assertEquals(node.getHistory().getObservation(0).getDewpti(), new Float(50.0), 0);
                assertEquals(node.getHistory().getObservation(0).getHum(), new Float(88.0), 0);
                assertEquals(node.getHistory().getObservation(0).getWspdi(), new Float(0.0), 0);
                assertEquals(node.getHistory().getObservation(0).getVisi(), new Float(10.0), 0);

                //compare daily summery object parameters
                assertEquals(node.getHistory().getDailysummary(0).getMintempi(), 48, 0);
                assertEquals(node.getHistory().getDailysummary(0).getMaxtempi(), 69, 0);
                assertEquals(node.getHistory().getDailysummary(0).getMeanwindspdi(), 1, 0);
                assertEquals(node.getHistory().getDailysummary(0).getMeanvisi(), 8, 0);
            }

            @Override
            public void invalidURL() {

            }
        }).execute();

    }
}
