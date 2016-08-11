package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> implements EndpointAsyncTask.JokeReceiver{

    String joke;
    boolean isJokeReceived = false;


    public ApplicationTest() {
        super(Application.class);

        while(!isJokeReceived);
        assertNotNull(joke);
        assertTrue("Joke not empty", !joke.isEmpty());
    }

    @Override
    public void retrivedJoke(String s) {
        joke = s;
        isJokeReceived = true;
    }
}