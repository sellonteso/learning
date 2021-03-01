package com.dms.stock.dbservice.resource;

import com.dms.stock.dbservice.model.Quote;
import com.dms.stock.dbservice.model.Quotes;
import com.dms.stock.dbservice.repository.QuotesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {

    private QuotesRepository quotesRepository;

    public DBServiceResource(QuotesRepository quotesRepository) {
        this.quotesRepository = quotesRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String userName){
        
        return getQuotesByUserName(userName);
    }

    private List<String> getQuotesByUserName(String userName) {
        return quotesRepository.findByUserName(userName)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes) {

        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quotesRepository.save(quote));

        return getQuotes(quotes.getUserName());
    }

    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String userName) {
        List<Quote> quotes = quotesRepository.findByUserName(userName);
        quotesRepository.deleteAll(quotes);

        return getQuotesByUserName(userName);
    }
}
