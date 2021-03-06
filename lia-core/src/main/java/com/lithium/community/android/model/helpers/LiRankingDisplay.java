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

package com.lithium.community.android.model.helpers;


import com.google.gson.annotations.SerializedName;
import com.lithium.community.android.model.LiBaseModelImpl;

/**
 * This model represents how a user's rank appears in the community.
 * http://community.lithium.com/t5/Developer-Documentation/bd-p/dev-doc-portal?section=commv2&collection=ranks
 */
public class LiRankingDisplay extends LiBaseModelImpl {

    private Boolean bold;
    private String color;

    @SerializedName("left_image")
    private LiImage leftImage;

    @SerializedName("right_image")
    private LiImage rightImage;

    @SerializedName("thread_image")
    private LiImage threadImage;


    public LiImage getThreadImage() {
        return threadImage;
    }

    @SerializedName("thread_image")
    public void setThreadImage(LiImage threadImage) {
        this.threadImage = threadImage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public LiImage getLeftImage() {
        return leftImage;
    }

    public void setLeftImage(LiImage leftImage) {
        this.leftImage = leftImage;
    }


    public LiImage getRightImage() {
        return rightImage;
    }

    public void setRightImage(LiImage rightImage) {
        this.rightImage = rightImage;
    }
}
