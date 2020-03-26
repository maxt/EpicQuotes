package ru.maxt.epic.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.maxt.epic.dto.QuoteDto;
import ru.maxt.epic.entity.Instrument;
import ru.maxt.epic.entity.Quote;
import ru.maxt.epic.repository.InstrumentRepository;
import java.util.List;

@Component
public class QuoteDtoToQuoteConverter implements Converter<QuoteDto, Quote> {

    @Autowired
    private InstrumentRepository instrRepo;

    @Override
    public Quote convert(QuoteDto source) {
        Instrument instr;
        List<Instrument> instrL = instrRepo.findByIsin(source.getIsin());
        if (instrL.isEmpty()) {
            instr = new Instrument(source.getIsin());
            instrRepo.save(instr);
        } else {
            instr = instrL.get(0);
        }
        Quote q = new Quote();
        q.setInstr(instr);
        q.setBid(source.getBid());
        q.setAsk(source.getAsk());
        return q;
    }
}
