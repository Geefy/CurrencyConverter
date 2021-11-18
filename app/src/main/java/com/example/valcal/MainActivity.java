package com.example.valcal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerFrom;
    Spinner spinnerTo;
    TextView currencyConvertedView;
    TextView amountView;
    CurrencyHandler currencyHandler = new CurrencyHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        currencyConvertedView = findViewById(R.id.currencyConvertedView);
        amountView = findViewById(R.id.amountTextNumber);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SetDropDownData(currencyHandler.getCurrencies());
            }
        });
        thread.start();
    }

    public void ConvertCurrencies(View view){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SetCurrencyRateData(currencyHandler.GetCurrencyRate(spinnerFrom.getSelectedItem().toString(), spinnerTo.getSelectedItem().toString()));
            }
        });
        thread.start();
    }

    private void SetDropDownData(ArrayList<String> currencies){
        Context context = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_spinner_item, currencies);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerFrom.setAdapter(adapter);
                spinnerTo.setAdapter(adapter);
            }
        });
    }

    private void SetCurrencyRateData(Rate currencyRate){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               currencyConvertedView.setText("From: " + currencyRate.from + "\nTo: " + currencyRate.to + "\nRate: " + currencyRate.rate * Integer.parseInt(amountView.getText().toString()) + " " + currencyRate.to);
            }
        });
    }
}