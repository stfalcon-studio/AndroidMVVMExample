package com.stfalcon.androidmvvmexample.features.profile.data;

import com.stfalcon.androidmvvmexample.common.models.User;

/*
 * Created by troy379 on 23.12.16.
 */
public interface IUserRepo {

    void getUser(Loader<User> loader);

    interface Loader<T> {
        void onLoaded(T t);
    }

}
