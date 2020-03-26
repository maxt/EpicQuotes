package ru.maxt.epic.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import ru.maxt.epic.dto.QuoteDto;
import ru.maxt.epic.entity.Quote;
import ru.maxt.epic.repository.QuotesRepository;
import javax.annotation.PostConstruct;

@Service
public class PersistenceService {

    private static Logger log = LoggerFactory.getLogger(PersistenceService.class);
    @Autowired
    private ConversionService converter;
    @Autowired
    private QuotesRepository quotesRepository;

    @PostConstruct
    public void init() {
        log.info("[{}] QuoteProcessingService is here", Thread.currentThread().getName());
    }

    @JmsListener(containerFactory = "myFactory", destination = "quotes")
    public void persistQuote(QuoteDto q) {
        log.info("<<< {}",q);
        Quote qq = converter.convert(q, Quote.class);
        log.info("converted: {}",qq);
        quotesRepository.save(qq);
        log.info("Quote {} has been successfully saved", q);
    }
}
