package com.dqr.dataToDb.mapping;

import com.dqr.dataToDb.model.Eod;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Eod (End of Day) Mapper class.
 * <p>
 * Created by dqromney on 2/13/17.
 */
public class EodMapper implements ResultSetMapper<Eod> {
    public Eod map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Eod(
                r.getLong("id"),
                r.getLong("symbol_id"),
                r.getDate("date"),
                r.getBigDecimal("open"),
                r.getBigDecimal("high"),
                r.getBigDecimal("low"),
                r.getBigDecimal("close"),
                r.getLong("volumne"),
                r.getLong("open_interest"),
                r.getDate("last_updated"));
    }
}
