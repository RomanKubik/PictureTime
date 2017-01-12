package com.example.kubik.picturetime.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.kubik.picturetime.R;
import com.example.kubik.picturetime.api.ApiClient;
import com.example.kubik.picturetime.api.ApiInterface;
import com.example.kubik.picturetime.models.photos.PhotoDetails;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kubik on 1/13/17.
 */

public class ImageActivity extends BaseActivity {

    @BindView(R.id.iv_random_image)
    ImageView ivRandomPicture;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_image);

        showRandomPhoto();
    }

    private void showRandomPhoto() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<PhotoDetails> call = apiService.getRandomPhoto(appId);
        call.enqueue(new Callback<PhotoDetails>() {
            @Override
            public void onResponse(Call<PhotoDetails> call, Response<PhotoDetails> response) {
                PhotoDetails photoDetails = response.body();
                Picasso.with(getApplicationContext())
                        .load(photoDetails.getUrls().getRegular())
                        .fit()
                        .centerCrop()
                        .into(ivRandomPicture);
            }

            @Override
            public void onFailure(Call<PhotoDetails> call, Throwable t) {

            }
        });
    }
}
