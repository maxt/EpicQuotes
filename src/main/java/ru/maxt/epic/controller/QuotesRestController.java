package ru.maxt.epic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.*;
import ru.maxt.epic.components.ElvlCalculator;
import ru.maxt.epic.dto.QuoteDto;
import ru.maxt.epic.entity.Quote;
import ru.maxt.epic.repository.QuotesRepository;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class QuotesRestController {

    private Logger log = LoggerFactory.getLogger(QuotesRestController.class);
    @Autowired
    private ElvlCalculator calculator;

    @Autowired
    private Cache elvlCache;

    @Autowired
    private QuotesRepository quotesRepository;

    @GetMapping(path = "/test")
    public String test() {
        return new Date().toString();
    }

    @PostMapping(path = "/quote")
    public void postQuote(@Valid @RequestBody QuoteDto q) {
        log.info("/quote request with parameter {}", q);
        calculator.processQuote(q);
    }

    @GetMapping(path = "/elvls")
    @ResponseBody
    public Map<Object, Object> getElvs() {
        CaffeineCache cc = (CaffeineCache) elvlCache;
        return cc.getNativeCache().asMap();
    }

    @GetMapping(path = "/elvl")
    public String getElvl(@RequestParam(name = "isin") String isin) {
        CaffeineCache cc = (CaffeineCache) elvlCache;
        return cc.get(isin).get().toString();
    }
    @GetMapping(path = "/history")
    public Iterable<Quote> getQuotes(){
        return quotesRepository.findAll();
    }
}
