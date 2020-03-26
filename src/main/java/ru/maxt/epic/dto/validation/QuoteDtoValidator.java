package ru.maxt.epic.dto.validation;

import ru.maxt.epic.dto.QuoteDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implements validation rule: bid<ask
 */
public class QuoteDtoValidator implements ConstraintValidator<ValidQuoteDto,QuoteDto> {
    @Override
    public boolean isValid(QuoteDto value, ConstraintValidatorContext context) {
        if (! (value instanceof QuoteDto)){
            return false;
        }
        QuoteDto quote = (QuoteDto)value;
        if (null==quote.getBid() || null == quote.getAsk()){
            return false;
        }
        return quote.getBid().compareTo(quote.getAsk())<0;
    }
}
