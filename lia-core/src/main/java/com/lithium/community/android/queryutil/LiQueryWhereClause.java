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

package com.lithium.community.android.queryutil;

import com.lithium.community.android.manager.LiClientManager.Client;

import java.util.ArrayList;

/**
 * Use to set fields for where clause for different clients.
 * Created by kunal.shrivastava on 12/22/16.
 */

public class LiQueryWhereClause {

    ArrayList<LiQueryClause> liQueryClauses;

    public LiQueryWhereClause() {
        liQueryClauses = new ArrayList();
    }

    public LiQueryWhereClause addClause(LiQueryClause liQueryClause) {
        liQueryClauses.add(liQueryClause);
        return this;
    }

    /**
     * Returns all conditions added in 'where' clause.
     */
    public ArrayList<LiQueryClause> getLiQueryClauses() {
        return liQueryClauses;
    }

    /**
     * Specifies fields for where clause for LI_ARTICLES_CLIENT.
     */
    public enum Articles implements ClientWhereKeyColumn {

        CONVERSATION_STYLE("conversation.style"),
        DEPTH("depth");
        private String value;

        Articles(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_ARTICLES_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_ARTICLES_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_BROWSE_CLIENT.
     */
    public enum Node implements ClientWhereKeyColumn {

        CONVERSATION_STYLE("conversation_style"),
        PARENT_ID("parent.id"),
        DEPTH("depth");
        private String value;

        Node(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_BROWSE_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_BROWSE_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_SEARCH_CLIENT.
     */
    public enum Search implements ClientWhereKeyColumn {

        BODY("body"),
        SUBJECT("subject"),
        TAGS_TEXT("tags.text"),
        LABELS_TEXT("labels.text"),
        CONVERSATION_STYLE("conversation.style");

        private String value;

        Search(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_SEARCH_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_SEARCH_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_SUBSCRIPTION_CLIENT.
     */
    public enum Subscription implements ClientWhereKeyColumn {

        TARGET_TYPE("target.type");

        private String value;

        Subscription(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_SUBSCRIPTION_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_SUBSCRIPTION_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_MESSAGE_CHILDREN_CLIENT.
     */
    public enum MessageChildren implements ClientWhereKeyColumn {

        PARENT_ID("parent.id");

        private String value;

        MessageChildren(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_MESSAGE_CHILDREN_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_MESSAGE_CHILDREN_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_QUESTION_CLIENT.
     */
    public enum Question implements ClientWhereKeyColumn {

        AUTHOR_ID("author.id"),
        DEPTH("depth");

        private String value;

        Question(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_QUESTIONS_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_QUESTIONS_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_CATEGORY_CLIENT.
     */
    public enum Category implements ClientWhereKeyColumn {

        NODE_TYPE("node_type");

        private String value;

        Category(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_CATEGORY_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_CATEGORY_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_ARTICLES_BROWSE_CLIENT.
     */
    public enum ArticleBrowse implements ClientWhereKeyColumn {

        BOARD_ID("board.id"),
        DEPTH("depth");

        private String value;

        ArticleBrowse(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_ARTICLES_BROWSE_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_ARTICLES_BROWSE_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_MESSAGE_CLIENT.
     */
    public enum Message implements ClientWhereKeyColumn {

        ID("id");

        private String value;

        Message(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_MESSAGE_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_MESSAGE_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_USER_DETAILS_CLIENT.
     */
    public enum User implements ClientWhereKeyColumn {

        ID("id");

        private String value;

        User(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_USER_DETAILS_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_USER_DETAILS_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_MESSAGE_CLIENT.
     */
    public enum MessageById implements ClientWhereKeyColumn {

        ID("id");

        private String value;

        MessageById(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_MESSAGE_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_MESSAGE_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_FLOATED_MESSAGES_CLIENT.
     */
    public enum FloatedMessage implements ClientWhereKeyColumn {

        MESSAGE_BOARD_ID("message.board.id"),
        SCOPE("scope");

        private String value;

        FloatedMessage(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_FLOATED_MESSAGES_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_FLOATED_MESSAGES_CLIENT.name();
        }
    }

    /**
     * Specifies fields for where clause for LI_SDK_SETTINGS_CLIENT.
     */
    public enum AppSdkSettings implements ClientWhereKeyColumn {

        CLIENT_ID("client_id");

        private String value;

        AppSdkSettings(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public Boolean isVaild(Client client) {
            return Client.LI_SDK_SETTINGS_CLIENT.equals(client);
        }

        public String getClient() {
            return Client.LI_SDK_SETTINGS_CLIENT.name();
        }
    }

    public interface ClientWhereKeyColumn {

        /**
         * returns client.
         */
        String getClient();

        /**
         * returns value of fields which can be used in where clause.
         */
        String getValue();

        /**
         * returns if the field which is used in where clause for a client is valid or not.
         *
         * @param client {@link Client}
         */
        Boolean isVaild(Client client);
    }

}
