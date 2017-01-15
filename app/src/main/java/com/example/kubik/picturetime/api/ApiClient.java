package com.example.kubik.picturetime.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kubik on 1/12/17.
 */

public class ApiClient {

    public static final String BASE_URL = "https://api.unsplash.com/";
    public static final String OAUTH_URL = "https://unsplash.com/";


    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit getAuthorizedClient(final String token) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain
                        .request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer ".concat(token))
                        .build();
                return chain.proceed(request);
            }
        });

        OkHttpClient httpClient = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        return retrofit;
    }

    public static Retrofit getOAuthClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(OAUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
