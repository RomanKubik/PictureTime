package com.example.kubik.picturetime.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kubik.picturetime.Navigate;
import com.example.kubik.picturetime.R;
import com.example.kubik.picturetime.api.ApiClient;
import com.example.kubik.picturetime.api.ApiInterface;
import com.example.kubik.picturetime.models.photos.PhotoDetails;
import com.squareup.picasso.Picasso;

import java.util.Objects;

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
    @BindView(R.id.tv_place)
    TextView tvPlace;
    @BindView(R.id.tv_author_bio)
    TextView tvAuthorBio;
    @BindView(R.id.tv_camera_info)
    TextView tvCameraInfo;

    private ApiInterface mApiService;
    private PhotoDetails mPhotoDetails;
    private String mImageId;

    private static String sToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_image);

        getExtras();
        sToken = loadToken();
        mApiService = ApiClient.getClient().create(ApiInterface.class);
        showPhoto();
    }

    private void getExtras() {
        Intent intent = getIntent();
        mImageId = intent.getStringExtra(Navigate.EXTRA_IMAGE_ID);
    }


    private void showPhoto() {
        Call<PhotoDetails> call;
        if (mImageId == null) {
            call = mApiService.getRandomPhoto(appId);

        } else {
            call = mApiService.getPhoto(mImageId, appId);
        }
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

                if (mPhotoDetails.getLocation() != null) {
                    tvPlace.setText(mPhotoDetails.getLocation().toString());
                } else {
                    tvPlace.setText(R.string.msg_no_location);
                }

                if (!TextUtils.isEmpty(mPhotoDetails.getUser().getBio())) {
                    tvAuthorBio.setText(mPhotoDetails.getUser().getBio());
                } else {
                    tvAuthorBio.setText(R.string.msg_no_author_bio);
                }

                tvCameraInfo.setText(mPhotoDetails.getSettings().toString());
            }

            @Override
            public void onFailure(Call<PhotoDetails> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.iv_det_like)
    public void onLikeClicked() {
        if (sToken != null) {
            if (mPhotoDetails.isLiked()) {
                Call<PhotoDetails> call = mApiService.unlikePhoto(mPhotoDetails.getId(), appId);
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
                Call<PhotoDetails> call = mApiService.likePhoto(mPhotoDetails.getId(), appId);
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
            tvLikes.setText(String.valueOf(mPhotoDetails.getLikes()));
        } else {
            Toast.makeText(getApplicationContext(), R.string.err_auth, Toast.LENGTH_SHORT).show();
        }
    }
}
