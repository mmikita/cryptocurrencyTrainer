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

public class CryptoCoinsCall {

    private static String REQUEST_LINK = "https://api.coinpaprika.com/v1/ticker/[coin]";
    private static String cost = "";

    public static String getCoinCost(String type, Context context) {


        String linkToRequest = REQUEST_LINK.replace("[coin]", getParamValue(type));
Log.i("linki", linkToRequest);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest
                (Request.Method.GET, linkToRequest, (String) null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("OnResponse: ", response.toString());
                        try {
                            cost = response.getString("price_usd");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response error: ", error.toString());

                    }
                });

        MyRequestQueue.getInstance(context).addToRequestQueue(jsonArrayRequest);

        return "12823.13";
    }


    private static String getParamValue(String type) {
        String value = "";
        switch (type) {
            case "Bitcoin":
                value = "btc-bitcoin";
                break;
            case "Ethereum":
                value = "btc-bitcoin";
                break;
            case "Litecoin":
                value = "eth-ethereum\n";
                break;
        }
        return value;
    }
}
