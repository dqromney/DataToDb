package com.dqr.dataToDb.mapping;

import com.dqr.dataToDb.model.Symbol;
import com.dqr.dataToDb.types.SymbolType;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Symbol Mapper class.
 * <p>
 * Created by dqromney on 2/13/17.
 */
public class SymbolMapper implements ResultSetMapper<Symbol> {
    public Symbol map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Symbol(
                r.getLong("id"),
                r.getString("symbol"),
                r.getString("name"),
                SymbolType.valueOf(r.getString("type")),
                r.getString("exchange"));
    }
}
