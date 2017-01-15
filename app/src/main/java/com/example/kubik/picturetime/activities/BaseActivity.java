package com.example.kubik.picturetime.activities;

import android.content.SharedPreferences;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.kubik.picturetime.R;

import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Base class for all activities in app
 * Created by Kubik on 1/12/17.
 */

public class BaseActivity extends AppCompatActivity {

    @BindString(R.string.app_id)
    String appId;

    private SharedPreferences sPreferences = getPreferences(MODE_PRIVATE);


    protected static final String PREF_TOKEN = "token";

    protected void saveToken(String token) {
        sPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putString(PREF_TOKEN, token);
        editor.apply();
    }

    @Nullable
    protected String loadToken() {
        return sPreferences.getString(PREF_TOKEN, null);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);

    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);

    }
}
