package com.bkm.bexflowsample.network.req;

import lombok.Data;

/**
 * Created by buraksoykal on 03/01/2017.
 */

@Data
public class CreateIssueReq {
    String amount;
    String installmentUrl;
    String nonceUrl;

    public CreateIssueReq(String amount,String installmentUrl,String nonceUrl){
        setAmount(amount);
        setInstallmentUrl(installmentUrl);
        setNonceUrl(nonceUrl);
    }
}
