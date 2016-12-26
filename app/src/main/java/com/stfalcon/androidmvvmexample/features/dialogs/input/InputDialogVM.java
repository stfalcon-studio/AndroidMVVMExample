package com.stfalcon.androidmvvmexample.features.dialogs.input;

import android.databinding.ObservableField;

/*
 * Created by troy379 on 26.12.16.
 */
public class InputDialogVM {

    public ObservableField<String> text;

    public InputDialogVM(ObservableField<String> text) {
        this.text = text;
    }

}
