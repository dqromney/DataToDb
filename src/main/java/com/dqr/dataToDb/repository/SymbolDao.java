package com.dqr.dataToDb.repository;

import com.dqr.dataToDb.types.SymbolType;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * Symbol Data Access Object (DAO).
 *
 * Created by dqromney on 2/9/17.
 */
public interface SymbolDao {

    @SqlUpdate("insert into symbol (symbol, name, type, exchange) values (:symbol, :name, :type, :exchange)")
    void insert(@Bind("symbol") String symbol, @Bind("name") String name, @Bind("symbolType") SymbolType symbolType, @Bind("exchange") String exchange);

    @SqlQuery("select symbol from symbol where id = :id")
    String findSymbolById(@Bind("id") int id);

    /**
     * close with no args is used to close the connection
     */
    void close();

}
