package com.example.kubik.picturetime;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.example.kubik.picturetime.activities.LoginActivity;
import com.example.kubik.picturetime.activities.MainListActivity;

/** Helps navigate between activities
 * Created by Kubik on 1/12/17.
 */

public final class Navigate
{
    private Navigate(){}

    public static void toLoginActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void toMainListActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, MainListActivity.class));
    }
}
