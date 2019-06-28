package com.example.cryptocurrencytrainer.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cryptocurrencytrainer.service.CurrencyService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CryptoCoinsCall {

    private static String REQUEST_LINK = "https://api.coinpaprika.com/v1/ticker/[coin]";
    private static String cost = "";

    public static String getCoinCost(String type, Context context, final ProgressDialog progressBar, final CurrencyService service) {


        String linkToRequest = REQUEST_LINK.replace("[coin]", getParamValue(type));
Log.i("linki", linkToRequest);

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest
                (Request.Method.GET, linkToRequest, (String) null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("OnResponse: ", response.toString());
                        try {
                            cost = response.getString("price_usd");
                            progressBar.dismiss();
                            service.calculateCostForOne();
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

        return "0";
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

    public static String getCost() {
        return cost;
    }
}
