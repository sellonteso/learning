package com.dms.stock.stockservice.resource;

import com.dms.stock.stockservice.model.StockQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {

    @Autowired
    RestTemplate restTemplate;

    private YahooFinance yahooFinance;

    public StockResource(YahooFinance yahooFinance) {
        this.yahooFinance = new YahooFinance();
    }
//
//    public StockResource(YahooFinance yahooFinance) {
//        this.yahooFinance = new YahooFinance();
//    }

    @GetMapping("/{username}")
    public List<StockQuote> getStock(@PathVariable("username") final String userName) {

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://localhost:8300/rest/db" + userName, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<String>>() {});

        List<String> quotes  = quoteResponse.getBody();

        return null;
    }
}
