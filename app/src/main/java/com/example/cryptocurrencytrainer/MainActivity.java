package com.example.cryptocurrencytrainer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        setWalletValues();


    }

    public void setWalletValues(){
        String[] values = null;
        if(wr.getValues() == null){
           values = wr.getCurrentWallet();
        }
       else{
            values = wr.getValues();
        }
        values[0] = cService.roundTheStringValue(values[0]);
        ((TextView) findViewById(R.id.wallet)).setText(values[0]);
        ((TextView) findViewById(R.id.btc)).setText(values[1]);
        ((TextView) findViewById(R.id.etm)).setText(values[2]);
        ((TextView) findViewById(R.id.ltc)).setText(values[3]);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item:

                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Resetowanie portfela")
                        .setMessage("Czy na pewno chcesz zresetować portfel?")
                        .setPositiveButton("Tak", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                wr.reset();
                                setWalletValues();
                            }
                        })
                        .setNegativeButton("Nie", null)
                        .show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void buy(View view) {
        Intent intent = new Intent(this, BuyActivity.class);
        intent.putExtra("values", wr.getValues());
        startActivity(intent);
    }


    public void sell(View view) {
        Intent intent = new Intent(this, SellActivity.class);
        intent.putExtra("values", wr.getValues());
        startActivity(intent);

    }
}
