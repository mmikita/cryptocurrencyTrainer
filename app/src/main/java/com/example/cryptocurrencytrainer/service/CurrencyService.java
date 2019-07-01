package com.example.cryptocurrencytrainer.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;

import com.example.cryptocurrencytrainer.MainActivity;
import com.example.cryptocurrencytrainer.api.CryptoCoinsCall;
import com.example.cryptocurrencytrainer.api.CurrencyExchangeCall;
import com.example.cryptocurrencytrainer.repository.WalletRepository;

public class CurrencyService {
    private ProgressDialog progressBar;
    private TextView costView;
    private static boolean isInternetProblem;

    public void getCurrentCoinCost(String type, Context context, TextView costView) {
        setCostView(costView);
        String cost = CurrencyExchangeCall.getRatting();
        String ratting = CryptoCoinsCall.getCost();
        progressBar = new ProgressDialog(context);
        progressBar.setMessage("Pobieranie danych...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.show();//displays the progress bar
        CurrencyExchangeCall.getUSDRatting(context);
        CryptoCoinsCall.getCoinCost(type, context, progressBar, this);

    }

    public void calculateCostForOne() {

        String ratting = CurrencyExchangeCall.getRatting();
        String cost = CryptoCoinsCall.getCost();
        double costInPLN = Double.parseDouble(cost) * Double.parseDouble(ratting);
        costInPLN  = roundTheValue(costInPLN);
        costView.setText(String.valueOf(costInPLN));

    }

    public String calculateFullCost(String cost, String qunatity) {

        double fullCost = Double.parseDouble(cost) * Double.parseDouble(qunatity);
        fullCost  = roundTheValue(fullCost);

        return String.valueOf(fullCost);
    }

    public void setCostView(TextView costView) {
        this.costView = costView;
    }

    public void buyCoins(Context context, String quantity, String type, String cost, String[] values) {
        WalletRepository repo = new WalletRepository(context);

        if(Double.valueOf(values[0])>=Double.valueOf(cost)){
            repo.buyCoins(quantity, type, cost, values);
            Intent mainActivityIntent = new Intent(context, MainActivity.class);
            context.startActivity(mainActivityIntent);
        }

        else
            displayNotReourcesDialog(context);
    }

    public void sellCoins(Context context, String quantity, String type, String cost, String[] values) {

        if(Double.valueOf(values[0])<=Double.valueOf(quantity)){
            WalletRepository repo = new WalletRepository(context);
            repo.sell(quantity, type, cost, values);
            Intent mainActivityIntent = new Intent(context, MainActivity.class);
            context.startActivity(mainActivityIntent);
        }
        else
            displayNotReourcesDialog(context);
    }

    public double roundTheValue(double value){
        value *= 100;
        value = Math.round(value);
        value /= 100;

        return value;
    }

    public String roundTheStringValue(String stringValue){
        double dValue = Double.valueOf(stringValue);

        dValue *= 100;
        dValue = Math.round(dValue);
        dValue /= 100;

        return String.valueOf(dValue);
    }

    public void displayInternetProblemDialog(Context context){

        if(isInternetProblem) {
            AlertDialog alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Problem");
            alertDialog.setMessage("Nie udało się pobrać aktualnego kursu, sprawdź połączenie z internetem i wybierz jeszcze raz rodzaj waluty");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else{
            isInternetProblem = false;
        }
    }

    public void displayNotReourcesDialog(Context context){

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Problem");
        alertDialog.setMessage("Brak wystarczających środków");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();



    }


    public static void setIsInternetProblem(boolean isInternetProblem) {
        CurrencyService.isInternetProblem = isInternetProblem;
    }

    public static boolean isIsInternetProblem() {
        return isInternetProblem;
    }
}


