package org.aom.currency_converter.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Abhishek
 * @since : 2024-08-24, Saturday
 **/
public class RateService {
    public Map<String, Number> getRates() {
        Map<String, Number> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.5);
        rates.put("INR", 80.0);
        return rates;
    }
}
