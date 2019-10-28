package com.koen.tictactoe.controller.API;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class APIController {

    private static final String TAG = "APIController";
    private static final String URL = "http://192.168.0.109:3000/api/computer/move";

    private static APIController instance = null;

    public RequestQueue requestQueue;

    private APIController(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized APIController getInstance(Context context) {
        if (null == instance)
            instance = new APIController(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized APIController getInstance() {
        if (null == instance)
            throw new IllegalStateException(APIController.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        return instance;
    }

    public void getBestPossibleMove() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("onResponse", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                // Basic Authentication
                //String auth = "Basic " + Base64.encodeToString(CONSUMER_KEY_AND_SECRET.getBytes(), Base64.NO_WRAP);

                headers.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IkFORFJPSURfVElDVEFDVE9FXzExMDEzIiwiaWF0IjoxNTcyMDA4NDkwfQ.QTiFMcjLIYYU0kHR4Pv-RyBbNnlL8gz2RyH4OcyCqoA");
                return headers;
            }
        };

        requestQueue.add(request);
    }
}
