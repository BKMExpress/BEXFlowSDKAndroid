
package com.bkm.bexflowsample.ui.activities;

import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatImageView;
import android.view.MenuItem;
import android.view.View;


public class BaseActivity extends AppCompatActivity {
    private int loadingProgressCounter;
    private AppCompatDialog dialogLoading;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View loadingFrame = getLayoutInflater().inflate(com.bkm.mobil.bexflowsdk.R.layout.bxflow_dialog_loading, null);
        final AppCompatImageView appimgLoading;

        appimgLoading = (AppCompatImageView) loadingFrame.findViewById(com.bkm.mobil.bexflowsdk.R.id.appimg_loading);
        final AnimationDrawable animationDrawable = (AnimationDrawable) appimgLoading.getDrawable();

        dialogLoading = new AppCompatDialog(this, com.bkm.mobil.bexflowsdk.R.style.bxflow_LoadingDialog);
        dialogLoading.setContentView(loadingFrame);
        dialogLoading.setCancelable(false);
        dialogLoading.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ViewCompat.postOnAnimation(appimgLoading, new Runnable() {
                    @Override
                    public void run() {
                        animationDrawable.start();
                    }
                });
            }
        });
        dialogLoading.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                ViewCompat.postOnAnimation(appimgLoading, new Runnable() {
                    @Override
                    public void run() {
                        animationDrawable.stop();
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void showLoadingDialog() {
        if (!dialogLoading.isShowing() && loadingProgressCounter == 0)
            dialogLoading.show();
        loadingProgressCounter++;
    }

    public void dismissLoadingDialog() {
        loadingProgressCounter--;
        if (dialogLoading.isShowing() && loadingProgressCounter == 0)
            dialogLoading.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissLoadingDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
