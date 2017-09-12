package com.example.kubik.picturetime.adapters;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kubik.picturetime.R;
import com.example.kubik.picturetime.models.photos.PhotoDetails;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Helps display pictures in recycler view using cards
 * Created by Kubik on 1/12/17.
 */

public class MainPhotoListAdapter extends StrangeAdapter<PhotoDetails> {

    private static OnItemClickListener onItemClickListener;

    public MainPhotoListAdapter(@ColorInt int pourColor) {
        super(pourColor);
    }

    @Override
    public StrangeHolder<PhotoDetails> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_main_list, parent, false);
        return new ViewHolder(view, pourColor);
    }

    @Override
    public void onBindViewHolder(StrangeHolder<PhotoDetails> holder, int position) {
        holder.setItem(itemList.get(position));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        MainPhotoListAdapter.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    public static class ViewHolder extends StrangeHolder<PhotoDetails> {

        private Context mContext;

        @BindView(R.id.cv_item_root)
        CardView cvContainer;
        @BindView(R.id.iv_item_main_list)
        ImageView ivPhoto;
        @BindView(R.id.tv_item_main_list_author)
        TextView tvAuthor;
        @BindView(R.id.tv_item_main_list_likes)
        TextView tvLikes;
        @BindView(R.id.iv_like)
        ImageView ivLiked;
        @BindView(R.id.iv_icon_author)
        ImageView ivAuthorIcon;

        public ViewHolder(View itemView, @ColorInt int color) {
            super(itemView, color);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setItem(PhotoDetails photoDetails) {
            super.setItem(photoDetails);
            if (photoDetails != null) {
                Picasso.with(mContext)
                        .load(photoDetails.getUser().getProfileImage().getMedium())
                        .fit()
                        .centerCrop()
                        .into(ivAuthorIcon);

                Picasso.with(mContext)
                        .load(photoDetails.getUrls().getRegular())
                        .fit()
                        .centerCrop()
                        .into(ivPhoto);

                tvAuthor.setText(photoDetails.getUser().getUsername());
                tvLikes.setText(String.valueOf(photoDetails.getLikes()));
                if (photoDetails.isLiked()) {
                    ivLiked.setImageResource(R.drawable.ic_favorite);
                } else {
                    ivLiked.setImageResource(R.drawable.ic_favorite_border);
                }
            }
        }

        @OnClick(R.id.cv_item_root)
        public void onClick() {
            onItemClickListener.onItemClicked(getAdapterPosition());
        }
    }
}

