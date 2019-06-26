package com.example.cryptocurrencytrainer.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WalletRepository extends SQLiteOpenHelper {


    private static final String DB_NAME = "coffeina";
    private static final int DB_VERSION = 2;
    SQLiteDatabase db;

    public WalletRepository(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getWritableDatabase();
    }

    public String[] getCurrentWallet() {
        String[] values = new String[4];

        Cursor cursor = db.query("WALLET",
                new String[]{"VALUE_PLN", "BTC", "ETM", "LTC"},
                "_id = ?",
                new String[]{Integer.toString(1)},
                null, null, null);
        if(cursor.moveToFirst()){
            for(int i =0; i<values.length; i++){
                Log.i("aaaaaaaa", cursor.getString(i));
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
        walletRow.put("VALUE_PLN", "232");
        walletRow.put("BTC", "1");
        walletRow.put("ETM", "2");
        walletRow.put("LTC", "3");
        db.insert("WALLET", null, walletRow);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void setValue() {
        ContentValues walletRow = new ContentValues();
        walletRow.put("VALUE_PLN", "1111");
        db.update("WALLET", walletRow, "_id = ?", new String[]{"1"});
    }
}
