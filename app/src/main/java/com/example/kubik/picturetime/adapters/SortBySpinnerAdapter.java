package com.example.kubik.picturetime.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kubik.picturetime.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kubik on 1/12/17.
 */

public class SortBySpinnerAdapter extends ArrayAdapter<String> {

    private List<String> mCategoryList = new ArrayList<>();
    private LayoutInflater inflater;

    public SortBySpinnerAdapter(Context context, List<String> objects) {
        super(context, R.layout.item_spinner, objects);

        mCategoryList = objects;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, parent);
    }

    private View getCustomView(int position, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_spinner, parent, false);
        TextView tvCategory = (TextView) view.findViewById(R.id.tv_sp_item);
        tvCategory.setText(mCategoryList.get(position));
        return view;
    }
}
