package com.udacity.gradle.builditbigger;

import org.junit.Test;

/**
 * Created by sknutti on 9/25/15.
 */
public class FetchJokeTaskTest {
    @Test
    public void verifyNonEmptyReturnString() {
        String joke = "";
        try {
            FetchJokeTask task = new FetchJokeTask();
            task.execute();
            joke = task.get();

            assert joke.length() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}