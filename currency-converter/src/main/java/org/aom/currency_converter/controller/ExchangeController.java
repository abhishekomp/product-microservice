package org.aom.currency_converter.controller;

import org.aom.currency_converter.service.ExchangeService;
import org.aom.currency_converter.service.rates.RatesProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author : Abhishek
 * @since : 2024-08-25, Sunday
 **/
@RestController
@RequestMapping("exchange-api")
public class ExchangeController {

    private final RatesProvider ratesProvider;
    private final ExchangeService exchangeService;

    public ExchangeController(RatesProvider ratesProvider, ExchangeService exchangeService) {
        this.ratesProvider = ratesProvider;
        this.exchangeService = exchangeService;
    }

    @GetMapping("/rates")
    public Map<String, Number> getRates(){
        return ratesProvider.getRates();
    }

    @GetMapping("/exchangeCurrency")
    public String exchangeCurrency(@RequestParam String from, @RequestParam String to, @RequestParam Number value){
        return exchangeService.exchangeCurrency(from, to, value);
    }

}
