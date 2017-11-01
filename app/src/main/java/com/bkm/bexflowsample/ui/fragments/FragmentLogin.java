package com.bkm.bexflowsample.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bkm.bexflowsample.R;
import com.bkm.bexflowsample.network.core.RestManagerApp;
import com.bkm.bexflowsample.network.core.RetrofitCallback;
import com.bkm.bexflowsample.network.req.MerchLoginReq;
import com.bkm.bexflowsample.network.res.MerchantLoginResponse;
import com.bkm.bexflowsample.ui.activities.ActivityMain;
import com.bkm.bexflowsample.utils.DialogUtil;

/**
 * Created by buraksoykal on 11/10/2017.
 */

public class FragmentLogin extends Fragment implements View.OnClickListener {

    AppCompatEditText apped_merch_name;
    AppCompatEditText apped_merch_pass;
    AppCompatButton continue_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(root);

        return root;
    }

    private void initViews(View root){

        apped_merch_name = root.findViewById(R.id.apped_merch_name);
        apped_merch_pass = root.findViewById(R.id.apped_merch_pass);
        continue_btn = root.findViewById(R.id.continue_btn);
        continue_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if(view.getId() != continue_btn.getId()) return;

        if(TextUtils.isEmpty(apped_merch_name.getText().toString()) || TextUtils.isEmpty(apped_merch_pass.getText().toString())){
            DialogUtil.showCommonAlertDialog(getActivity(),R.string.bxflow_error_title,R.string.error_invalid_credantials);
        }

        ((ActivityMain)getActivity()).showLoadingDialog();
        RestManagerApp.getInstance().requestMerchantLogin(new MerchLoginReq(apped_merch_name.getText().toString(),apped_merch_pass.getText().toString())).enqueue(new RetrofitCallback<MerchantLoginResponse>(getActivity()) {
            @Override
            public void onSuccess(MerchantLoginResponse successResponse) {
                ((ActivityMain)getActivity()).dismissLoadingDialog();
                ((ActivityMain)getActivity()).setMerchant_token(successResponse.getMerchantObject().getToken());
                ((ActivityMain)getActivity()).setMerchant_path(successResponse.getMerchantObject().getPath());
                ((ActivityMain)getActivity()).startFragment(new FragmentAmount());
            }

            @Override
            public void onFailure(String code, String error) {
                ((ActivityMain)getActivity()).dismissLoadingDialog();
                DialogUtil.showCommonAlertDialog(getActivity(),getString(R.string.bxflow_error_title),error);
            }
        });
//        if(view.getId()==continue_btn){
//            if(apped_merch_name)
//            proceedLogin();
//        }
    }
}
