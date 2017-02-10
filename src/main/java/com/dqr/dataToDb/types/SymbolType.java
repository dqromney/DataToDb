package com.dqr.dataToDb.types;

/**
 * Symbol Type enum.
 *
 * Created by dqromney on 2/9/17.
 */
public enum SymbolType {
    STOCK("Stock", 1),
    INDEX("Index", 2),
    FUTURE("Future", 3);

    private String name;
    private Integer value;

    SymbolType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SymbolType{");
        sb.append("name='").append(name).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
