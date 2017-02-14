package com.dqr.dataToDb.service;

import com.dqr.dataToDb.model.Eod;
import com.dqr.dataToDb.model.Symbol;
import com.dqr.dataToDb.repository.EodDao;
import com.dqr.dataToDb.repository.SymbolDao;
import org.skife.jdbi.v2.DBI;

/**
 * Securities service.
 *
 * Created by dqromney on 2/13/17.
 */
public class Securities {
    private DBI dbi;

    public Securities(DBI pDbi) {
        this.dbi = pDbi;
    }

    public void add(Symbol pSymbol, Eod pEod) {
        SymbolDao symbolDao = dbi.open(SymbolDao.class);
        EodDao eodDao = dbi.open(EodDao.class);

        Symbol symbol = symbolDao.findBySymbol(pSymbol.getSymbol());
        if (symbol == null) {
            symbolDao.insert(pSymbol.getSymbol(), pSymbol.getName(), pSymbol.getType(), pSymbol.getExchange());
            symbol = symbolDao.findBySymbol(pSymbol.getSymbol());
        }
        pEod.setSymbolId(symbol.getId());
        eodDao.insert(symbol.getId(), pEod.getDate(), pEod.getOpen(), pEod.getHigh(), pEod.getLow(), pEod.getClose(), pEod.getVolume(), pEod.getOpenInterest(), pEod.getLastUpdate());

        symbolDao.close();
        eodDao.close();
    }
}
