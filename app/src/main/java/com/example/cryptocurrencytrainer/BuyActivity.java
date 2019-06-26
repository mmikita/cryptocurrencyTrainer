package com.example.cryptocurrencytrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cryptocurrencytrainer.service.CurrencyService;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
       Spinner spinner = findViewById(R.id.coins);
        TextView cost = findViewById(R.id.cost);
      //  TextView selectedCoin = (TextView)spinner.getSelectedView(); selectedCoin.getText().toString();
        cost.setText(CurrencyService.getCurrentCoinCost("Bitcoin"));


    }
}
