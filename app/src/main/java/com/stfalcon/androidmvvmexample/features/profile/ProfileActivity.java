package com.stfalcon.androidmvvmexample.features.profile;

import android.content.Context;
import android.content.Intent;

import com.stfalcon.androidmvvmexample.BR;
import com.stfalcon.androidmvvmexample.R;
import com.stfalcon.androidmvvmexample.databinding.ActivityProfileBinding;
import com.stfalcon.androidmvvmhelper.mvvm.activities.BindingActivity;

/*
 * Created by troy379 on 23.12.16.
 */
public class ProfileActivity
        extends BindingActivity<ActivityProfileBinding, ProfileActivityVM> {

    private static final String KEY_STATUS = "STATUS";

    public static void open(Context context, String status) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(KEY_STATUS, status);
        context.startActivity(intent);
    }

    @Override
    public ProfileActivityVM onCreate() {
        setSupportActionBar(getBinding().toolbar);

        return new ProfileActivityVM(this,
                getIntent().getStringExtra(KEY_STATUS));
    }

    @Override
    public int getVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_profile;
    }

}