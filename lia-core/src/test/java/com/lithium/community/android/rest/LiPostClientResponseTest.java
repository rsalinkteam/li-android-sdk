/*
 * Copyright 2018 Lithium Technologies Pvt Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lithium.community.android.rest;

import com.lithium.community.android.rest.LiBaseResponse;
import com.lithium.community.android.rest.LiPostClientResponse;

import junit.framework.Assert;

import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

/**
 * Created by shoureya.kant on 12/6/16.
 */

public class LiPostClientResponseTest {

    private LiBaseResponse liBaseResponse;
    private LiPostClientResponse liPostClientResponse;

    @Test
    public void getResponseTest() {
        liBaseResponse = PowerMockito.mock(LiBaseResponse.class);
        liPostClientResponse = new LiPostClientResponse(liBaseResponse);
        Assert.assertEquals(liBaseResponse, liPostClientResponse.getResponse());
    }

    @Test
    public void trahsformedResponseTest() {
        liBaseResponse = PowerMockito.mock(LiBaseResponse.class);
        liPostClientResponse = new LiPostClientResponse(liBaseResponse);
        Assert.assertNull(liPostClientResponse.getTransformedResponse());
    }
}
