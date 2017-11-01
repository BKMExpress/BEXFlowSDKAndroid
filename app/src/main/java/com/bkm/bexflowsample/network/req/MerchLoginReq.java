package com.bkm.bexflowsample.network.req;

import lombok.Data;

/**
 * Created by buraksoykal on 03/01/2017.
 */

@Data
public class MerchLoginReq {
    String id;
    String signature;

    public MerchLoginReq(String id, String signature){
        setId(id);
        setSignature(signature);
    }
}
