package com.bkm.bexflowsample.ui.dialogs;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;

import com.bkm.mobil.bexflowsdk.R;


public class AlertDialogBuilder extends AlertDialog.Builder {



    public AlertDialogBuilder(Context context) {
        super(context);
    }

    public AlertDialogBuilder(Context context, int theme) {
        super(context, theme);
    }

    public AlertDialogBuilder(Context context, String customTitle) {

        super(context);
        AppCompatTextView apptxtDialogTitle;
        View viewDialogTitle = LayoutInflater.from(context).inflate(R.layout.bxflow_dialog_title, null);
        apptxtDialogTitle = (AppCompatTextView) viewDialogTitle.findViewById(R.id.apptxt_dialog_title);
        apptxtDialogTitle.setText(customTitle);
        setCustomTitle(viewDialogTitle);
    }
}
