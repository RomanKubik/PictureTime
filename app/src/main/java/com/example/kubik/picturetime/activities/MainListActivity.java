package com.example.kubik.picturetime.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.kubik.picturetime.R;
import com.example.kubik.picturetime.adapters.MainPhotoListAdapter;
import com.example.kubik.picturetime.api.ApiClient;
import com.example.kubik.picturetime.api.ApiInterface;
import com.example.kubik.picturetime.models.photos.PhotoDetails;
import com.example.kubik.picturetime.utils.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainListActivity extends BaseActivity {

    @BindString(R.string.app_id)
    String appId;

    @BindView(R.id.rv_main_list)
    RecyclerView rvMainList;

    private LinearLayoutManager mLayoutManager;
    private MainPhotoListAdapter mPhotoListAdapter;
    private EndlessRecyclerViewScrollListener mScrollListener;

    private List<PhotoDetails> mPhotoList = new ArrayList<>();

    private static int sCurrentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        setRecyclerView();
        loadData();
    }

    private void setRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mPhotoListAdapter = new MainPhotoListAdapter(this, mPhotoList);
        mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
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
            public void onItemClicked(View view, int position) {
                Log.d("MyTag", String.valueOf(position));
            }
        });
    }

    private void loadData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<PhotoDetails>> call = apiService.getPhotosList(appId, sCurrentPage, "latest");
        call.enqueue(new Callback<List<PhotoDetails>>() {
            @Override
            public void onResponse(Call<List<PhotoDetails>> call, Response<List<PhotoDetails>> response) {
                mPhotoList = response.body();
                mPhotoListAdapter.addItemsToList(mPhotoList);
                sCurrentPage++;
            }

            @Override
            public void onFailure(Call<List<PhotoDetails>> call, Throwable t) {
                Log.e("MyTag", t.getMessage());
            }
        });
    }

}
