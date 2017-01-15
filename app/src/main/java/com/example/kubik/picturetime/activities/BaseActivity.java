package com.example.kubik.picturetime.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
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

    private static SharedPreferences sPreferences;

    protected static final String PREF_TOKEN = "token";

    protected static void saveToken(String token) {
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putString(PREF_TOKEN, token);
        editor.commit();
    }

    @Nullable
    protected static String loadToken() {
        return sPreferences.getString(PREF_TOKEN, null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (sPreferences == null) sPreferences = getPreferences(MODE_PRIVATE);
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
