package com.dqr.dataToDb.types;

/**
 * Symbol Type enum.
 *
 * Created by dqromney on 2/9/17.
 */
public enum SymbolType {
    UNDEFINED("Undefined", 0),
    STOCK("Stocks", 1),
    INDEX("Indexes", 2),
    FUTURE("Futures", 3),
    CANADA("Canada", 4),
    FUNDS("Funds", 5),
    PTFS("Ptf's", 6);

    private String name;
    private Integer value;

    SymbolType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public static SymbolType findByName(String pType) {
        SymbolType symbolType = SymbolType.UNDEFINED;
        for(SymbolType type: SymbolType.values()) {
            if (pType.equals(type.getName())) {
                symbolType = type;
                break;
            }
        }
        return symbolType;
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
