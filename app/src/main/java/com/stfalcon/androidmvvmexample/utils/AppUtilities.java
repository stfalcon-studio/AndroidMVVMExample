package com.stfalcon.androidmvvmexample.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;

/*
 * Created by troy379 on 16.03.16.
 */
public final class AppUtilities {
    private AppUtilities() {
        throw new AssertionError();
    }

    public static void showSnackbar(Context context, @StringRes int stringRes, boolean isLong) {
        showSnackbar(context, context.getString(stringRes), isLong);
    }

    public static void showSnackbar(Context context, String text, boolean isLong) {
        if (context instanceof Activity) {
            View rootView = ((ViewGroup) ((Activity) context).findViewById(android.R.id.content)).getChildAt(0);
            if (rootView instanceof CoordinatorLayout)
                showSnackbar((CoordinatorLayout) rootView, text, isLong);
        }
    }

    public static void showSnackbar(CoordinatorLayout layout, @StringRes int stringRes, boolean isLong) {
        showSnackbar(layout, layout.getResources().getString(stringRes), isLong);
    }

    public static void showSnackbar(CoordinatorLayout layout, String text, boolean isLong) {
        Snackbar snack = Snackbar.make(layout, text,
                isLong ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_SHORT);
        snack.setAction(android.R.string.ok, v -> {
        });
        snack.show();
    }
}
