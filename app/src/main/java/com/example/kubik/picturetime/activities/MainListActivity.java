package com.example.kubik.picturetime.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kubik.picturetime.Navigate;
import com.example.kubik.picturetime.R;
import com.example.kubik.picturetime.adapters.SortBySpinnerAdapter;
import com.example.kubik.picturetime.api.ApiClient;
import com.example.kubik.picturetime.api.ApiInterface;
import com.example.kubik.picturetime.kotlin.MainPhotoListAdapter;
import com.example.kubik.picturetime.models.photos.PhotoDetails;
import com.example.kubik.picturetime.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainListActivity extends BaseActivity implements ImageActivity.OnPhotoLikedListener {

    @BindView(R.id.rv_main_list)
    RecyclerView rvMainList;

    @BindView(R.id.tb_activity_main)
    Toolbar tbActivityMain;

    @BindView(R.id.sp_activity_main)
    Spinner spSortCategories;

    @BindView(R.id.iv_random_picture)
    ImageView ivRandomImage;

    private MainPhotoListAdapter mPhotoListAdapter;

    private List<String> mSortCategories = new ArrayList<>();

    private ApiInterface mApiService;

    private static int sCurrentPage = 1;
    private static String sCurrentSortCategory;
    private static String sToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        sToken = loadToken();
        setToolbar();
        setRecyclerView();
        ImageActivity.setOnPhotoLikedListener(this);
    }

    private void setToolbar() {
        Collections.addAll(mSortCategories, getResources().getStringArray(R.array.sort_categories));
        sCurrentSortCategory = mSortCategories.get(0);

        setSupportActionBar(tbActivityMain);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SortBySpinnerAdapter spinnerAdapter = new SortBySpinnerAdapter(this, mSortCategories);
        spSortCategories.setAdapter(spinnerAdapter);
        spSortCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sCurrentPage = 1;
                sCurrentSortCategory = mSortCategories.get(i);
                mPhotoListAdapter.clearData();
                loadData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mPhotoListAdapter = new MainPhotoListAdapter(getResources().getColor(R.color.colorPrimary));
        EndlessRecyclerViewScrollListener mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d("MyTag", "onLoadMore");
                loadData();
            }
        };
        rvMainList.setAdapter(mPhotoListAdapter);
        rvMainList.setLayoutManager(mLayoutManager);
        rvMainList.addOnScrollListener(mScrollListener);
        mPhotoListAdapter.setOnItemClickListener(new MainPhotoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
//                showPicture(photoDetails.getId());
            }
        });
        loadData();
    }

    private void showPicture(String id) {
        Navigate.toImageActivity(this, id);
    }

    private void loadData() {
        if (sToken != null) {
            mApiService = ApiClient.getAuthorizedClient(sToken).create(ApiInterface.class);
        } else {
            mApiService = ApiClient.getClient().create(ApiInterface.class);
        }
        Call<List<PhotoDetails>> call = mApiService.getPhotosList(appId, sCurrentPage, 50, sCurrentSortCategory);
        call.enqueue(new Callback<List<PhotoDetails>>() {
            @Override
            public void onResponse(Call<List<PhotoDetails>> call, Response<List<PhotoDetails>> response) {
                if (response.body() == null) {
                    Toast.makeText(getApplicationContext() ,R.string.err_load_data, Toast.LENGTH_SHORT).show();
                    return;
                }
                List<PhotoDetails> list = response.body();
                mPhotoListAdapter.addData(list);
                sCurrentPage++;
            }

            @Override
            public void onFailure(Call<List<PhotoDetails>> call, Throwable t) {
                Log.e("MyTag", t.getMessage());
            }
        });
    }

    @OnClick(R.id.iv_random_picture)
    public void onRandomClicked() {
        Navigate.toImageActivity(this, null);
    }

    @Override
    public void onPhotoLiked(String id) {
        Log.d("MyTag", "Liked");
    }

    @Override
    public void onPhotoDisliked(String id) {
        Log.d("MyTag", "Disliked");
    }
}
