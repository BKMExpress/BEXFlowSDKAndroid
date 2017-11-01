package com.bkm.bexflowsample.network.res;


import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import java.util.HashMap;

import lombok.Data;

/**
 * Created by bsoykal on 07/04/16.
 */
@Data
@Parcel

public class BaseResponse {

    String code;
    String call;
    String description;
    String message;
    String result;
    HashMap<String,String> parameters;

    @ParcelConstructor
    public BaseResponse() {
    }
}
