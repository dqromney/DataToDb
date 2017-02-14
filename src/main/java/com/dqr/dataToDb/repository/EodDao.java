package com.dqr.dataToDb.repository;

import com.dqr.dataToDb.mapping.EodMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.math.BigDecimal;
import java.util.Date;

/**
 * End of Day Data Access Object (DAO).
 *
 * Created by dqromney on 2/9/17.
 */
@RegisterMapper(EodMapper.class)
public interface EodDao {

    @SqlUpdate("insert into eod (symbol_id, date, open, high, low, close, volume, open_interest, last_update) values (:symbol_id, :date, :open, :high, :low, :close, :volume, :open_interest, :last_update)")
    void insert(@Bind("symbol_id") Long symbolId, @Bind("date") Date date, @Bind("open") BigDecimal open, @Bind("high") BigDecimal high, @Bind("low") BigDecimal low, @Bind("close") BigDecimal close, @Bind("volume") Long volume, @Bind("open_interest") Long openInterest, @Bind("last_update") Date lastUpdate);

//    @SqlQuery("select new Eod(eod.id, eod.symbolId, eod.date, eod.open, eod.high, eod.low, eod.close, eod.volume, eod.openInterest, eod.lastUpdate) from eod where id = :id")
//    Eod findQuoteById(@Bind("id") int id);

    /**
     * close with no args is used to close the connection
     */
    void close();

}
