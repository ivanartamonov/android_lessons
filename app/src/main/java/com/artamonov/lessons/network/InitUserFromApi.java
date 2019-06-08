package com.artamonov.lessons.network;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.artamonov.lessons.R;
import com.artamonov.lessons.models.User;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InitUserFromApi implements Callback<User> {

    private AppCompatActivity activity;

    public InitUserFromApi(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        User user = response.body();

        if (user != null) {
            TextView tvUserName = this.activity.findViewById(R.id.tv_user_name);
            ImageView userAvatar = this.activity.findViewById(R.id.img_user_avatar);

            tvUserName.setText(user.getName());

            Glide.with(this.activity)
                    .load(user.getAvatarUrl())
                    .into(userAvatar);
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        t.printStackTrace();
    }
}
