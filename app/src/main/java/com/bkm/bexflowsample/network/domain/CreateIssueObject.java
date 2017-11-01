package com.bkm.bexflowsample.network.domain;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Data;

@Data
@Parcel
public class CreateIssueObject {

    String id;
    String token;
    String path;
    String reply;

    @ParcelConstructor
    public CreateIssueObject() {
    }
}
