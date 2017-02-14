package com.dqr.dataToDb.repository;

import com.dqr.dataToDb.mapping.SymbolMapper;
import com.dqr.dataToDb.model.Symbol;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Symbol Data Access Object (DAO).
 * <p>
 * Created by dqromney on 2/9/17.
 */
@RegisterMapper(SymbolMapper.class)
public interface SymbolDao {

    /**
     * Insert.
     *
     * @param symbol     the symbol
     * @param name       the name
     * @param type       the symbol type
     * @param exchange   the exchange
     */
    @SqlUpdate("insert into symbol (symbol, name, type, exchange) values (:symbol, :name, :type, :exchange)")
    void insert(@Bind("symbol") String symbol, @Bind("name") String name, @Bind("type") Integer type, @Bind("exchange") String exchange);

    /**
     * Find symbol by id string.
     *
     * @param id the id
     * @return the string
     */
    @SqlQuery("select symbol from symbol where id = :id")
    String findSymbolById(@Bind("id") int id);

    /**
     * Find symbol by symbol symbol.
     *
     * @param symbol the symbol
     * @return the symbol
     */
    @SqlQuery("select * from symbol where symbol = :symbol")
    Symbol findBySymbol(@Bind("symbol") String symbol);

    /**
     * close with no args is used to close the connection
     */
    void close();

}
