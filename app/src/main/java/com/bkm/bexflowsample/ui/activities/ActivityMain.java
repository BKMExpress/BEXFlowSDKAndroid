package com.bkm.bexflowsample.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.bkm.bexflowsample.R;
import com.bkm.bexflowsample.ui.fragments.FragmentLogin;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by buraksoykal on 11/10/2017.
 */

public class ActivityMain extends BaseActivity {
    @Getter
    @Setter
    String merchant_token = "";
    @Getter
    @Setter
    String merchant_path = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFragment(new FragmentLogin());

    }

    public void startFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();
    }

    public void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onBackPressed(){
        if(getFragmentManager().getBackStackEntryCount()==1){
            finish();
        }else{
            popFragment();
        }
    }
}
