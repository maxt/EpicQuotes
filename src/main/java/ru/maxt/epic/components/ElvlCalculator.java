package ru.maxt.epic.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.maxt.epic.dto.QuoteDto;

import java.math.BigDecimal;

@Component
public class ElvlCalculator {

    @Autowired
    private Cache elvlsCache;
    @Autowired
    private JmsTemplate jmsTemplate;

    public void processQuote(QuoteDto quote){
        CaffeineCache cc = (CaffeineCache)elvlsCache;
        if (!cc.getNativeCache().asMap().containsKey(quote.getIsin())){
            if (0!=quote.getBid().doubleValue()) {      //rule 3
                cc.put(quote.getIsin(), quote.getBid());
            }else{
                cc.put(quote.getIsin(),quote.getAsk()); //rule 4
            }
            sendToPersistenceService(quote);
            return;
        }

        BigDecimal currentElVl = (BigDecimal) cc.get(quote.getIsin()).get();

        if (currentElVl.compareTo(quote.getBid())<0){   //rule 1
            cc.put(quote.getIsin(),quote.getBid());
            sendToPersistenceService(quote);
            return;
        }
        if (currentElVl.compareTo(quote.getAsk())>0){   //rule 2
            cc.put(quote.getIsin(),quote.getAsk());
            sendToPersistenceService(quote);
            return;
        }
    }

    private void sendToPersistenceService(QuoteDto q){
        jmsTemplate.send("quotes",s->s.createObjectMessage(q));
    }

}
