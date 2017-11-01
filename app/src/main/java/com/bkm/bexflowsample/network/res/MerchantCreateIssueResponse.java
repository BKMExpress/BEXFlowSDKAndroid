package com.bkm.bexflowsample.network.res;

import com.bkm.bexflowsample.network.domain.CreateIssueObject;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Parcel
@EqualsAndHashCode(callSuper = true)
public class MerchantCreateIssueResponse extends BaseResponse {

    @SerializedName("data")
    CreateIssueObject createIssueObject;

    @ParcelConstructor
    public MerchantCreateIssueResponse() {
    }
}
