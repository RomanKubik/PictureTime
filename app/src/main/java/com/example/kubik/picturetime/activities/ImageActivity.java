package com.example.kubik.picturetime.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kubik.picturetime.R;
import com.example.kubik.picturetime.api.ApiClient;
import com.example.kubik.picturetime.api.ApiInterface;
import com.example.kubik.picturetime.models.photos.PhotoDetails;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kubik on 1/13/17.
 */

public class ImageActivity extends BaseActivity {

    @BindView(R.id.iv_random_image)
    ImageView ivRandomPicture;
    @BindView(R.id.iv_det_like)
    ImageView ivLiked;
    @BindView(R.id.tv_likes)
    TextView tvLikes;
    @BindView(R.id.tv_author)
    TextView tvAuthor;

    private ApiInterface mApiService;
    private PhotoDetails mPhotoDetails;

    private static String sToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_image);

        sToken = loadToken();
        mApiService = ApiClient.getClient().create(ApiInterface.class);
        showRandomPhoto();
    }


    private void showRandomPhoto() {
        Call<PhotoDetails> call = mApiService.getRandomPhoto(appId);
        call.enqueue(new Callback<PhotoDetails>() {
            @Override
            public void onResponse(Call<PhotoDetails> call, Response<PhotoDetails> response) {
                mPhotoDetails = response.body();
                Picasso.with(getApplicationContext())
                        .load(mPhotoDetails.getUrls().getRegular())
                        .fit()
                        .centerCrop()
                        .into(ivRandomPicture);
                tvAuthor.setText(mPhotoDetails.getUser().getUsername());
                tvLikes.setText(String.valueOf(mPhotoDetails.getLikes()));
                if (mPhotoDetails.isLiked()) {
                    ivLiked.setImageResource(R.drawable.filled_heart);
                } else {
                    ivLiked.setImageResource(R.drawable.heart);
                }
            }

            @Override
            public void onFailure(Call<PhotoDetails> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.cv_activity_image)
    public void onLikeClicked() {
        if (sToken != null) {
            if (mPhotoDetails.isLiked()) {
                Call<PhotoDetails> call = mApiService.likePhoto(mPhotoDetails.getId(), appId);
                call.enqueue(new Callback<PhotoDetails>() {
                    @Override
                    public void onResponse(Call<PhotoDetails> call, Response<PhotoDetails> response) {
                        ivLiked.setImageResource(R.drawable.heart);
                        mPhotoDetails = response.body();
                    }

                    @Override
                    public void onFailure(Call<PhotoDetails> call, Throwable t) {

                    }
                });
            } else {
                Call<PhotoDetails> call = mApiService.unlikePhoto(mPhotoDetails.getId(), appId);
                call.enqueue(new Callback<PhotoDetails>() {
                    @Override
                    public void onResponse(Call<PhotoDetails> call, Response<PhotoDetails> response) {
                        ivLiked.setImageResource(R.drawable.filled_heart);
                        mPhotoDetails = response.body();
                    }

                    @Override
                    public void onFailure(Call<PhotoDetails> call, Throwable t) {

                    }
                });
            }
        } else {
            Toast.makeText(getApplicationContext(), R.string.err_auth, Toast.LENGTH_SHORT).show();
        }
    }
}
