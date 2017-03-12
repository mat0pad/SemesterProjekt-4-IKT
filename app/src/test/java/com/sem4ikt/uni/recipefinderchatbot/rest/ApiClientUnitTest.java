package com.sem4ikt.uni.recipefinderchatbot.rest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import retrofit2.Retrofit;

/**
 * Created by mathiaslykkepedersen on 11/03/2017.
 */

public class ApiClientUnitTest {

    private IApiClient apiClient = null;

    @Before
    public void setUp() {
        apiClient = new ApiClient();
    }

    @Test
    public void getClientNotNull(){

        Retrofit test = apiClient.getClient();

        Assert.assertNotNull(test);
    }

    @Test
    public void getClientStaticTest(){

        Retrofit test = apiClient.getClient();

        Assert.assertEquals(test, apiClient.getClient());
    }
}
