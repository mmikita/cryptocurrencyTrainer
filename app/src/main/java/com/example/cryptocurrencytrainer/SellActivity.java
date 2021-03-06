package com.example.cryptocurrencytrainer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cryptocurrencytrainer.service.CurrencyService;

public class SellActivity extends AppCompatActivity {

    Context buyContext;
    CurrencyService service;
    Spinner coins;
    TextView cost;
    Intent intent;
    String[] values;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buyContext = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        service = new CurrencyService();
        coins = findViewById(R.id.coins);
        cost = findViewById(R.id.cost);
        service.getCurrentCoinCost("Bitcoin", buyContext, cost);
        EditText quantity = findViewById(R.id.quantity);
        intent = getIntent();
        values = intent.getStringArrayExtra("values");
        coins.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                service.getCurrentCoinCost(coins.getSelectedItem().toString(), buyContext, cost);
                TextView fullCost = findViewById(R.id.fullCost);
                EditText quantity = (EditText) findViewById(R.id.quantity);
                quantity.setText("0");
                fullCost.setText("0");

                TextView waletCoins = findViewById(R.id.coinsWallet);
                String[] types = service.defineTypeOfCoin(coins.getSelectedItem().toString(), values);
                waletCoins.setText(types[1]);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        quantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            {


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                TextView cost = findViewById(R.id.cost);
                TextView fullCost = findViewById(R.id.fullCost);

                EditText quantity = (EditText) findViewById(R.id.quantity);
                String message = quantity.getText().toString();
                if (!message.equals(""))
                    fullCost.setText(service.calculateFullCost(cost.getText().toString(), message));
            }
        });
    }


    public void sellCoins(View view) {
        TextView fullCost = findViewById(R.id.fullCost);
        EditText quantity = findViewById(R.id.quantity);
        String message = quantity.getText().toString();


        service.sellCoins(this, message, coins.getSelectedItem().toString(), fullCost.getText().toString(), values);


    }
}
