package com.example.cryptocurrencytrainer;

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

public class BuyActivity extends AppCompatActivity {
    CurrencyService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        service = new CurrencyService();
       Spinner spinner = findViewById(R.id.coins);
        TextView cost = findViewById(R.id.cost);
      //  TextView selectedCoin = (TextView)spinner.getSelectedView(); selectedCoin.getText().toString();
        service.getCurrentCoinCost("Bitcoin",this, cost);
        EditText quantity = (EditText) findViewById(R.id.quantity);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        quantity.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}{


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
                if(!message.equals(""))
                    fullCost.setText(service.calculateFullCost(cost.getText().toString(), message));
            }
        });
    }


    public void buyCoins(){
        TextView fullCost = findViewById(R.id.fullCost);
        EditText quantity = (EditText) findViewById(R.id.quantity);


        String message = quantity.getText().toString();
        fullCost.setText(service.calculateFullCost(fullCost.getText().toString(), ""));
    }
}
