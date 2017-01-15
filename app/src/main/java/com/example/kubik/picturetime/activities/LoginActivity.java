package com.example.kubik.picturetime.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.SharedPreferencesCompat;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.kubik.picturetime.Navigate;
import com.example.kubik.picturetime.R;
import com.example.kubik.picturetime.api.ApiClient;
import com.example.kubik.picturetime.api.ApiInterface;
import com.example.kubik.picturetime.models.AuthRequest;
import com.example.kubik.picturetime.models.AuthResponse;
import com.google.gson.Gson;
import com.google.gson.internal.Streams;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kubik on 1/12/17.
 */

public class LoginActivity extends BaseActivity {

    @BindString(R.string.app_id)
    String appId;
    @BindString(R.string.app_secret)
    String secret;
    @BindString(R.string.redirect_url)
    String redirectUrl;
    @BindString(R.string.grant_type)
    String grantType;

    @BindView(R.id.wv_auth)
    WebView wvAuth;

    private AuthResponse mResponse;
    private SharedPreferences mSharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wvAuth.loadUrl("https://unsplash.com/oauth/authorize?client_id=633ad3340d3e2f38c040ae773e0c5ec161b9ebe3a683b7560df147f59945ed99&redirect_uri=urn%3Aietf%3Awg%3Aoauth%3A2.0%3Aoob&response_type=code&scope=public+write_likes");
        wvAuth.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.toLowerCase().contains("https://unsplash.com/oauth/authorize/")) {
                    int idx = url.lastIndexOf('/');
                    String code = url.substring(idx + 1);
                    ApiInterface apiService = ApiClient.getOAuthClient().create(ApiInterface.class);
                    AuthRequest request = new AuthRequest(appId, secret, redirectUrl, code, grantType);
                    Call<AuthResponse> call = apiService.authUser(request);
                    call.enqueue(new Callback<AuthResponse>() {
                        @Override
                        public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                            mResponse = response.body();
                            saveToken(mResponse.getAccess_token());
                            Navigate.toMainListActivity(getApplicationContext());
                            finish();
                        }

                        @Override
                        public void onFailure(Call<AuthResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    @OnClick(R.id.btn_skip)
    public void onSkip() {
        Navigate.toMainListActivity(this);
        finish();
    }
}
