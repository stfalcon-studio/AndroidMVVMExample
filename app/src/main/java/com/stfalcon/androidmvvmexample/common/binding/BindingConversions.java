package com.stfalcon.androidmvvmexample.common.binding;

import android.databinding.BindingConversion;
import android.view.View;

/*
 * Created by troy379 on 16.03.16.
 */
public final class BindingConversions {
    private BindingConversions() {
        throw new AssertionError();
    }

    @BindingConversion
    public static int convertBooleanToVisibility(boolean visible) {
        return visible ? View.VISIBLE : View.GONE;
    }

}
