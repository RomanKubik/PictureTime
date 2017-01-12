package com.example.kubik.picturetime.api;

import com.example.kubik.picturetime.models.photos.PhotoDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kubik on 1/12/17.
 */

public interface ApiInterface {

    @GET("photos")
    Call<List<PhotoDetails>> getPhotosList(@Query("client_id") String appId,
                                           @Query("page") int page,
                                           @Query("order_by") String orderBy);
}
