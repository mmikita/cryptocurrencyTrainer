package com.example.cryptocurrencytrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.cryptocurrencytrainer.repository.WalletRepository;
import com.example.cryptocurrencytrainer.service.CurrencyService;

public class MainActivity extends AppCompatActivity {

    CurrencyService cService;
    WalletRepository wr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wr = new WalletRepository(this);
        cService = new CurrencyService();
        String[] values = wr.getCurrentWallet();
        ((TextView) findViewById(R.id.wallet)).setText(values[0]);
        ((TextView) findViewById(R.id.btc)).setText(values[1]);
        ((TextView) findViewById(R.id.etm)).setText(values[2]);
        ((TextView) findViewById(R.id.ltc)).setText(values[3]);


    }

    public void buy(View view) {
     Intent intent = new Intent(this, BuyActivity.class);
        intent.putExtra("values", wr.getValues());
     startActivity(intent);
    }

    public void sell(View view) {
        wr.setValue();
        ((TextView) findViewById(R.id.wallet)).setText(wr.getCurrentWallet()[0]);

    }
}
