package org.aom.currency_converter.service;

import org.aom.currency_converter.service.rates.LocalRatesProvider;
import org.aom.currency_converter.service.rates.RatesProvider;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author : Abhishek
 * @since : 2024-08-24, Saturday
 **/
@Service
public class ExchangeService {
    private final RatesProvider ratesProvider;

    public ExchangeService(RatesProvider ratesProvider) {
        this.ratesProvider = ratesProvider;
    }

    public String exchangeCurrency(String from, String to, Number value){
        Map<String, Number> rates = ratesProvider.getRates();
        Number baseCurrValue = (1.0 / rates.get(from).doubleValue()) * value.doubleValue();
        double v = baseCurrValue.doubleValue() * rates.get(to).doubleValue();
        return String.valueOf(v);
    }

    public static void main(String[] args) {
        RatesProvider ratesProvider1 = new LocalRatesProvider();
        ExchangeService exchangeService = new ExchangeService(ratesProvider1);
        //Number currency = exchangeService.exchangeCurrency("EUR", "INR", 10);
        //Number currency = exchangeService.exchangeCurrency("INR", "EUR", 1600.0);
        String currency = exchangeService.exchangeCurrency("USD", "INR", 2.0);
        //Number currency = exchangeService.exchangeCurrency("INR", "USD", 160.0);
        //Number currency = exchangeService.exchangeCurrency("USD", "EUR", 1.0);
        //Number currency = exchangeService.exchangeCurrency("EUR", "USD", 0.5);
        System.out.println(currency);
    }
}
