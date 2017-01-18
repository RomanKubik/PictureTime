package com.example.kubik.picturetime.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.example.kubik.picturetime.Navigate;
import com.example.kubik.picturetime.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Splash screen
 * Created by Kubik on 1/12/17.
 */

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_SCREEN_DURATION = 3000;

    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (loadToken() != null) {
                    Navigate.toMainListActivity(getApplicationContext());
                } else {
                    Navigate.toLoginActivity(getApplicationContext());
                }
                finish();
            }
        }, SPLASH_SCREEN_DURATION);

    }
}
