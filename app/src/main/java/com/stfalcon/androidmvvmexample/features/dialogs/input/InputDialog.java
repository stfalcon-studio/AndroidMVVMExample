package com.stfalcon.androidmvvmexample.features.dialogs.input;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.stfalcon.androidmvvmexample.R;
import com.stfalcon.androidmvvmexample.databinding.ItemInputDialogBinding;

/*
 * Created by troy379 on 26.12.16.
 */
public class InputDialog {

    public static void show(Context context, ObservableField<String> text) {
        String oldText = text.get();
        ItemInputDialogBinding dialogBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.item_input_dialog, null, false);
        dialogBinding.setViewModel( new InputDialogVM(text));
        int margin = (int) context.getResources().getDimension(R.dimen.activity_horizontal_margin);

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
        builder.setPositiveButton(android.R.string.ok, null);
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> text.set(oldText));
        builder.setOnCancelListener(dialog -> text.set(oldText));
        builder.setView(dialogBinding.getRoot(), margin, margin, margin, margin);
        builder.show();
    }
}
