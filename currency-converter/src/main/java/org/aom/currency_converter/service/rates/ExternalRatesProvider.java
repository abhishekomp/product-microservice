package org.aom.currency_converter.service.rates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * @author : Abhishek
 * @since : 2024-08-24, Saturday
 **/
@Service
@ConditionalOnProperty(name = "app.feature.localrates", havingValue = "false", matchIfMissing = true)
public class ExternalRatesProvider implements RatesProvider{

    private static final Logger logger = LoggerFactory.getLogger(ExternalRatesProvider.class);

    private final RestTemplate restTemplate;

//    @Value("${open-exchange-rates.access-key}")
    private String accessKey;

    public ExternalRatesProvider(RestTemplate restTemplate, @Value("${open-exchange-rates.access-key}") String accessKey) {
        this.restTemplate = restTemplate;
        this.accessKey = accessKey;
    }

    @Override
    public Map<String, Number> getRates(Date date) {
        logger.info("ExternalRatesProvider getRates(Date) invoked");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String formattedDateAsString = simpleDateFormat.format(date);
        String endpoint = "/api/historical/" + simpleDateFormat.format(date) + ".json?app_id=" + accessKey;
        System.out.println("endpoint = " + endpoint);
        //endpoint = /api/historical/2014-02-11.json?app_id=d400e7eb06654c8389b4c18e183f4917
        //String endpoint = "/api/latest.json?app_id=" + accessKey;
        RatesResponse result = restTemplate.getForObject("http://openexchangerates.org" + endpoint, RatesResponse.class);
        //ResponseEntity<RatesResponse> forEntity = restTemplate.getForEntity("http://openexchangerates.org" + endpoint, RatesResponse.class);
        //return null;
        return result.getRates();
        //return forEntity.getBody().getRates();
    }

    @Override
    public Map<String, Number> getRates() {
        logger.info("ExternalRatesProvider getRates() invoked");
        String endpoint = "/api/latest.json?app_id=" + accessKey;
        RatesResponse result = restTemplate.getForObject("http://openexchangerates.org" + endpoint, RatesResponse.class);
        return result.getRates();
    }

    public static void main(String[] args) {
        ExternalRatesProvider externalRatesProvider = new ExternalRatesProvider(new RestTemplate(), "d400e7eb06654c8389b4c18e183f4917");
        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Map<String, Number> rates = externalRatesProvider.getRates(date);
        System.out.println(rates);
    }
}
