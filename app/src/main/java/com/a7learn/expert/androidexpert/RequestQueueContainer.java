package com.a7learn.expert.androidexpert;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by user on 10/29/2017.
 */

public class RequestQueueContainer {
    private static RequestQueue requestQueue;

    private RequestQueueContainer(){

    }
    public static RequestQueue getInstance(Context context){
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(context);
        }
        return requestQueue;
    }
}
