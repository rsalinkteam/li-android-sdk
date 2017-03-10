/*
 * Utils.java
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

package lithium.community.android.sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lithium.community.android.sdk.model.LiBaseModel;
import lithium.community.android.sdk.model.response.LiBrowse;

/**
 * Utility class for common operations.
 */
public class LiCoreSDKUtils {
    public static final String LOGIN_RESULT = "LOGIN_RESULT";
    private static final int INITIAL_READ_BUFFER_SIZE = 1024;

    private LiCoreSDKUtils() {
    }


    /**
     * Check that the specified argument value is not {@code null}.
     * If it is {@code null}, throw a {@link IllegalArgumentException}
     *
     * @param argValue the argument value to check.
     * @throws IllegalArgumentException if the argument value is {@code null}.
     */
    public static <ArgT> ArgT checkNotNull(ArgT argValue) {
        return checkNotNull(argValue, "Argument cannot be null");
    }

    /**
     * Check that the specified argument value is not {@code null}.
     * If it is {@code null}, throw a {@link IllegalArgumentException}
     *
     * @param argValue the argument value to check.
     * @throws IllegalArgumentException if the argument value is {@code null}.
     */
    public static <ArgT> void checkNotNull(ArgT... argValue) {
        for (ArgT arg : argValue) {
            if (arg == null) {
                throw new IllegalArgumentException("Argument cannot be null");
            }
        }
    }

    /**
     * Check that the specified argument value is not {@code null}.
     * If it is {@code null}, throw a {@link IllegalArgumentException}
     *
     * @param argValue the argument value to check.
     * @throws IllegalArgumentException if the argument value is {@code null}.
     */
    public static <ArgT> ArgT checkNotNull(ArgT argValue, @Nullable String errorMessage) {
        if (argValue == null) {
            throw new IllegalArgumentException((errorMessage == null ? "Argument cannot be null" :
                    errorMessage));
        }
        return argValue;
    }

    /**
     * Ensures that a collection is not null or empty.
     */
    @NonNull
    public static <T extends Collection<?>> T checkCollectionNotEmpty(
            T collection, @Nullable String errorMessage) {
        LiCoreSDKUtils.checkNotNull(collection, errorMessage);
        checkNotNull(!collection.isEmpty(), errorMessage);
        return collection;
    }

    /**
     * Ensures that the string is either null, or a non-empty string.
     */
    @NonNull
    public static String checkNullOrNotEmpty(String str, @Nullable Object errorMessage) {
        if (str != null) {
            checkNotEmpty(str, errorMessage);
        }
        return str;
    }


    /**
     * Ensures that a string is not null or empty.
     */
    @NonNull
    public static String checkNotEmpty(String str, @Nullable Object errorMessage) {
        // ensure that we throw NullPointerException if the value is null, otherwise,
        // IllegalArgumentException if it is empty
        checkNotNull(str, errorMessage);
        checkArgument(!TextUtils.isEmpty(str), String.valueOf(errorMessage));
        return str;
    }


