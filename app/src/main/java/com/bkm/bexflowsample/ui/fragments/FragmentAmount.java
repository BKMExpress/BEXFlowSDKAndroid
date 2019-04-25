package com.bkm.bexflowsample.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bkm.bexflowsample.R;
import com.bkm.bexflowsample.network.core.RestManagerApp;
import com.bkm.bexflowsample.network.core.RetrofitCallback;
import com.bkm.bexflowsample.network.req.CreateIssueReq;
import com.bkm.bexflowsample.network.res.MerchantCreateIssueResponse;
import com.bkm.bexflowsample.ui.activities.ActivityMain;
import com.bkm.bexflowsample.utils.DialogUtil;

import com.bkm.mobil.bexflowsdk.base.BEXPaymentListener;
import com.bkm.mobil.bexflowsdk.base.BEXStarter;
import com.bkm.mobil.bexflowsdk.en.Environment;

/**
 * Created by buraksoykal on 11/10/2017.
 */

public class FragmentAmount extends Fragment implements View.OnClickListener {

    AppCompatEditText apped_amount;
    AppCompatEditText apped_merch_pass;
    AppCompatButton continue_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_amount, container, false);
        initViews(root);
        return root;
    }

    private void initViews(View root){

        apped_amount = root.findViewById(R.id.apped_amount);
        continue_btn = root.findViewById(R.id.continue_btn);
        continue_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() != continue_btn.getId()) return;

        if(TextUtils.isEmpty(apped_amount.getText().toString())){
            DialogUtil.showCommonAlertDialog(getActivity(),R.string.bxflow_error_title,R.string.error_invalid_amount);
        }

        if (((ActivityMain)getActivity()).getMerchant_path().length() == 0 || ((ActivityMain)getActivity()).getMerchant_token().length() == 0) {
            Toast.makeText(getActivity(), "PATH veya TOKEN Boş olamaz!!", Toast.LENGTH_LONG).show();
            return;
        }

        String instUrl = "https://bex-demo.finartz.com/merchant/installments";
        ((ActivityMain)getActivity()).showLoadingDialog();
        RestManagerApp.getInstance().requestCreatePaymentIssue(((ActivityMain)getActivity()).getMerchant_token(), ((ActivityMain)getActivity()).getMerchant_path(), new CreateIssueReq(apped_amount.getText().toString().replace(".",","), instUrl, "")).enqueue(new RetrofitCallback<MerchantCreateIssueResponse>(getActivity()) {
            @Override
            public void onSuccess(MerchantCreateIssueResponse successResponse) {
                ((ActivityMain)getActivity()).dismissLoadingDialog();

                BEXStarter.startBexFlow(getActivity(), Environment.PREPROD, successResponse.getCreateIssueObject().getId(), successResponse.getCreateIssueObject().getPath(), successResponse.getCreateIssueObject().getToken(), new BEXPaymentListener(){
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getActivity(),"Ödeme Başarılı!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCanceled() {
                        Toast.makeText(getActivity(),"Ödeme İptal edildi!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String errorId, String error) {
                        Toast.makeText(getActivity(),"Ödeme Hatası :: "+error,Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(String errorCode, String error) {
                ((ActivityMain)getActivity()).dismissLoadingDialog();
                Toast.makeText(getActivity(), "Payment Ticket Yaratma Problemi :: errMsg -> " + error, Toast.LENGTH_LONG).show();
            }
        });

    }


}
