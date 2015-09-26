/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.sknutti.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.sknutti.JokeSource;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.sknutti.com",
                ownerName = "backend.builditbigger.sknutti.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that returns a joke
     */
    @ApiMethod(name = "getJoke")
    public Joke getJoke() {
        Joke response = new Joke();

        JokeSource jokeSource = new JokeSource();
        String joke = jokeSource.getJoke();
        response.setData(joke);

        return response;
    }

}
