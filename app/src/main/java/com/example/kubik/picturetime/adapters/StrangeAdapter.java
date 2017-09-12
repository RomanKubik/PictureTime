package com.example.kubik.picturetime.adapters;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roman.kubik on 9/7/17.
 */

public abstract class StrangeAdapter<T> extends RecyclerView.Adapter<StrangeAdapter.StrangeHolder<T>> {

    protected List<T> itemList = new ArrayList<>();
    @ColorInt
    protected int pourColor;

    private List<Integer> emptyPositions = new ArrayList<>();

    public StrangeAdapter(@ColorInt int pourColor) {
        this.pourColor = pourColor;
        addEmptyList();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private void addEmptyItem() {
        itemList.add(null);
        emptyPositions.add(itemList.size() - 1);
    }

    private void addEmptyList() {
        itemList.add(null);
        emptyPositions.add(itemList.size() - 1);
        itemList.add(null);
        emptyPositions.add(itemList.size() - 1);
        itemList.add(null);
        emptyPositions.add(itemList.size() - 1);
        itemList.add(null);
        emptyPositions.add(itemList.size() - 1);
        itemList.add(null);
        emptyPositions.add(itemList.size() - 1);
        itemList.add(null);
        emptyPositions.add(itemList.size() - 1);
    }

    public void clearData() {
        itemList.clear();
        emptyPositions.clear();
        addEmptyList();
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        for (int i = emptyPositions.size() - 1; i >= 0; i--) {
            itemList.remove(emptyPositions.get(i).intValue());
        }
        emptyPositions.clear();
        itemList.addAll(data);
        addEmptyItem();
        notifyDataSetChanged();
    }

    public static class StrangeHolder<T> extends RecyclerView.ViewHolder {

        private static final String VALUE_IMAGE_VIEW = "image_view";
        private static final String VALUE_TEXT_VIEW = "text_view";
        private static final String VALUE_VIEW = "view";

        private static final String EMPTY_TEXT = "\t\t\t\t\t\t\t\t\t";

        private Map<Integer, String> viewTypeIdMap = new HashMap<>();

        private View itemView;

        @ColorInt
        private int pourColor;

        public StrangeHolder(View itemView, @ColorInt int pourColor) {
            super(itemView);
            this.pourColor = pourColor;
            this.itemView = itemView;
            if (itemView instanceof ViewGroup) {
                createViewIdMap((ViewGroup) itemView);
            }
        }

        public void setItem(T item) {
            if (item == null) {
                setBackgrounds(pourColor);
            } else {
                setBackgrounds(Color.TRANSPARENT);
            }
        }

        private void createViewIdMap(ViewGroup viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View v = viewGroup.getChildAt(i);
                if (v instanceof  ViewGroup) {
                    createViewIdMap((ViewGroup) v);
                } else if (v instanceof ImageView) {
                    viewTypeIdMap.put(v.getId(), VALUE_IMAGE_VIEW);
                } else if (v instanceof TextView) {
                    viewTypeIdMap.put(v.getId(), VALUE_TEXT_VIEW);
                } else {
                    viewTypeIdMap.put(v.getId(), VALUE_VIEW);
                }
            }
        }

        private void setBackgrounds(@ColorInt int pourColor) {
            for (Integer id : viewTypeIdMap.keySet()) {
                String viewType = viewTypeIdMap.get(id);
                switch (viewType) {
                    case VALUE_IMAGE_VIEW:
                        ImageView iv = ((ImageView)itemView.findViewById(id));
                        iv.setImageDrawable(null);
                        iv.setBackgroundColor(pourColor);
                        break;
                    case VALUE_TEXT_VIEW:
                        TextView tv = (TextView) itemView.findViewById(id);
                        tv.setText(EMPTY_TEXT);
                        tv.setBackgroundColor(pourColor);
                        break;
                    case VALUE_VIEW:
                        itemView.findViewById(id).setBackgroundColor(pourColor);
                        break;
                }
            }
        }

    }
}