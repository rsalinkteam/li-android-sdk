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

package com.lithium.community.android.ui.components.fragments;


import com.lithium.community.android.model.LiBaseModel;

/**
 * An interface for adding click and long click behaviors to a particular message from the list of messages displayed.
 * Created by sumit.pannalall on 10/25/16.
 */
public interface LiOnMessageRowClickListener {
    /**
     * Normal Click handler method for the selected message
     *
     * @param item {@link LiBaseModel} for the selected message
     */
    void onMessageRowClick(LiBaseModel item);

    /**
     * Long Click handler method for the selected message
     *
     * @param item {@link LiBaseModel} for the selected message
     */
    void onMessageRowLongClick(LiBaseModel item);
}
