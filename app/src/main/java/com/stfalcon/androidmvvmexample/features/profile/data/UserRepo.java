package com.stfalcon.androidmvvmexample.features.profile.data;

import com.stfalcon.androidmvvmexample.Demo;
import com.stfalcon.androidmvvmexample.common.models.User;

/*
 * Created by troy379 on 23.12.16.
 */
public class UserRepo implements IUserRepo {

    @Override
    public void getUser(Loader<User> loader) {
        Demo.simulateLoading(() -> loader.onLoaded(Demo.getUser()), false);
    }
}
