package com.example.valcal;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FixerCurrency implements CurrencyDAO{
    String apiKey = "OTYxNzViZDAtYmMxZi00NzRkLTg2ZDAtNmE1NzBmZjc1MDUx";
    @Override
    public Rate getRate(String from, String to) {
        String urlText = "https://api.m3o.com/v1/currency/Convert?from="+from+"&to="+to;
            HttpURLConnection con = null;
            try{
                URL url = new URL(urlText);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Authorization", "Bearer " + apiKey);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                return new Gson().fromJson(content.toString(),Rate.class);
            }
            catch(Exception e){
                Log.e("error: ", e.toString());
            }
            finally {
                con.disconnect();
            }
            return null;
        }

    @Override
    public CurrencyDTO getCurrencies() {
        String urlText = "https://api.m3o.com/v1/currency/Codes";
        HttpURLConnection con = null;
        try{
            URL url = new URL(urlText);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            CurrencyDTO currencyDTO = new Gson().fromJson(content.toString(), CurrencyDTO.class);
            return currencyDTO;
        }
        catch(Exception e){
            Log.e("error: ", e.toString());
        }
        finally {
            con.disconnect();
        }
        return null;
    }
}
