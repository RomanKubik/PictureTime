package com.example.kubik.picturetime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kubik.picturetime.R;
import com.example.kubik.picturetime.models.photos.PhotoDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Helps display pictures in recycler view using cards
 * Created by Kubik on 1/12/17.
 */

public class MainPhotoListAdapter extends RecyclerView.Adapter<MainPhotoListAdapter.ViewHolder> {

    private List<PhotoDetails> mPhotoList;
    private Context mContext;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MainPhotoListAdapter(Context context, List<PhotoDetails> photoList) {
        this.mContext = context;
        this.mPhotoList = photoList;
    }

    public void addItemsToList(List<PhotoDetails> list) {
        mPhotoList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearList() {
        mPhotoList.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_main_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PhotoDetails photoDetails = mPhotoList.get(position);

        Picasso.with(mContext)
                .load(photoDetails.getUrls().getRegular())
                .fit()
                .centerCrop()
                .into(holder.ivPhoto);

        holder.tvAuthor.setText(photoDetails.getUser().getUsername());
        holder.tvLikes.setText(String.valueOf(photoDetails.getLikes()));
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_item_main_list)
        ImageView ivPhoto;
        @BindView(R.id.tv_item_main_list_author)
        TextView tvAuthor;
        @BindView(R.id.tv_item_main_list_likes)
        TextView tvLikes;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClicked(view, getAdapterPosition());
            }
        }
    }
}