    public static void checkArgument(boolean expression, @NonNull String errorTemplate, Object... params) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(errorTemplate, params));
        }
    }

    /**
     * Read a string from an input stream.
     */
    public static String readInputStream(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        char[] buffer = new char[INITIAL_READ_BUFFER_SIZE];
        StringBuilder sb = new StringBuilder();
        int readCount;
        while ((readCount = br.read(buffer)) != -1) {
            sb.append(buffer, 0, readCount);
        }
        return sb.toString();
    }

    /**
     * Close an input stream quietly, i.e. without throwing an exception.
     */
    public static void closeQuietly(InputStream in) {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException ignored) {
            // deliberately do nothing
        }
    }

    public static int getThemePrimaryColor(Context context) {
        int colorAttr;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            colorAttr = android.R.attr.colorPrimary;
        } else {
            //Get colorAccent defined for AppCompat
            colorAttr = context.getResources().getIdentifier("colorPrimary", "attr", context.getPackageName());
        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(colorAttr, outValue, true);
        return outValue.data;
    }

    /**
     * Converts the consolidated, space-delimited scope string to a set. If the supplied scope
     * string is {@code null}, then {@code null} will be returned.
     */
    @Nullable
    public static Set<String> stringToSet(@Nullable String spaceDelimitedStr) {
        if (spaceDelimitedStr == null) {
            return null;
        }
        List<String> strings = Arrays.asList(TextUtils.split(spaceDelimitedStr, " "));
        LinkedHashSet<String> stringSet = new LinkedHashSet<>(strings.size());
        stringSet.addAll(strings);
        return stringSet;
    }

    /**
     * Checks if json and field are non null and inserts into JSONObject.
     * @param json
     * @param field
     * @param value
     */
    public static void putIfNotNull(
            @NonNull JSONObject json,
            @NonNull String field,
            @Nullable String value) {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        if (value == null) {
            return;
        }
        try {
            json.put(field, value);
        } catch (JSONException ex) {
            throw new IllegalStateException("JSONException thrown in violation of contract", ex);
        }
    }

    /**
     * Checks if json, field and value are non null and inserts into JSONObject.
     * @param json
     * @param field
     * @param value
     */
    public static void put(
            @NonNull JSONObject json,
            @NonNull String field,
            @NonNull JSONObject value) {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        LiCoreSDKUtils.checkNotNull(value, "value must not be null");
        try {
            json.put(field, value);
        } catch (JSONException ex) {
            throw new IllegalStateException("JSONException thrown in violation of contract", ex);
        }
    }

    /*
    Below are general checks on JSONObject.
     */

    public static void putIfNotNull(
            @NonNull JSONObject json,
            @NonNull String field,
            @Nullable Long value) {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        if (value == null) {
            return;
        }
        try {
            json.put(field, value);
        } catch (JSONException ex) {
            throw new IllegalStateException("JSONException thrown in violation of contract", ex);
        }
    }

    public static void putIfNotNull(
            @NonNull JSONObject json,
            @NonNull String field,
            @Nullable Uri value) {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        if (value == null) {
            return;
        }
        try {
            json.put(field, value);
        } catch (JSONException ex) {
            throw new IllegalStateException("JSONException thrown in violation of contract", ex);
        }
    }

    public static String getStringIfDefined(
            @NonNull JSONObject json,
            @NonNull String field) throws JSONException {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        if (!json.has(field)) {
            return null;
        }

        String value = json.getString(field);
        if (value == null) {
            throw new JSONException("field \"" + field + "\" is mapped to a null value");
        }
        return value;
    }

    @Nullable
    public static Long getLongIfDefined(
            @NonNull JSONObject json,
            @NonNull String field)
            throws JSONException {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        if (!json.has(field)) {
            return null;
        }

        return json.getLong(field);
    }

    @Nullable
    public static String iterableToString(@Nullable Iterable<String> strings) {
        if (strings == null) {
            return null;
        }

        Set<String> stringSet = new LinkedHashSet<>();
        for (String str : strings) {
            LiCoreSDKUtils.checkArgument(!TextUtils.isEmpty(str),
                    "individual scopes cannot be null or empty");
            stringSet.add(str);
        }

        if (stringSet.isEmpty()) {
            return null;
        }

        return TextUtils.join(" ", stringSet);
    }

    public static void put(
            @NonNull JSONObject json,
            @NonNull String field,
            @NonNull int value) {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        LiCoreSDKUtils.checkNotNull(value, "value must not be null");

        try {
            json.put(field, value);
        } catch (JSONException ex) {
            throw new IllegalStateException("JSONException thrown in violation of contract, ex");
        }
    }

    public static void put(
            @NonNull JSONObject json,
            @NonNull String field,
            @NonNull String value) {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        LiCoreSDKUtils.checkNotNull(value, "value must not be null");
        try {
            json.put(field, value);
        } catch (JSONException ex) {
            throw new IllegalStateException("JSONException thrown in violation of contract", ex);
        }
    }

    public static void put(
            @NonNull JSONObject json,
            @NonNull String field,
            @NonNull JSONArray value) {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        LiCoreSDKUtils.checkNotNull(value, "value must not be null");
        try {
            json.put(field, value);
        } catch (JSONException ex) {
            throw new IllegalStateException("JSONException thrown in violation of contract", ex);
        }
    }

    @NonNull
    public static String getString(
            @NonNull JSONObject json,
            @NonNull String field)
            throws JSONException {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        if (!json.has(field)) {
            throw new JSONException("field \"" + field + "\" not found in json object");
        }

        String value = json.getString(field);
        if (value == null) {
            throw new JSONException("field \"" + field + "\" is mapped to a null value");
        }
        return value;
    }


    @Nullable
    public static Uri getUriIfDefined(
            @NonNull JSONObject json,
            @NonNull String field)
            throws JSONException {
        LiCoreSDKUtils.checkNotNull(json, "json must not be null");
        LiCoreSDKUtils.checkNotNull(field, "field must not be null");
        if (!json.has(field)) {
            return null;
        }

        String value = json.getString(field);
        if (value == null) {
            throw new JSONException("field \"" + field + "\" is mapped to a null value");
        }

        return Uri.parse(value);
    }

    /**
     * this method creates a Tree like structure depicting parent child relation for the Category and SubCategory in a community.
     * //TODO right now it is hardcoded to LiBrowse and it should be generalized to use LiBaseModel
     * @param response
     * @return Map with Key as Parent node and vlaues as list of children.
     */
    public static Map<LiBrowse, List<LiBaseModel>> getTransformedResponse(List<LiBaseModel> response) {
        LiCoreSDKUtils.checkNotNull(response, "response cannot be null");
        Map<LiBrowse, List<LiBaseModel>> map = new HashMap<>();
        for (LiBaseModel liBaseModel : response) {
            LiBrowse liBrowse = (LiBrowse) liBaseModel.getModel();
            if (map.get(liBrowse.getParent()) == null) {
                List<LiBaseModel> categories = new ArrayList<>();
                categories.add(liBrowse);

                map.put(liBrowse.getParent(), categories);
            } else {
                map.get(liBrowse.getParent()).add(liBrowse);
            }
        }
        return map;
    }

    /**
     * Computes default json as string from raw folder.
     * @param context
     * @param rawResId
     * @return String is the raw file.
     */
    public static String getRawString(Context context, int rawResId) {
        String defaultJsonString;
        InputStream inputStream = context.getResources().openRawResource(rawResId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int readPointer;
        try {
            if (inputStream != null) {
                readPointer = inputStream.read();
                while (readPointer != -1) {
                    byteArrayOutputStream.write(readPointer);
                    readPointer = inputStream.read();
                }
                inputStream.close();
            }
        } catch (IOException e) {
            Log.e("Default JSON read", e.getMessage());
        }
        defaultJsonString = byteArrayOutputStream.toString();
        return defaultJsonString;
    }

    public static boolean isFireBaseIntegrated() {
        try {
            FirebaseInstanceId.getInstance().getToken();
            return true;
        }
        catch (IllegalStateException e) {
            Log.e(LiSDKConstants.LI_LOG_TAG, "FireBase not integrated.");
        }
        return false;
    }

    public static Long getTime(Long time){
        return (time != null ? (time * 1000 + LiSystemClock.INSTANCE.getCurrentTimeMillis()) : null);
    }
}