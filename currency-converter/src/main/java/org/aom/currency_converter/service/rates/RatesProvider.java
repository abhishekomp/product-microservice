package org.aom.currency_converter.service.rates;

import java.util.Date;
import java.util.Map;

public interface RatesProvider {
    Map<String, Number> getRates(Date date);
    Map<String, Number> getRates();
}