package com.bkm.bexflowsample.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import com.bkm.bexflowsample.R;
import com.bkm.bexflowsample.ui.dialogs.AlertDialogBuilder;


public class DialogUtil {


    public static void showErrorDialog(final Context context, String message,
                                       boolean finish) {
        if (!((Activity) context).isFinishing()) {
            new AlertDialogBuilder(context).setTitle(R.string.bxflow_error_title).setMessage(message)
                    .setPositiveButton(R.string.bxflow_dialog_ok, finish ? new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ((Activity) context).finish();
                        }
                    } : null).show();
        }

    }

    public static void showCommonAlertDialog(Context context, int title, int message) {
        new AlertDialogBuilder(context).setTitle(context.getString(title)).setMessage(context.getString(message)).setPositiveButton(context.getString(R.string.bxflow_dialog_ok), null).show();
    }

    public static void showCommonAlertDialog(Context context, String title, String message) {
        new AlertDialogBuilder(context).setTitle(title).setMessage(message).setPositiveButton(context.getString(R.string.bxflow_dialog_ok), null).show();
    }

    public static void showCommonAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        new AlertDialogBuilder(context).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(context.getString(R.string.bxflow_dialog_ok), listener).show();
    }

    public static void showCommonAlertDialogWithDismissListener(Context context, String title, String message, DialogInterface.OnDismissListener listener) {
        new AlertDialogBuilder(context).setTitle(title).setMessage(message).setCancelable(false).setPositiveButton(context.getString(R.string.bxflow_dialog_ok), null).setOnDismissListener(listener).show();
    }






}



