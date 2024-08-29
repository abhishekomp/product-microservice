package org.aom.currency_converter.service.rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Abhishek
 * @since : 2024-08-25, Sunday
 **/
@Service
@ConditionalOnProperty(name = "app.feature.localrates", havingValue = "true")
public class LocalRatesProvider implements RatesProvider {

    private static final Logger logger = LoggerFactory.getLogger(LocalRatesProvider.class);
    @Override
    public Map<String, Number> getRates(Date date) {
        logger.info("LocalRatesProvider getRates(Date) invoked with date: {}", date);
        Map<String, Number> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.5);
        rates.put("INR", 80.0);
        return rates;
    }

    @Override
    public Map<String, Number> getRates() {
        logger.info("LocalRatesProvider getRates() invoked");
        return getRates(null);
    }
}
