package com.udacity.gradle.builditbigger;

import android.util.Log;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Pawan Khandal on 11/28/18,21
 */
public class EndpointsAsyncTaskTest {
    private final String TAG = EndpointsAsyncTaskTest.class.getSimpleName();
    
    @Test
    public void doInBackground() {
        try {
            MainActivity mainActivity = new MainActivity();
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(mainActivity);
            endpointsAsyncTask.execute();
            String result = endpointsAsyncTask.get(60, TimeUnit.SECONDS);
        
            assertNotNull(result);
            assertTrue(result.length() > 0);
            Log.e(TAG, "doInBackground: "+result );
        } catch (Exception e) {
            Log.e(TAG, "time out please try again later");
        }
    }
}