package com.example.cryptocurrencytrainer.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WalletRepository extends SQLiteOpenHelper {


    private static final String DB_NAME = "coffeina";
    private static final int DB_VERSION = 1;
    SQLiteDatabase db;

    public WalletRepository(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = this.getReadableDatabase();
    }

    public  String getCurrentWallet(){



        Cursor cursor = db.query ("WALLET",
                new String[] {"VALUE"},
                "_id = ?",
                new String[] {Integer.toString(1)},
                null, null,null);


        return cursor.moveToFirst()? cursor.getString(0)+ " zł": "0 zł";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE WALLET(_id INTEGER PRIMARY KEY AUTOINCREMENT, VALUE TEXT)");


        ContentValues walletRow = new ContentValues();
        walletRow.put("VALUE", "323");
        db.insert("WALLET", null, walletRow);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void setValue()
    {
        ContentValues walletRow = new ContentValues();
        walletRow.put("VALUE", "1111");
        db.update("WALLET", walletRow, "_id = ?", new String[]{"1"});
    }
}
