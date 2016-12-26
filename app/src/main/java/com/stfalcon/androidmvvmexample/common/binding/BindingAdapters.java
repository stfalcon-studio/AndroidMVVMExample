package com.stfalcon.androidmvvmexample.common.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stfalcon.androidmvvmexample.R;

/*
 * Created by troy379 on 15.03.16.
 */
public final class BindingAdapters {
    private BindingAdapters() {
        throw new AssertionError();
    }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.bg_image_holder)
                .into(view);
    }

    @BindingAdapter("app:onClick")
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(v -> runnable.run());
    }
}
