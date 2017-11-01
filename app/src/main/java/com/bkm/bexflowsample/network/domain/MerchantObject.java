package com.bkm.bexflowsample.network.domain;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Data;

/**
 * Created by buraksoykal on 27/02/2017.
 */

@Data
@Parcel
public class MerchantObject {

    String id;
    String token;
    String path;

    @ParcelConstructor
    public MerchantObject(String id,String token,String path){
        setId(id);
        setToken(token);
        setPath(path);
    }

}





