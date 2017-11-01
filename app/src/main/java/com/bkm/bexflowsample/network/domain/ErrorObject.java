package com.bkm.bexflowsample.network.domain;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Data;

/**
 * Created by buraksoykal on 27/02/2017.
 */

@Data
@Parcel
public class ErrorObject {

    String status;
    String[] details;

    @ParcelConstructor
    public ErrorObject(String status, String[] details){
        setStatus(status);
        setDetails(details);
    }

}





