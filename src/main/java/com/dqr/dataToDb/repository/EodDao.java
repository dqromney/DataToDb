package com.dqr.dataToDb.repository;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import java.math.BigDecimal;
import java.util.Date;

/**
 * End of Day Data Access Object (DAO).
 *
 * Created by dqromney on 2/9/17.
 */
public interface EodDao {

    @SqlUpdate("insert into eod (symbol_id, date, open, high, low, close, volume, open_interest, last_update) values (:symbol_id, :date, :open, :high, :low, :close, :volume, :open_interest, :last_update)")
    void insert(@Bind("symbol_id") String symbolId, @Bind("date") Date date, @Bind("open") BigDecimal open, @Bind("high") BigDecimal high, @Bind("low") BigDecimal low, @Bind("close") BigDecimal close, @Bind("exchange") String exchange);

//    @SqlQuery("select new Eod(eod.id, eod.symbolId, eod.date, eod.open, eod.high, eod.low, eod.close, eod.volume, eod.openInterest, eod.lastUpdate) from eod where id = :id")
//    Eod findQuoteById(@Bind("id") int id);

    /**
     * close with no args is used to close the connection
     */
    void close();

}
