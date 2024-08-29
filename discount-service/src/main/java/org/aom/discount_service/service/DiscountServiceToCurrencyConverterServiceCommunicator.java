package org.aom.discount_service.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Objects;

/**
 * @author : Abhishek
 * @since : 2024-08-29, Thursday
 **/
@Service
public class DiscountServiceToCurrencyConverterServiceCommunicator {
    //public static final String CURRENCY_CONVERTER_SERVICE = "http://localhost:8093/currency-converter/";
    public static final String CURRENCY_CONVERTER_SERVICE = "http://localhost:8093/exchange-api/";

    private final RestTemplate restTemplate;

    public DiscountServiceToCurrencyConverterServiceCommunicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal exchangeCurrency(String from, String to, BigDecimal value) {
        //http://localhost:8080/exchange-api/exchangeCurrency?from=USD&to=INR&value=10
        String url = CURRENCY_CONVERTER_SERVICE + "exchangeCurrency";
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("from", from)
                .queryParam("to", to)
                .queryParam("value", value.toString())
                .build()
                .toUri();

        //ResponseEntity<Number> responseEntity = restTemplate.getForEntity(CURRENCY_CONVERTER_SERVICE + "exchangeCurrency/{movieId}", Number.class, movieInfoId);
        System.out.println("invoking currency converter using url: " + uri.toString());
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        System.out.println("responseEntity.getBody() = " + responseEntity.getBody());
        return new BigDecimal(Objects.requireNonNull(responseEntity.getBody()));
    }

    public static void main(String[] args) {
        DiscountServiceToCurrencyConverterServiceCommunicator service = new DiscountServiceToCurrencyConverterServiceCommunicator(new RestTemplate());
        BigDecimal convertedCurrency = service.exchangeCurrency("USD", "EUR", BigDecimal.valueOf(100));
        System.out.println("convertedCurrency = " + convertedCurrency);
    }
}
