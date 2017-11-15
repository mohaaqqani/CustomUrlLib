package com.example.buildcustomurl;

//  Contains Function for building custom URLs along with generic query strings and request params

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CustomUrls {
    private static final String TAG = CustomUrls.class.getCanonicalName();

    /**
     * Builds URLs with subURLs and queryParams
     * @param baseUrls
     * @param queryParams
     * @param subURLList
     * @return url
     */
    public static URL BuildURL(@NonNull String baseUrls, @Nullable List<String> subURLList, @Nullable Map<String,String> queryParams){
        URL url =   null;

        try {
            Uri.Builder base    =   Uri.parse(baseUrls).buildUpon();
            try {
                if (subURLList != null){
                    for(String subUrl:  subURLList){
                        base    =   Uri.parse(base.toString()).buildUpon().appendPath(subUrl);
                    }
                }

            }   catch (NullPointerException e){
                e.printStackTrace();
                Log.w(TAG, "BuildURL: NullPointerException [" + Arrays.toString(e.getStackTrace()) + "]",e.getCause() );
            }
            try {
                if (queryParams !=  null){
                    for (Map.Entry<String,String>  entry :   queryParams.entrySet()){
                        base    =   Uri.parse(base.toString()).buildUpon().appendQueryParameter(entry.getKey(),entry.getValue());
                    }
                }
            }catch (NullPointerException e){
                e.printStackTrace();
                Log.w(TAG, "BuildURL: NullPointerException [" + Arrays.toString(e.getStackTrace()) + "]", e.getCause());
            }
            try {
                url =   new URL(base.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.w(TAG, "BuildURL: MalformedURLException [" + Arrays.toString(e.getStackTrace()) + "]", e.getCause());
            }
        }   catch (Exception e){
            e.printStackTrace();
            Log.w(TAG, "BuildURL: Exception [" + Arrays.toString(e.getStackTrace()) + "]", e.getCause());
        }

        return url;
    }
}
