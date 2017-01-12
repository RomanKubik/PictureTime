package com.example.kubik.picturetime.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.kubik.picturetime.R;
import com.example.kubik.picturetime.adapters.MainPhotoListAdapter;
import com.example.kubik.picturetime.api.ApiClient;
import com.example.kubik.picturetime.api.ApiInterface;
import com.example.kubik.picturetime.models.photos.PhotoDetails;

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

    private List<PhotoDetails> mPhotoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        loadData();
        setRecyclerView();
    }

    private void loadData() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<PhotoDetails>> call = apiService.getPhotosList(appId, 1, "latest");
        call.enqueue(new Callback<List<PhotoDetails>>() {
            @Override
            public void onResponse(Call<List<PhotoDetails>> call, Response<List<PhotoDetails>> response) {
                mPhotoList = response.body();
                mPhotoListAdapter.addItemsToList(mPhotoList);
            }

            @Override
            public void onFailure(Call<List<PhotoDetails>> call, Throwable t) {
                Log.e("MyTag", t.getMessage());
            }
        });
    }

    private void setRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mPhotoListAdapter = new MainPhotoListAdapter(this, mPhotoList);
        rvMainList.setAdapter(mPhotoListAdapter);
        rvMainList.setLayoutManager(mLayoutManager);
    }

}
