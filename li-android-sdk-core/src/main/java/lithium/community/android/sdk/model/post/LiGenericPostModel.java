/*
 * LiGenericPostModel.java
 * Created on Dec 27, 2016
 *
 * Copyright 2016 Lithium Technologies, Inc.
 * San Francisco, California, U.S.A.  All Rights Reserved.
 *
 * This software is the  confidential and proprietary information
 * of  Lithium  Technologies,  Inc.  ("Confidential Information")
 * You shall not disclose such Confidential Information and shall
 * use  it  only in  accordance  with  the terms of  the  license
 * agreement you entered into with Lithium.
 */

package lithium.community.android.sdk.model.post;

import com.google.gson.JsonObject;

/**
 * Request body for making any POST call. It requires parameter as JsonObject.
 * Created by shoureya.kant on 12/19/16.
 */

public class LiGenericPostModel extends LiBasePostModel {

    private JsonObject data;

    public JsonObject getData() {
        return data;
    }
    public void setData(JsonObject data) {
        this.data = data;
    }
}
