package com.example.builditbigger;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.mock.MockContext;
import android.util.Log;
import android.util.Pair;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.frag.MainActivityFragment;
import com.udacity.gradle.builditbigger.free.EndpointsAsyncTask;
import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
public class AsyncEndpointsTestFree extends TestCase {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void test()throws Exception {

            String result = null;
            Context context = getInstrumentation().getTargetContext();
            System.out.println("context val" + " " + context);
            result = new EndpointsAsyncTask(context,null).execute(new Pair<Context, String>(context, "retrieveJoke")).get();
            System.out.println("*********RESULT********* = "+result);
            org.junit.Assert.assertNotNull(result);
            Log.i("ASSERTION",result);

    }
}