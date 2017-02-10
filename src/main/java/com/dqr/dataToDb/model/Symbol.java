package com.dqr.dataToDb.model;

import com.dqr.dataToDb.types.SymbolType;

/**
 * Symbol model class.
 *
 * Created by dqromney on 2/9/17.
 */
public class Symbol {
    private Long id;
    private String symbol;
    private String name;
    private SymbolType type;
    private String exchange;

    public Symbol() {
        // Empty
    }

    public Symbol(Long id, String symbol, String name, SymbolType type, String exchange) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.exchange = exchange;
    }

    // ----------------------------------------------------------------
    // Access methods
    // ----------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SymbolType getType() {
        return type;
    }

    public void setType(SymbolType type) {
        this.type = type;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Symbol{");
        sb.append("id=").append(id);
        sb.append(", symbol='").append(symbol).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", exchange='").append(exchange).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
