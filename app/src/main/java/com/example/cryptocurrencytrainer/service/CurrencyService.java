package com.example.cryptocurrencytrainer.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.cryptocurrencytrainer.api.CryptoCoinsCall;
import com.example.cryptocurrencytrainer.api.CurrencyExchangeCall;

public class CurrencyService {
    private ProgressDialog progressBar;
    private TextView costView;

    public String getCurrentCoinCost(String type, Context context, TextView costView) {
        setCostView(costView);
        String cost = CurrencyExchangeCall.getRatting();
        String ratting = CryptoCoinsCall.getCost();
        if (cost.equals("") && ratting.equals("")) {
            progressBar = new ProgressDialog(context);
            progressBar.setMessage("Pobieranie danych...");
            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressBar.show();//displays the progress bar
            CurrencyExchangeCall.getUSDRatting(context);
            CryptoCoinsCall.getCoinCost(type, context, progressBar, this);
        }



        return "0";

    }

    public void calculateCostForOne(){

        String ratting = CurrencyExchangeCall.getRatting();
        String cost = CryptoCoinsCall.getCost();
        Log.i("sprawdzam", ratting + " "+ cost);
        double costInPLN = Double.parseDouble(cost) * Double.parseDouble(ratting);
        costInPLN *= 100;
        costInPLN = Math.round(costInPLN);
        costInPLN /= 100;
        costView.setText(String.valueOf(costInPLN));

    }

    public String calculateFullCost(String cost, String qunatity) {

        double fullCost = Double.parseDouble(cost) * Double.parseDouble(qunatity);
        fullCost *= 100;
        fullCost = Math.round(fullCost);
        fullCost /= 100;

        return String.valueOf(fullCost);
    }

    public void setCostView(TextView costView) {
        this.costView = costView;
    }
}
