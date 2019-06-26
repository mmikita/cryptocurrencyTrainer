package com.example.cryptocurrencytrainer.service;

import com.example.cryptocurrencytrainer.api.CryptoCoinsCall;
import com.example.cryptocurrencytrainer.api.CurrencyExchangeCall;

public class CurrencyService {

    public  String getCurrentCoinCost(String type) {
        String costInUSD = CryptoCoinsCall.getCoinCost(type);
        double costInPLN = Double.parseDouble(costInUSD) * Double.parseDouble(CurrencyExchangeCall.getRatting(type));
        costInPLN *= 100;
        costInPLN = Math.round(costInPLN);
        costInPLN /= 100;
        String costInString = String.valueOf(costInPLN);

        return costInString;
    }

    public String calculateFullCost(String cost, String qunatity){

        double fullCost = Double.parseDouble(cost)*Double.parseDouble(qunatity);
        fullCost *= 100;
        fullCost = Math.round(fullCost);
        fullCost /= 100;

        return String.valueOf(fullCost);
    }

}
