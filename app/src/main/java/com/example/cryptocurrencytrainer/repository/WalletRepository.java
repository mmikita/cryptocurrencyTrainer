package com.example.cryptocurrencytrainer.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WalletRepository extends SQLiteOpenHelper {


    private static final String DB_NAME = "coffeina";
    private static final int DB_VERSION = 2;
    SQLiteDatabase db;
    String[] values;

    public WalletRepository(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    public String[] getCurrentWallet() {
        values = new String[4];

        Cursor cursor = db.query("WALLET",
                new String[]{"VALUE_PLN", "BTC", "ETM", "LTC"},
                "_id = ?",
                new String[]{Integer.toString(1)},
                null, null, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < values.length; i++) {
                values[i] = cursor.getString(i);
            }

        }
        cursor.close();
        db.close();
        return values;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE WALLET(_id INTEGER PRIMARY KEY AUTOINCREMENT, VALUE_PLN TEXT, BTC TEXT, ETM TEXT, LTC TEXT)");
        ContentValues walletRow = new ContentValues();
        walletRow.put("VALUE_PLN", "50000");
        walletRow.put("BTC", "0");
        walletRow.put("ETM", "0");
        walletRow.put("LTC", "0");
        db.insert("WALLET", null, walletRow);

    }

    public void reset() {
        db = this.getWritableDatabase();
        ContentValues walletRow = new ContentValues();
        walletRow.put("VALUE_PLN", "50000");
        walletRow.put("BTC", "0");
        walletRow.put("ETM", "0");
        walletRow.put("LTC", "0");
        values[0] = "50000";
        for (int i = 1; i < values.length; i++) {
            values[i] = "0";
        }
        db.update("WALLET", walletRow, "_id = ?", new String[]{"1"});
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void setValue() {
        ContentValues walletRow = new ContentValues();
        walletRow.put("VALUE_PLN", "1111");
        db.update("WALLET", walletRow, "_id = ?", new String[]{"1"});
    }

    public String[] getValues() {
        return values;
    }

    public void buyCoins(String quantity, String type, String cost, String[] values) {
        String coinType = "";
        String coinQuantity = "";
        switch (type) {
            case "Bitcoin":
                coinType = "BTC";
                coinQuantity = values[1];
                break;
            case "Ethereum":
                coinType = "ETM";
                coinQuantity = values[2];
                break;
            case "Litecoin":
                coinType = "LTC";
                coinQuantity = values[3];
                break;
        }
        db = this.getWritableDatabase();
        String valuePLN = String.valueOf(Double.parseDouble(values[0]) - Double.parseDouble(cost));
        String coinWalltetQuantity = String.valueOf(Double.parseDouble(coinQuantity) + Double.parseDouble(quantity));

        ContentValues walletRow = new ContentValues();
        walletRow.put("VALUE_PLN", valuePLN);
        walletRow.put(coinType, coinWalltetQuantity);
        db.update("WALLET", walletRow, "_id = ?", new String[]{"1"});

        db.close();

    }

    public void sell(String quantity, String type, String cost, String[] values) {
        String coinType = "";
        String coinQuantity = "";
        switch (type) {
            case "Bitcoin":
                coinType = "BTC";
                coinQuantity = values[1];
                break;
            case "Ethereum":
                coinType = "ETM";
                coinQuantity = values[2];
                break;
            case "Litecoin":
                coinType = "LTC";
                coinQuantity = values[3];
                break;
        }
        db = this.getWritableDatabase();
        String valuePLN = String.valueOf(Double.parseDouble(values[0]) + Double.parseDouble(cost));
        String coinWalltetQuantity = String.valueOf(Double.parseDouble(coinQuantity) - Double.parseDouble(quantity));

        ContentValues walletRow = new ContentValues();
        walletRow.put("VALUE_PLN", valuePLN);
        walletRow.put(coinType, coinWalltetQuantity);
        db.update("WALLET", walletRow, "_id = ?", new String[]{"1"});

        db.close();

    }



}
