package org.aom.currency_converter.service.rates;

import java.util.Map;

/**
 * @author : Abhishek
 * @since : 2024-08-24, Saturday
 **/
public class RatesResponse {
    private Map<String, Number> rates;

    public Map<String, Number> getRates() {
        return rates;
    }

    public void setRates(Map<String, Number> rates) {
        this.rates = rates;
    }
}
