package com.bkm.bexflowsample.network.res;

import com.bkm.bexflowsample.network.domain.MerchantObject;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Parcel
@EqualsAndHashCode(callSuper = true)
public class MerchantLoginResponse extends BaseResponse {

    @SerializedName("data")
    MerchantObject merchantObject;

    @ParcelConstructor
    public MerchantLoginResponse() {
    }
}
