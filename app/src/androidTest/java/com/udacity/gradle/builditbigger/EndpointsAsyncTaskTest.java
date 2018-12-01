package com.udacity.gradle.builditbigger;

import android.util.Log;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test class to check the joke is loaded or not
 */
public class EndpointsAsyncTaskTest implements EndpointsAsyncTask.JokeRecciveInterface {
    private final String TAG = EndpointsAsyncTaskTest.class.getSimpleName();
    private String joke;
    
    @Test
    public void doInBackground() {
        try {
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
            endpointsAsyncTask.execute();
            String result = endpointsAsyncTask.get(60, TimeUnit.SECONDS);
        
            assertNotNull(result);
            assertTrue(result.length() > 0);
            Log.e(TAG, "doInBackground: "+result );
        } catch (Exception e) {
            Log.e(TAG, "time out please try again later");
            fail();
        }
    }
    
    @Override
    public void onJokeReceive(String data) {
        this.joke = data;
        
    }
}