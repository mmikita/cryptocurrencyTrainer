package com.example.cryptocurrencytrainer.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CurrencyExchangeCall {

    private static String REQUEST_LINK = "http://api.nbp.pl/api/exchangerates/rates/a/USD?format=json";
    private static String ratting = "";


    public static void getUSDRatting(Context context){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, REQUEST_LINK, (String) null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("OnResponseeee: ", response.toString());
                        try {
                            ratting = response.getJSONArray("rates").getJSONObject(0).getString("mid");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("JSONError", "Error occuring while recieve JSON");
                        };
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response error: ", error.toString());

                    }
                });

        MyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);



    }


    public static String getRatting() {
        return ratting;
    }



}
