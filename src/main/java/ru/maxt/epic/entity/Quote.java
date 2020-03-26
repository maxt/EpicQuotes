package ru.maxt.epic.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Quote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "instr_id",nullable = false)
    private Instrument instr;

    @Column(nullable = false)
    private BigDecimal bid;
    @Column(nullable = false)
    private BigDecimal ask;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instrument getInstr() {
        return instr;
    }

    public void setInstr(Instrument instr) {
        this.instr = instr;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(id, quote.id) &&
                Objects.equals(instr, quote.instr) &&
                Objects.equals(bid, quote.bid) &&
                Objects.equals(ask, quote.ask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instr, bid, ask);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", instr=" + instr +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }
}
