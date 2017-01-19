package com.example.kubik.picturetime;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.kubik.picturetime.activities.ImageActivity;
import com.example.kubik.picturetime.activities.LoginActivity;
import com.example.kubik.picturetime.activities.MainListActivity;
import com.example.kubik.picturetime.models.photos.PhotoDetails;

/** Helps navigate between activities
 * Created by Kubik on 1/12/17.
 */

public final class Navigate
{
    public static final String EXTRA_IMAGE_ID = "image_id";

    private Navigate(){}

    public static void toLoginActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void toMainListActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, MainListActivity.class));
    }

    public static void toImageActivity(@NonNull Context context, @Nullable String id) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(EXTRA_IMAGE_ID, id);
        context.startActivity(intent);
    }
}
