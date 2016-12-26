package com.stfalcon.androidmvvmexample.features.profile;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;

import com.stfalcon.androidmvvmexample.BR;
import com.stfalcon.androidmvvmexample.Demo;
import com.stfalcon.androidmvvmexample.R;
import com.stfalcon.androidmvvmexample.common.binding.fields.CustomObservableInt;
import com.stfalcon.androidmvvmexample.common.binding.fields.RecyclerConfiguration;
import com.stfalcon.androidmvvmexample.common.models.User;
import com.stfalcon.androidmvvmexample.common.ui.adapters.RecyclerBindingAdapter;
import com.stfalcon.androidmvvmexample.features.dialogs.input.InputDialog;
import com.stfalcon.androidmvvmexample.features.profile.data.IUserRepo;
import com.stfalcon.androidmvvmexample.features.profile.data.UserRepo;
import com.stfalcon.androidmvvmexample.utils.AppUtilities;
import com.stfalcon.androidmvvmexample.utils.TimerTask;
import com.stfalcon.androidmvvmhelper.mvvm.activities.ActivityViewModel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

/*
 * Created by troy379 on 23.12.16.
 */
public class ProfileActivityVM
        extends ActivityViewModel<ProfileActivity> {

    public ProfileActivityVM(ProfileActivity activity, String status) {
        super(activity);
        this.userRepo = new UserRepo();
        this.status.set(status);

        initRecycler();
        startTimer();
    }

    private static final int LAYOUT_HOLDER = R.layout.item_photo;
    private static final int PHOTOS_COLUMN_COUNT = 3;

    public final ObservableField<String> status = new ObservableField<>();
    public final ObservableBoolean isLoading = new ObservableBoolean();
    public final ObservableField<Boolean> isFriend = new ObservableField<>();
    public final CustomObservableInt friendsCount = new CustomObservableInt();
    public final CustomObservableInt starsCount = new CustomObservableInt();
    public final RecyclerConfiguration recyclerConfiguration = new RecyclerConfiguration();
    public final ObservableField<User> user = new ObservableField<>();
    public final ObservableField<String> field = new ObservableField<String>() {
        @Override
        public String get() {
            return super.get();
        }

        @Override
        public void set(String value) {
            super.set(value);
        }
    };

    private IUserRepo userRepo;

    @Override
    public void onResume() {
        isLoading.set(this.user.get() == null);
        userRepo.getUser(this::onUserLoaded);
    }

    public void changeFriendshipStatus() {
        boolean oldValue = isFriend.get();
        isFriend.set(null);
        Demo.simulateLoading(() -> {
            isFriend.set(oldValue);
            if (isFriend.get()) friendsCount.decrement();
            else friendsCount.increment();
            isFriend.set(!isFriend.get());
        }, false);
    }

    public void edit() {
        InputDialog.show(activity, status);
    }

    public void showDevMessage() {
        AppUtilities.showSnackbar(activity, R.string.dev_message, false);
    }

    private void onUserLoaded(User user) {
        isLoading.set(false);

        this.user.set(user);
        status.set(user.getStatus());
        isFriend.set(user.isFriend());
        friendsCount.set(user.getCounters().getFriends());
        starsCount.set(user.getCounters().getStars());
    }

    private void initRecycler() {
        RecyclerBindingAdapter<String> adapter = getAdapter();

        recyclerConfiguration.setLayoutManager(new GridLayoutManager(activity, PHOTOS_COLUMN_COUNT));
        recyclerConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerConfiguration.setAdapter(adapter);
    }

    private RecyclerBindingAdapter<String> getAdapter() {
        ArrayList<String> photos = Demo.getPhotos();
        RecyclerBindingAdapter<String> adapter = new RecyclerBindingAdapter<>(LAYOUT_HOLDER, BR.url, photos);
        adapter.setOnItemClickListener((position, item)
                -> AppUtilities.showSnackbar(
                activity,
                activity.getString(R.string.photo_message, position + 1),
                false));
        return adapter;
    }

    private void startTimer() {
        new Timer().schedule(
                new TimerTask(() -> starsCount.set(starsCount.get() + 1)),
                0, new Random().nextInt(Demo.LOADING_LONG));
    }

}