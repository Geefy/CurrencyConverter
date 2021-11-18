package com.example.valcal;

public interface CurrencyDAO {
    Rate getRate(String base, String toConvertTo);
    CurrencyDTO getCurrencies();

}
