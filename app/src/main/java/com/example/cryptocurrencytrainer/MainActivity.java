package com.example.cryptocurrencytrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cryptocurrencytrainer.repository.WalletRepository;
import com.example.service.CurrencyService;

public class MainActivity extends AppCompatActivity {

    CurrencyService cService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.wallet)).setText(WalletRepository.getCurrentWallet());
        cService = new CurrencyService();
        ((TextView)findViewById(R.id.btc)).setText(cService.getCurrentBitcoinRate());
        ((TextView)findViewById(R.id.etm)).setText(cService.getCurrenteternumRate());
        ((TextView)findViewById(R.id.ltc)).setText(cService.getLitecoinRate());


    }
}
