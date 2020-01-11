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

public class NetworkManager {

    private static final String TAG = "NetworkManager";
    private static final String PREFIX = "http://192.168.0.109:3000/api";
    private static NetworkManager instance = null;

    public RequestQueue requestQueue;

    private NetworkManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized NetworkManager getInstance(Context context) {
        if (null == instance)
            instance = new NetworkManager(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized NetworkManager getInstance() {
        if (null == instance)
            throw new IllegalStateException(NetworkManager.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        return instance;
    }

    public void getRequest(String url, final VolleyCallback<String> listener) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, PREFIX + url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("onResponse", response.toString());
                listener.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResponse", error.getMessage());
                listener.onError(error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IkFORFJPSURfVElDVEFDVE9FXzExMDEzIiwiaWF0IjoxNTcyMDA4NDkwfQ.QTiFMcjLIYYU0kHR4Pv-RyBbNnlL8gz2RyH4OcyCqoA");
                return headers;
            }
        };

        requestQueue.add(request);
    }

    public void postRequest(String url, JSONObject params, final VolleyCallback<String> listener) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, PREFIX + url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("onResponse", response.toString());
                listener.onSuccess(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IkFORFJPSURfVElDVEFDVE9FXzExMDEzIiwiaWF0IjoxNTcyMDA4NDkwfQ.QTiFMcjLIYYU0kHR4Pv-RyBbNnlL8gz2RyH4OcyCqoA");
                return headers;
            }
        };

        requestQueue.add(request);
    }

}
