package com.example.valcal;

import java.util.ArrayList;

public class CurrencyHandler {

    FixerCurrency fixerCurrency = new FixerCurrency();
    public ArrayList<String> getCurrencies(){
        CurrencyDTO currencyDTO = fixerCurrency.getCurrencies();
        ArrayList<String> currencies = new ArrayList<>();
        for(Currency cur : currencyDTO.codes){
            currencies.add(cur.name);
        }
        return currencies;
    }

    public Rate GetCurrencyRate(String from, String to){
        Rate currencyRate = fixerCurrency.getRate(from, to);

        return currencyRate;

    }
}
