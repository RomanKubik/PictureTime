package com.example.kubik.picturetime.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.kubik.picturetime.Navigate;
import com.example.kubik.picturetime.R;

/**
 * Splash screen
 * Created by Kubik on 1/12/17.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Navigate.toLoginActivity(this);
    }
}
