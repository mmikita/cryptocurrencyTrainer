package com.example.cryptocurrencytrainer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.cryptocurrencytrainer.repository.WalletRepository;
import com.example.service.CurrencyService;

public class MainActivity extends AppCompatActivity {

    CurrencyService cService;
    WalletRepository wr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wr = new WalletRepository(this);

        ((TextView)findViewById(R.id.wallet)).setText(wr.getCurrentWallet());
        cService = new CurrencyService();
        ((TextView)findViewById(R.id.btc)).setText(cService.getCurrentBitcoinRate());
        ((TextView)findViewById(R.id.etm)).setText(cService.getCurrenteternumRate());
        ((TextView)findViewById(R.id.ltc)).setText(cService.getLitecoinRate());


    }

    public void buy(View view) {
wr.setValue();
        ((TextView)findViewById(R.id.wallet)).setText(wr.getCurrentWallet());
    }

    public void sell(View view) {
        wr.setValue();
        ((TextView)findViewById(R.id.wallet)).setText(wr.getCurrentWallet());

    }
}
