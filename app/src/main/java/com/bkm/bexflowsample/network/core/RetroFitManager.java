package com.bkm.bexflowsample.network.core;


import com.bkm.bexflowsample.network.domain.MerchantObject;
import com.bkm.bexflowsample.network.req.CreateIssueReq;
import com.bkm.bexflowsample.network.req.MerchLoginReq;
import com.bkm.bexflowsample.network.res.MerchantCreateIssueResponse;
import com.bkm.bexflowsample.network.res.MerchantLoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by bsoykal on 07/04/16.
 */
public interface RetroFitManager {


    @POST("v1/merchant/login")
    Call<MerchantLoginResponse> requestMerchantLogin(@Body MerchLoginReq merchLoginReq);


    @POST("v1/merchant/{merchantPath}/ticket?type=payment")
    Call<MerchantCreateIssueResponse> requestCreatePaymentIssue(@Header("Bex-Connection") String merchantToken, @Path("merchantPath") String merchantPath, @Body CreateIssueReq createIssueReq);

    @Headers("Content-Type: application/json")
    @POST("sdk/init")
    Call<MerchantObject> requestMerchantDirectLogin();
}





