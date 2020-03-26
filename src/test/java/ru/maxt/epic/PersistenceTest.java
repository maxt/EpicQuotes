package ru.maxt.epic;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.maxt.epic.entity.Instrument;
import ru.maxt.epic.entity.Quote;
import ru.maxt.epic.repository.InstrumentRepository;
import ru.maxt.epic.repository.QuotesRepository;

import java.math.BigDecimal;

@DataJpaTest
public class PersistenceTest {

    private Logger log = LoggerFactory.getLogger(PersistenceTest.class);

    @Autowired
    private InstrumentRepository instrRepo;
    @Autowired
    private QuotesRepository quotesRepo;

    @Test
    public void persistInstrument(){
        Instrument instr1 = new Instrument("USDRUR");
        instrRepo.save(instr1);
        Assert.notNull(instr1,"Something went really wrong during persisting of Instrument entity");
        log.info("Entity {} saved with id={}", instr1,instr1.getId());
        Assert.notNull(instr1.getId(),"Id was'n set");

        Quote q = new Quote();
        q.setInstr(instr1);
        q.setBid(BigDecimal.valueOf(80));
        q.setAsk(BigDecimal.valueOf(81));

        quotesRepo.save(q);
        Assert.notNull(q.getId(),"Empty id of saved Quote entity");
        log.info("Saved {} with id {}",q,q.getId());


    }
}
