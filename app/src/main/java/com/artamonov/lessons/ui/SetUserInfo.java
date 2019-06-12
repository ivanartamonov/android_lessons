package com.artamonov.lessons.ui;

import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.artamonov.lessons.R;
import com.artamonov.lessons.models.User;
import com.bumptech.glide.Glide;

public class SetUserInfo {
    public static void execute(AppCompatActivity activity, User user) {
        Log.d("InitUser", user.getName());
        Log.d("InitUser", activity.getCallingActivity().toString());
        TextView tvUserName = activity.findViewById(R.id.tv_user_name);
        ImageView userAvatar = activity.findViewById(R.id.img_user_avatar);

        //tvUserName.setText(user.getName());
/*
        Glide.with(activity)
                .load(user.getAvatarUrl())
                .into(userAvatar);*/
    }
}