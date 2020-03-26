package ru.maxt.epic.dto;

import ru.maxt.epic.dto.validation.ValidQuoteDto;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@ValidQuoteDto
public class QuoteDto implements Serializable {

    @Size(min = 12,max = 12)
    private String isin;

    private BigDecimal bid;

    private BigDecimal ask;

    public QuoteDto(){

    }

    public QuoteDto(String isin, double bid, double ask) {
        this.isin = isin;
        this.bid = BigDecimal.valueOf(bid);
        this.ask = BigDecimal.valueOf(ask);
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return "QuoteDto{" +
                "isin='" + isin + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }
}
